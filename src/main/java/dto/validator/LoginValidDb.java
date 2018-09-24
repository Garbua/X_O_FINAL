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

	/**
	 * Метод проверки существует ли пользователь и его пароль в БД
	 * @param model - записываются сообщения о том что пользователь не найден или пароль не найден
	 * @param session - записывается в сессию пользователь и его пароль
	 * @param userDTO - объект с полями: пользователь и пароль
	 * @return - если пользователя и его пароля нет в БД - страницу авторизации, если есть в БД - на страницу профиля
	 */
	public String validLoginDb(Model model, HttpSession session,UserDTO userDTO){
		if (userService.loginExists(userDTO.getLogin())) {

			if (userService.passwordCorrect(userDTO.getPassword(), userDTO.getLogin())) {

				if (session.getAttribute("userDTO") == null) {
					session.setAttribute("userDTO", userDTO);
					return "/pages/gameLogin";
				}
				model.addAttribute("form_error", "0");
				return "pages/gameLogin";
			} else {
				model.addAttribute("form_error", "1");
				return "pages/index";
			}
		} else {
			model.addAttribute("form_error", String.format("2",
					userDTO.getLogin()));
			return "pages/index";
		}
	}
}
