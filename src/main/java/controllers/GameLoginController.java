package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameLoginController extends ExceptionHandlerController {

	@RequestMapping(value = "/gamelogin", method = RequestMethod.GET)
	public String viewGameLoginPage(){
		return "pages/gameLogin";
	}
}
