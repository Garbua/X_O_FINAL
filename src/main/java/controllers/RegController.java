package controllers;
import converter.ConverterImpl;
import dto.ProfileDTO;
import service.UserService;
import validators.profile.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class RegController {

	@Autowired
	private ProfileValidator regValidator;

	@Autowired
	private UserService userService;

	/**
	 * Метод POST регистрации нового пользователя
	 * @param model
	 * @param profileDTO - объект нового пользователя с заполненными полями
	 * @param result - содержит ошибки валидации
	 * @return - если есть ошибки - вернуть обратно на страницу регистрации,
	 * если нет - передать в метод валидации введённые данные для проверки .
	 */
	@RequestMapping(method = {RequestMethod.POST})
	public String Registration(Model model,@Valid @ModelAttribute("profileDTO") ProfileDTO profileDTO,
	                           BindingResult result) {

		regValidator.validate(profileDTO, result);

		if (result.hasErrors()) {
			return "pages/registrationPage";
		} else if (userService.loginExists(profileDTO.getLogin())) {
			model.addAttribute("reg_very", "label.reg.eslogin");
			return "pages/registrationPage";
		} else {
			if (userService.emailExists(profileDTO.getEmail())) {
				model.addAttribute("reg_very", "label.reg.esemail");
				return "pages/registrationPage";
			} else {
				try {
					model.addAttribute("reg_very", "label.reg.success");
					model.addAttribute("profileDTO", new ProfileDTO());
					userService.saveOfUpdate(new ConverterImpl().createFrom(profileDTO));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "pages/index";
			}
		}
	}

	/**
	 * Метод GET метода регистрации
	 * @param model - объект нового пользователя
	 * @return - страница регистрации
	 */
	@RequestMapping( method = RequestMethod.GET)
	public String displayUser(Model model){

		model.addAttribute("profileDTO",new ProfileDTO());

		return "pages/registrationPage";
	}
}
