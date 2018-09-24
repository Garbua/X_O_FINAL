package controllers;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController extends ExceptionHandlerController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(Model model, HttpSession session){
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		UserEntity userEntity = userService.getUserByLogin(userDTO.getLogin());
		model.addAttribute("profile", userEntity);
		return "/pages/profile";
	}
}
