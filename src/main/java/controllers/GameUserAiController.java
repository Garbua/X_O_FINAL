package controllers;


import dao.GameDAO;
import entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameUserAiController extends ExceptionHandlerController {


	@Autowired
	private GameDAO gameDAO;

	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(){
		gameDAO.createGame(new Game("started"));
		return "pages/aiGame";
	}


	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(){
		return "pages/aiGame";
	}
}
