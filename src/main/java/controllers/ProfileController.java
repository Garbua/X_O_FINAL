package controllers;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(Model model, HttpSession session){
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		model.addAttribute("profile", userDTO);
		return "/pages/profile";
	}

	@RequestMapping(value = "/profileedit", method = RequestMethod.GET)
	public String editProfile(Model model, HttpSession session){
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		model.addAttribute("profileedit", userDTO);
		return "pages/profileEdit" ;
	}

	@RequestMapping(value = "/profileedit", method = RequestMethod.POST)
	public String profileEditProcess(Model model, @ModelAttribute ("profileedit") UserEntity newUser){
		userService.updateUser(newUser);
		model.addAttribute("profile", newUser);
		return "pages/profile";
	}


	@RequestMapping(value = "/deleteuser",method = RequestMethod.GET)
	public String deleteUser(Model model, HttpSession session){
		UserDTO user = (UserDTO) session.getAttribute("userDTO");
		userService.deleteUser(user);
		session.invalidate();
		model.addAttribute("userDTO", new UserDTO());
		return "pages/index";
	}
}
