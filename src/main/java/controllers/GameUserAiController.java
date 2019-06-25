package controllers;

import dto.GamePoleDTO;
import dto.UserDTO;
import entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.GameService;
import service.MoveService;
import service.PlayerService;
import service.UserService;
import service.impl.GameUserAiService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;


@Controller
public class GameUserAiController {

	private static final Logger LOG = Logger.getLogger(GameUserAiController.class);
	@Autowired
	private GameService gameService;
	@Autowired
	private MoveService moveService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private UserService userService;
	@Autowired
	private GameUserAiService gameUserAiService;


	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model) {
		model.addAttribute("gKey", gameUserAiService.getNewGameAi());
		LOG.info("Started processing");
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.GET, params = "continue")
	public String continueGameUserAiGet(Model model, HttpServletRequest request) {
		if (gameService.getGameByStatus(EnumConstants.STARTED) != null) {
			model.addAttribute("gKey", gameUserAiService.getContinueGameAi());
		} else {
			return "pages/gameLogin";
		}
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(HttpServletRequest request, @RequestParam("idGame") long idGame, HttpSession session,
	                             @Valid @ModelAttribute("gKey") GamePoleDTO gPole, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			Game game = gameService.getGameByID(idGame);
			game.setStatus(EnumConstants.STARTED);
			gameService.saveOfUpdate(game);
//			Мой ход :
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			UserEntity user = userService.getUserById(userDTO.getId());
			createMove(game, gPole, user);
//			Проверка победителя:
			String loginWin = checkWinner(playerService.getPlayerByUserGame(user, game).getSign(), gPole, game, request);
			if (!loginWin.equalsIgnoreCase("")){
				request.setAttribute("win", loginWin);
				return "pages/winner";
			}else {
//				Ход компьютера:
				UserEntity ai = userService.getUserByLogin(EnumConstants.AI);
				createMoveAi(game, gPole, ai, request);
//				Проверка победителя:
				String aiWin = checkWinner(playerService.getPlayerByUserGame(ai, game).getSign(), gPole, game, request);
				if (!aiWin.equalsIgnoreCase("")){
					request.setAttribute("win", aiWin);
					return "pages/winner";
				}
			}
		}
		return "pages/aiGame";
	}


	public void createMove(Game game, GamePoleDTO gPole, UserEntity user) {

		MoveEntity moveEntity = null;
		MoveEntity move = new MoveEntity();

		for (int i = 0; i < EnumConstants.S * EnumConstants.S; i++) {
			String mv = gPole.getgAll().get(i);
			try {
				moveEntity = moveService.getMoveByGamePole(game, String.valueOf(i));
				if (!("".equalsIgnoreCase(mv) && "".equalsIgnoreCase(moveEntity.getMove()))) {
					moveService.saveOfUpdate(moveEntity);
				}
			} catch (NoResultException nre) {
				if (!"".equalsIgnoreCase(mv)) {
					move.setGame(game);
					move.setPole(String.valueOf(i));
					move.setMove(mv);
					move.setUser(user);
					moveService.saveOfUpdate(move);
					createPlayer(move, game, user);
				}
			}
		}
	}



	public void createPlayer(MoveEntity move, Game game, UserEntity user) {
		if (game.getWinner() == null) {
			Player playerDb = null;
			try {
				playerDb = playerService.getPlayerByUserGame(user, game);
				if (playerDb != null) {
					playerService.saveOfUpdate(playerDb);
				}

			} catch (NoResultException n) {
				Player player = new Player();
				player.setUser(user);
				player.setGame(game);
				player.setSign(move.getMove());
				playerService.saveOfUpdate(player);
			}
		}
	}

	public void createMoveAi(Game game, GamePoleDTO gPole, UserEntity ai, HttpServletRequest request) {
		String rPole = ai(gPole);
		Map<Integer, String> gPoleNew = gPole.getgAll();
		gPoleNew.put(Integer.valueOf(rPole), EnumConstants.O);
		gPole.setgAll(gPoleNew);
		request.setAttribute("p" + rPole, gPole.getgAll().get(rPole));
//		MoveEntity moveAi = new MoveEntity();
//		moveAi.setMove("o");
//		moveAi.setUser(ai);
//		moveAi.setPole(rPole);
//		moveAi.setGame(game);
//		moveService.saveOfUpdate(moveAi);
//		saveOfUpdate(moveAi,game,ai);
		createMove(game, gPole, ai);
	}

	public String ai(GamePoleDTO gamePoleDTO) {
		Random random = new Random();
		String randomPole = null;
		List<Integer> free = new ArrayList<>();
		Map<Integer, String> map = gamePoleDTO.getgAll();
		for (int i = 0; i < EnumConstants.S * EnumConstants.S; i++) {
			if (map.get(i) == null || "".equalsIgnoreCase(map.get(i)))
				free.add(i);
		}

		if (free.size() != 0) {
			int pole = random.nextInt(free.size());
			randomPole = String.valueOf(free.get(pole));
		}
		free.clear();

		return randomPole;
	}

	public String checkWinner(String key, GamePoleDTO gamePoleDTO, Game game, HttpServletRequest request) {

		LOG.debug("Started check winner");

		if (horizontalCheck(key, gamePoleDTO, game) == true ||
				verticalCheck(key, gamePoleDTO, game) == true ||
				diagonalCheck1(key, gamePoleDTO, game) == true ||
				diagonalCheck2(key, gamePoleDTO, game) == true) {
			return saveWinDb(key, game, request);
		}else if (drawCheck(gamePoleDTO, game) == true){
			return saveWinDb(EnumConstants.STANDOFF, game, request);
		}
		return "";
	}

	public boolean horizontalCheck(String key, GamePoleDTO gamePoleDTO, Game game) {
		boolean winner = false;
		for (int i = 0; i < EnumConstants.S * EnumConstants.S; i += EnumConstants.S) {
			if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
				winner = true;
				for (int j = 0; j < EnumConstants.S; j++) {
					if (!gamePoleDTO.getgAll().get(i + j).equalsIgnoreCase(key)) {
						winner = false;
						break;
					}
				}
			} else return winner;
		}
		return winner;
	}

	public boolean verticalCheck(String key, GamePoleDTO gamePoleDTO, Game game) {
		boolean winner = false;
		for (int i = 0; i < EnumConstants.S * EnumConstants.S; i += EnumConstants.S) {
			if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
				winner = true;
				for (int j = 0; j < EnumConstants.S; j++) {
					if (!gamePoleDTO.getgAll().get((i / EnumConstants.S) + (j * EnumConstants.S)).equalsIgnoreCase(key)) {
						winner = false;
						break;
					}
				}
			} else return winner;
		}
		return winner;
	}

	public boolean diagonalCheck1(String key, GamePoleDTO gamePoleDTO, Game game) {
		boolean winner = false;
		if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
			winner = true;
			for (int i = 0; i < EnumConstants.S * EnumConstants.S; i += EnumConstants.S + 1) {
				if (!gamePoleDTO.getgAll().get(i).equalsIgnoreCase(key)) {
					winner = false;
				}
			}
		} else return winner;

		return winner;
	}

	public boolean diagonalCheck2(String key, GamePoleDTO gamePoleDTO, Game game) {
		boolean winner = false;
		if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
			winner = true;
			for (int i = EnumConstants.S - 1; i < (EnumConstants.S * EnumConstants.S) - 1; i += EnumConstants.S - 1) {
				if (!gamePoleDTO.getgAll().get(i).equalsIgnoreCase(key)) {
					winner = false;
				}
			}
		} else return winner;
		return winner;
	}

	public boolean drawCheck(GamePoleDTO gamePoleDTO, Game game) {
		boolean winner = false;
		if (!gamePoleDTO.getgAll().containsValue("") && "".equalsIgnoreCase(game.getWinner().getLogin())) {
			winner = true;
		}
		return winner;
	}

	public String saveWinDb(String winner, Game game, HttpServletRequest request) {
		String win = "";
		if (!"".equalsIgnoreCase(winner)) {
			Game refGame = game;
			if (!"".equalsIgnoreCase(winner) && !EnumConstants.STANDOFF.equalsIgnoreCase(winner)) {
				UserEntity user = playerService.getPlayerByGameSign(game, winner).getUser();
				refGame.setWinner(user);
				refGame.setStatus(EnumConstants.COMPLETE);
				gameService.saveOfUpdate(refGame);
				win = "Winner : " + user.getLogin();

			} else {
				if (!"".equalsIgnoreCase(winner) && EnumConstants.STANDOFF.equalsIgnoreCase(winner)) {
					UserEntity n = userService.getUserByLogin(winner);
					refGame.setWinner(n);
					refGame.setStatus(EnumConstants.COMPLETE);
					gameService.saveOfUpdate(refGame);
					win = "label.winNO" + "  : STANDOFF";
				}
			}
		}
		return win;
	}
}
