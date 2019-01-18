package controllers;


import dto.UserDTO;
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
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

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
		Game game = new Game();
		String s = StatusGame.valueOf("Started").getName();
		game.setStatus(s);
		gameService.createGame(game);
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(Model model, HttpServletRequest request, HttpSession session){
		MoveEntity move = new MoveEntity();
		Game game = gameService.getGameByStatus(StatusGame.valueOf("Started").getName());
System.out.println(game.getId_game());
			move.setGame(game);
			move.setPole("0");
			move.setMove("X");
			UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
			System.out.println(userDTO.getLogin());
			UserEntity user = userService.getUserByLogin(userDTO.getLogin());
			System.out.println(user.getFirstName());
			move.setUser(user);
			moveService.createMove(move);

			Player player = new Player();
			player.setUser(user);
			player.setGame(game);
			player.setSign(move.getMove());
			playerService.createPlayer(player);
//			request.setAttribute("p" + i, map.get(i));

		return "pages/aiGame";
	}
}
