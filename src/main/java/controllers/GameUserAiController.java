package controllers;


import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.GameService;
import service.MoveService;
import service.PlayerService;
import service.UserService;
import javax.servlet.http.HttpServletRequest;

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

	private UserEntity user;
	private Game gameAi;
	private Player player;
	private MoveEntity move;
	private Integer pole = 3;

	@RequestMapping(value = "/aistartgame", method = RequestMethod.GET)
	public String startGameAi(){
		gameAi = new Game(StatusGame.Started.getName());
		gameService.createGame(gameAi);

		return "pages/aiGame";
	}


	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model){
		user = userService.getUserByLogin(user.getLogin());
		player = new Player();
		playerService.createPlayer(player);
		player.setGame(gameAi);
		player.setUser(user);
//		player.setSign(Signs.X.getName());
		player.setSign("X");
		gameAi.addPlayer(player);
		move = new MoveEntity();
		move.setUser(user);
		user.addMove(move);
		user.addPlayer(player);
		moveService.updateMove(move);
		playerService.updatePlayer(player);
		gameService.update(gameAi);
		userService.updateUser(user);

		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(Model model, HttpServletRequest request){
		for (int i = 0; i <= (pole * pole)-1 ; i++) {
			String param = String.valueOf(i);
			move.setGame(gameAi);
			gameAi.addMove(move);
			move.setPole(param);
			move.setMove(request.getParameter(param));
			move.setUser(user);
			user.addMove(move);
			moveService.updateMove(move);
			userService.updateUser(user);
			request.setAttribute("p" + i, request.getParameter(param));

		}
		return "pages/aiGame";
	}
}
