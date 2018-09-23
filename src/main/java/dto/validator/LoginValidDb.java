package dto.validator;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import service.UserService;

import javax.servlet.http.HttpSession;

@Component
public class LoginValidDb {

	@Autowired
	private UserService userService;


	public String validLoginDb(Model model, HttpSession session,UserDTO userDTO){
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
			model.addAttribute("form_error", String.format("Пользователь <b>%s </b> не найден!",
					userDTO.getLogin()));
			return "pages/index";
		}
	}
}
