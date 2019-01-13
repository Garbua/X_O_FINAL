package controllers;


import entity.StatusGame;
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



	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model){
		String s = StatusGame.valueOf("Started").getName();
		gameService.getGameByStatus(s);
		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(Model model, HttpServletRequest request){

		return "pages/aiGame";
	}
}
