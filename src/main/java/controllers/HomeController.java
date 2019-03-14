package controllers;

import dto.ProfileDTO;
import dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	/**
	 * Метод GET стартовой страницы
	 * @param model - объект с полями : логин и пароль
	 * @return - страница авторизации
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayHome(Model model) {
		model.addAttribute("profileDTO", new ProfileDTO());
		return "pages/index";
	}
}
