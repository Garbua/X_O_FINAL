package controllers;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.user.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping()
public class LoginController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String outUser(Model model){
		model.addAttribute("user",new UserEntity());
		return "pages/index";
	}

	@RequestMapping(value = "/check")
	public String checkUser(HttpSession session, @Valid @ModelAttribute("user") UserEntity user,
	                        BindingResult result, Model model){

		if(result.hasErrors()){
			return "pages/index";

		}else{
			if(userService.loginExists(user.getLogin())){
				if(userService.passwordCorrect(user.getPassword(),user.getLogin())){
					if(session.getAttribute("user")== null){
						session.setAttribute("user", user);
						return "redirect:/gameLogin";
					}
				}

			}
		}
		return "pages/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayHome(Model model){
		model.addAttribute("user", new UserEntity());
		return "pages/index";
	}
}
