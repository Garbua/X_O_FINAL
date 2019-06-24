package controllers;
import dto.ProfileDTO;
import dto.UserDTO;
import entity.UserEntity;
import org.apache.log4j.Logger;
import service.UserService;
import validators.login.LoginValid;
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
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginValid loginValid;

	@Autowired
	private UserService userService;

	/**
	 * Метод GET страницы авторизации
	 * @param model - пустой объект (логин и пароль)
	 * @return - страница авторизации
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String outUser(Model model) {
		model.addAttribute("profileDTO", new ProfileDTO());
		return "pages/index";
	}

	/**
	 * Метод POST страницы авторизации
	 * @param model
	 * @param session - запись логина ипароля в сессию
	 * @param profileDTO - объект с логином и паролем введённые пользователем
	 * @param result - ошибки при валидации введённых данных
	 * @return - если есть ошибки - возврат на страницу авторизации, если нет то передаёт работу валидатору БД.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String checkUser(Model model, HttpSession session, @Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO,
	                        BindingResult result ) {

		loginValid.validate(profileDTO, result);

		if (result.hasErrors()) {
			return "pages/index";

		} else if (userService.loginExists(profileDTO.getLogin())) {

			if (userService.passwordCorrect(profileDTO.getPassword(), profileDTO.getLogin())) {

				if (session.getAttribute("userDTO") == null) {
					UserEntity userEntity = userService.getUserByLogin(profileDTO.getLogin());
					UserDTO userDTO = new UserDTO();
					userDTO.setId(userEntity.getId());
					userDTO.setLogin(userEntity.getLogin());
					session.setAttribute("userDTO", userDTO);
					LOG.info("Create userDTO in session id:  "+ userDTO.getId());
					LOG.info("profileDTO_login :  "+ profileDTO.getLogin());
					return "/pages/gameLogin";
				}
				model.addAttribute("form_error", "label.error0");
				return "pages/gameLogin";
			} else {
				model.addAttribute("form_error", "label.error1");
				return "pages/index";
			}
		} else {
			model.addAttribute("form_error", "label.error2");
			return "pages/index";
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
		model.addAttribute("profileDTO", new ProfileDTO());
		return "pages/index";
	}
}

