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




	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model, HttpServletRequest request){
		GamePoleDTO gamePoleDTO = new GamePoleDTO();
			Game game = new Game();
			game.setStatus(EnumConstants.STARTED);
			gameService.saveOfUpdate(game);
		request.setAttribute("idGame", gameService.getGameByStatus(EnumConstants.STARTED).get(0).getId_game());
		model.addAttribute("gKey", gamePoleDTO);
		LOG.info("Started processing");
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.GET, params = "continue")
	public String continueGameUserAiGet(Model model, HttpServletRequest request){
		GamePoleDTO gamePoleDTO = new GamePoleDTO();
		if(gameService.getGameByStatus(EnumConstants.STARTED) != null) {
			displayGame(gameService.getGameByStatus(EnumConstants.STARTED).get(0), gamePoleDTO);
			request.setAttribute("idGame", gameService.getGameByStatus(EnumConstants.STARTED).get(0).getId_game());
			model.addAttribute("gKey", gamePoleDTO);
		}else {
			return "pages/gameLogin";
		}
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(HttpServletRequest request, @RequestParam ("idGame") long idGame, HttpSession session,
	                             @Valid@ModelAttribute("gKey") GamePoleDTO gPole, BindingResult result, Model model){
		if(!result.hasErrors()) {
			Game game = gameService.getGameByID(idGame);
			game.setStatus(EnumConstants.STARTED);
			gameService.saveOfUpdate(game);
//			Мой ход :
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			UserEntity user = userService.getUserByLogin(userDTO.getLogin());
			createMove(game,gPole,user);
//			Проверка победителя:
			checkWinner( EnumConstants.X, gPole, game, request);
//			Ход компьютера:
			UserEntity ai = userService.getUserByLogin(EnumConstants.AI);
			createMoveAi(game,gPole, ai,request);
//			Проверка победителя:
			checkWinner( EnumConstants.O, gPole, game, request);

		}
		return "pages/aiGame";
	}


	public void createMove(Game game,GamePoleDTO gPole, UserEntity user){

		MoveEntity moveEntity = null;
		MoveEntity move = new MoveEntity();

	for (int i = 0; i < EnumConstants.S * EnumConstants.S; i++) {
		String mv = gPole.getgAll().get(i);
		try {
			moveEntity = moveService.getMoveByGamePole(game,String.valueOf(i));
			if (!("".equalsIgnoreCase(mv) && "".equalsIgnoreCase(moveEntity.getMove()))) {
				moveService.saveOfUpdate(moveEntity);
			}
		}catch (NoResultException nre){
			if (!"".equalsIgnoreCase(mv)){
				move.setGame(game);
				move.setPole(String.valueOf(i));
				move.setMove(mv);
				move.setUser(user);
				moveService.saveOfUpdate(move);
				createPlayer(move,game,user);
			}
		}
	}
	}

	public void displayGame(Game game, GamePoleDTO gamePoleDTO) {
		Map<Integer, String> gPole2 = new HashMap<>();
		for (MoveEntity m : moveService.getMoveByGame(game)) {
			gPole2.put(Integer.valueOf(m.getPole()), m.getMove());
		}
		gamePoleDTO.setgAll(gPole2);
	}

	public void createPlayer(MoveEntity move, Game game, UserEntity user){
		if ( game.getWinner() == null ) {
			Player playerDb = null;
			try {
				playerDb = playerService.getPlayerByUserGame(user,game);
				if (playerDb != null){
					playerService.saveOfUpdate(playerDb);
				}

			}catch (NoResultException n){
				Player player = new Player();
				player.setUser(user);
				player.setGame(game);
				player.setSign(move.getMove());
				playerService.saveOfUpdate(player);
			}
		}
	}

	public void createMoveAi (Game game,GamePoleDTO gPole,UserEntity ai,HttpServletRequest request){
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
		createMove(game,gPole,ai);
	}

	public String ai( GamePoleDTO gamePoleDTO) {
		Random random = new Random();
		String randomPole = null;
		List<Integer> free = new ArrayList<>();
		Map<Integer, String> map = gamePoleDTO.getgAll();
		for (int i = 0; i < EnumConstants.S * EnumConstants.S ; i++) {
			if(map.get(i) == null || "".equalsIgnoreCase(map.get(i)))
				free.add(i);
		}

		if (free.size() != 0) {
			int pole = random.nextInt(free.size());
			randomPole = String.valueOf(free.get(pole));
		}
		free.clear();

		return randomPole ;
	}

	public void checkWinner(String key,GamePoleDTO gamePoleDTO,Game game,HttpServletRequest request) {

LOG.debug("Started check winner");
		String winner = "";
//Вопрос как мне в winner записать результат одной из проверок, что бы передать потом на запись победителя в метод saveWinDb()?

		// проверка горизонтали
		winner = horizontalCheck(key, gamePoleDTO, game);

		//проверка вертикали
		winner = verticalCheck(key, gamePoleDTO, game);

		//проверка диагонали 0-4-8
		winner = diagonalCheck1(key, gamePoleDTO, game);

		//проверка диагонали 2-4-6
		winner = diagonalCheck2(key, gamePoleDTO, game);

		//проверка ничьей
		winner = drawCheck(gamePoleDTO, game);

//		Запись победителя:
		saveWinDb(winner, game, request);

	}

	public String horizontalCheck(String key,GamePoleDTO gamePoleDTO,Game game){
		String winner = "";
		for (int i = 0; i < EnumConstants.S * EnumConstants.S; i += EnumConstants.S) {
			if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
				winner = key;
				for (int j = 0; j < EnumConstants.S; j++) {
					if (!gamePoleDTO.getgAll().get(i + j).equalsIgnoreCase(key)) {
						winner = "";
						break;
					}
				}
			}else return winner;
		}
		return winner;
	}

	public String verticalCheck(String key,GamePoleDTO gamePoleDTO, Game game){
		String winner = "";
		for (int i = 0; i < EnumConstants.S * EnumConstants.S; i += EnumConstants.S) {
			if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")){
				winner = key;
				for (int j = 0; j < EnumConstants.S; j++) {
					if (!gamePoleDTO.getgAll().get((i / EnumConstants.S) + (j * EnumConstants.S)).equalsIgnoreCase(key)) {
						winner = "";
						break;
					}
				}
			}else return winner;
		}
		return winner;
	}

	public String diagonalCheck1(String key,GamePoleDTO gamePoleDTO, Game game){
		String winner = "";
		if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
			winner = key;
			for (int i = 0; i < EnumConstants.S * EnumConstants.S; i += EnumConstants.S + 1) {
				if (!gamePoleDTO.getgAll().get(i).equalsIgnoreCase(key)) {
					winner = "";
				}
			}
		}else return winner;

		return winner;
	}

	public String diagonalCheck2(String key,GamePoleDTO gamePoleDTO,Game game){
		String winner = "";
		if (game.getWinner() == null || game.getWinner().getLogin().equalsIgnoreCase("")) {
			winner = key;
			for (int i = EnumConstants.S - 1; i < (EnumConstants.S * EnumConstants.S) - 1; i += EnumConstants.S - 1) {
				if (!gamePoleDTO.getgAll().get(i).equalsIgnoreCase(key)) {
					winner = "";
				}
			}
		}else return winner;
		return winner;
	}

	public String drawCheck(GamePoleDTO gamePoleDTO, Game game){
		String winner = "";
		if (!gamePoleDTO.getgAll().containsValue("")&& "".equalsIgnoreCase(game.getWinner().getLogin())) {
			 winner = EnumConstants.STANDOFF;
		}
		return winner;
	}

	public void saveWinDb (String winner, Game game, HttpServletRequest request){
		if (!"".equalsIgnoreCase(winner)) {
			Game refGame = game;
			if (!"".equalsIgnoreCase(winner) && !EnumConstants.STANDOFF.equalsIgnoreCase(winner)) {
				UserEntity user = playerService.getPlayerByGameSign(game, winner).getUser();
				refGame.setWinner(user);
				refGame.setStatus(EnumConstants.COMPLETE);
				gameService.saveOfUpdate(refGame);
				String win = user.getLogin();
				request.setAttribute("win", win);
			} else {
				if (!"".equalsIgnoreCase(winner) && EnumConstants.STANDOFF.equalsIgnoreCase(winner)) {
					UserEntity n = userService.getUserByLogin(EnumConstants.STANDOFF);
					refGame.setWinner(n);
					refGame.setStatus(EnumConstants.COMPLETE);
					gameService.saveOfUpdate(refGame);
					request.setAttribute("win", "label.winNO");
				}
			}
		}
	}
}
