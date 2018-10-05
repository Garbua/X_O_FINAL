package controllers;

import entity.MoveEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameLoginController extends ExceptionHandlerController {

	@RequestMapping(value = "/gamelogin", method = RequestMethod.GET)
	public String viewGameLoginPage(){
		return "pages/gameLogin";
	}


	@RequestMapping(value = "/displaygame", method = RequestMethod.GET)
	public String displayGame(Model model){
		return "pages/nickGame";
	}

	@RequestMapping(value = "/displaygame", method = RequestMethod.POST)
	public String startGame(Model model){
		return "pages/nickGame";
	}
}
