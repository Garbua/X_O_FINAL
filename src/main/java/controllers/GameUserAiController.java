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
import service.GameService;
import service.MoveService;
import service.PlayerService;
import service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
public class GameUserAiController extends ExceptionHandlerController {


	@Autowired
	private GameService gameService;


	@Autowired
	private MoveService moveService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserService userService;



	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model){

		GamePoleDTO gamePoleDTO = new GamePoleDTO();

		if(gameService.getGameByStatus("Started") == null) {
			String s = StatusGame.valueOf("Started").getName();
			Game game = new Game();
			game.setStatus(s);
			gameService.createGame(game);
		}else {
			displayGame(gameService.getGameByStatus("Started"), gamePoleDTO);
		}

		model.addAttribute("gKey", gamePoleDTO);
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(HttpServletRequest request, HttpSession session,@Valid@ModelAttribute("gKey")
			GamePoleDTO gPole, BindingResult result, Model model){

		if(!result.hasErrors()) {
			Game game = gameService.getGameByStatus(StatusGame.valueOf("Started").getName());
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			UserEntity user = userService.getUserByLogin(userDTO.getLogin());

			MoveEntity move = new MoveEntity();
			createMove(move,game,gPole,user);

			if ( game.getWinner().getLogin() == null ) {
				Player player = new Player();
				player.setUser(user);
				player.setGame(game);
				player.setSign(move.getMove());
				playerService.createPlayer(player);
			}
		}else {
			System.out.println("ERROR!!! VALID");
			System.out.println(result.toString());
		}

		return "pages/aiGame";
	}


	public void createMove(MoveEntity move, Game game,GamePoleDTO gPole, UserEntity user){
		move.setGame(game);

		if(moveService.getCountPoleDb(game) != 0){
			System.out.println("pole != 0");
			for (int i = 0; i < 9; i++) {
				String mv = gPole.getgAll().get(i);
				if ("X".equalsIgnoreCase(mv) && mv != null) {
					for (MoveEntity m : moveService.getMoveByGame(game)) {
							if (!m.getPole().equalsIgnoreCase(String.valueOf(i))){
								move.setPole(String.valueOf(i));
								move.setMove("X");
								move.setUser(user);
								moveService.createMove(move);
							}
						}
					}
				}
		}else {
			System.out.println("pole == 0");
			for (int i = 0; i < 9; i++){
				String p = gPole.getgAll().get(i);
				if("X".equalsIgnoreCase(p) && p != null){
					move.setPole(String.valueOf(i));
					move.setMove("X");
					move.setUser(user);
					moveService.createMove(move);
				}
			}
		}

//		move.setUser(user);
//		moveService.createMove(move);
	}

	public void displayGame(Game game, GamePoleDTO gamePoleDTO) {
		Map<Integer, String> gPole2 = new HashMap<>();
		for (MoveEntity m : moveService.getMoveByGame(game)) {
			gPole2.put(Integer.valueOf(m.getPole()), m.getMove());
		}
		gamePoleDTO.setgAll(gPole2);
	}
}
