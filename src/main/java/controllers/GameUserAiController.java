package controllers;

import dto.GamePoleDTO;
import dto.UserDTO;
import entity.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@Controller
public class GameUserAiController {
	@Autowired
	private GameService gameService;
	@Autowired
	private MoveService moveService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private UserService userService;
	private String winner = "";



	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model, HttpServletRequest request){
		GamePoleDTO gamePoleDTO = new GamePoleDTO();
		if(gameService.getGameByStatus("Started") == null) {
			String s = StatusGame.valueOf("Started").getName();
			Game game = new Game();
			game.setStatus(s);
			gameService.saveOfUpdate(game);
		}else {
			displayGame(gameService.getGameByStatus("Started"), gamePoleDTO);
		}
		request.setAttribute("idGame", gameService.getGameByStatus("Started").getId_game());

		model.addAttribute("gKey", gamePoleDTO);
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(HttpServletRequest request, @RequestParam ("idGame") long idGame, HttpSession session,
	                             @Valid@ModelAttribute("gKey") GamePoleDTO gPole, BindingResult result, Model model){
		if(!result.hasErrors()) {
//			Мой ход :
			Game game = gameService.getGameByID(idGame);
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			UserEntity user = userService.getUserByLogin(userDTO.getLogin());
			createMove(game,gPole,user);
//			Проверка победителя:
			checkWinner( "x",gPole,request);
//			Ход компьютера:
			UserEntity ai = userService.getUserByLogin("AI");
			createMoveAi(game,gPole, ai,request);
//			Проверка победителя:
			checkWinner( "o",gPole,request);

//			Запись победителя:
				Game refGame = game;
				switch (winner) {
					case "x":
						refGame.setWinner(user);
						refGame.setStatus(StatusGame.valueOf("Complete").getName());
						gameService.saveOfUpdate(refGame);
						request.setAttribute("win", "label.winPlayer");
						break;
					case "o":
						refGame.setWinner(ai);
						refGame.setStatus(StatusGame.valueOf("Complete").getName());
						gameService.saveOfUpdate(refGame);
						request.setAttribute("win", "label.winAI");
						break;
					case "n":
						UserEntity n = userService.getUserByLogin("Standoff");
						refGame.setWinner(n);
						refGame.setStatus(StatusGame.valueOf("Complete").getName());
						gameService.saveOfUpdate(refGame);
						request.setAttribute("win", "label.winNO");
						break;
					case "":
						break;
					default:
						break;
				}

			request.setAttribute("win", winner);
			winner = "";

		}else {
			System.out.println("ERROR!!! VALID");
			System.out.println(result.toString());
		}
		return "pages/aiGame";
	}


	public void createMove(Game game,GamePoleDTO gPole, UserEntity user){

		MoveEntity moveEntity = null;
		MoveEntity move = new MoveEntity();

	for (int i = 0; i < 9; i++) {
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
		gPoleNew.put(Integer.valueOf(rPole), "o");
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
		String randomPole = "";
		List<Integer> free = gamePoleDTO.getgAll().entrySet().stream().filter((p) -> p.getValue().equalsIgnoreCase("") ||
				p.getValue() ==null ).map((p) -> p.getKey()).collect(Collectors.toList());
		if ("".equals(winner) && (free.size() != 0)) {
			int pole = random.nextInt(free.size());
			randomPole = String.valueOf(pole);
		}
		free.clear();

		return randomPole ;
	}

	public String checkWinner(String key,GamePoleDTO gamePoleDTO, HttpServletRequest request) {

		// проверка горизонтали
		horizontalCheck(key, gamePoleDTO);

		//проверка вертикали
		verticalCheck(key, gamePoleDTO);

		//проверка диагонали 0-4-8
		diagonalCheck1(key, gamePoleDTO);

		//проверка диагонали 2-4-6
		diagonalCheck2(key, gamePoleDTO);

		//проверка ничьей
		drawCheck(gamePoleDTO);

		return winner;

	}

	public String horizontalCheck(String key,GamePoleDTO gamePoleDTO){
		for (int i = 0; i < 9; i += 3) {
			if ("".equals(winner)) {
				winner = key;
				for (int j = 0; j < 3; j++) {
					if (!gamePoleDTO.getgAll().get(i + j).equalsIgnoreCase(key)) {
						winner = "";
						break;
					}
				}
			}else return winner;
		}
		return winner;
	}

	public String verticalCheck(String key,GamePoleDTO gamePoleDTO){
		for (int i = 0; i < 9; i += 3) {
			if ("".equals(winner)){
				winner = key;
				for (int j = 0; j < 3; j++) {
					if (!gamePoleDTO.getgAll().get((i / 3) + (j * 3)).equalsIgnoreCase(key)) {
						winner = "";
						break;
					}
				}
			}else return winner;
		}
		return winner;
	}

	public String diagonalCheck1(String key,GamePoleDTO gamePoleDTO){
		if ("".equals(winner)) {
			winner = key;
			for (int i = 0; i < 9; i += 3 + 1) {
				if (!gamePoleDTO.getgAll().get(i).equalsIgnoreCase(key)) {
					winner = "";
				}
			}
		}else return winner;
		return winner;
	}

	public String diagonalCheck2(String key,GamePoleDTO gamePoleDTO){
		if ("".equals(winner)) {
			winner = key;
			for (int i = 3 - 1; i < 8; i += 2) {
				if (!gamePoleDTO.getgAll().get(i).equalsIgnoreCase(key)) {
					winner = "";
				}
			}
		}else return winner;
		return winner;
	}

	public String drawCheck(GamePoleDTO gamePoleDTO){
		if (!gamePoleDTO.getgAll().containsValue("")&& "".equalsIgnoreCase(winner)) {
			return winner = "n";
		}
		return winner;
	}
}
