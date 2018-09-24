package controllers;
import dto.UserDTO;
import dto.validator.LoginValid;
import dto.validator.LoginValidDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value ="/login")
public class LoginController {

	@Autowired
	private LoginValid loginValid;

	@Autowired
	private LoginValidDb loginValidDb;

	/**
	 * Метод GET страницы авторизации
	 * @param model - пустой объект (логин и пароль)
	 * @return - страница авторизации
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String outUser(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "pages/index";
	}

	/**
	 * Метод POST страницы авторизации
	 * @param model
	 * @param session - запись логина ипароля в сессию
	 * @param userDTO - объект с логином и паролем введённые пользователем
	 * @param result - ошибки при валидации введённых данных
	 * @return - если есть ошибки - возврат на страницу авторизации, если нет то передаёт работу валидатору БД.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String checkUser(Model model, HttpSession session, @Valid @ModelAttribute("userDTO") UserDTO userDTO,
	                        BindingResult result ) {

		loginValid.validate(userDTO, result);

		if (result.hasErrors()) {
			return "pages/index";

		} else {
			return loginValidDb.validLoginDb(model, session, userDTO);

		}

	}

	/**
	 * Метод выхода из профиля
	 * @param session - анулируется сессия
	 * @param model - создаётся пустой объект пользователя
	 * @return - возвращает на страницу авторизации
	 */
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("userDTO", new UserDTO());
		return "pages/index";
	}
}

