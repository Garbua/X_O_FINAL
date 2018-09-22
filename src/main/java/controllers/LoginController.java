package controllers;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value ="/login")
public class LoginController {

	@Autowired
	private UserService userService;


	@RequestMapping(method = RequestMethod.GET)
	public String outUser(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "pages/index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String checkUser(Model model, HttpSession session, @Valid @ModelAttribute("userDTO") UserDTO userDTO,
	                        BindingResult result ) {

		if (result.hasErrors()) {
			return "pages/index";

		} else {
			if (userService.loginExists(userDTO.getLogin())) {

				if (userService.passwordCorrect(userDTO.getPassword(), userDTO.getLogin())) {

					if (session.getAttribute("userDTO") == null) {
						session.setAttribute("userDTO", userDTO);
						return "pages/gameLogin";
					}
					model.addAttribute("form_error", "Попробуйте войти ещё раз!");
					return "pages/index";
				} else {
					model.addAttribute("form_error", "Неверный пароль!");
					return "pages/index";
				}
			} else {
				model.addAttribute("form_error", "Пользователь не найден!");
				return "pages/index";
			}
		}

	}
}

