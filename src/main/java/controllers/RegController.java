package controllers;
import entity.UserEntity;
import entity.validator.RegValidatorDb;
import entity.validator.UserEntityValidator;
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
public class RegController extends ExceptionHandlerController {

	@Autowired
	private UserEntityValidator regValidator;

	@Autowired
	private RegValidatorDb regValidatorDb;

	/**
	 * Метод POST регистрации нового пользователя
	 * @param model
	 * @param userEntity - объект нового пользователя с заполненными полями
	 * @param result - содержит ошибки валидации
	 * @return - если есть ошибки - вернуть обратно на страницу регистрации,
	 * если нет - передать в метод валидации введённые данные для проверки .
	 */
	@RequestMapping(method = {RequestMethod.POST})
	public String Registration(Model model,@Valid @ModelAttribute("user_info") UserEntity userEntity,
	                           BindingResult result) {

		regValidator.validate(userEntity, result);

		if(result.hasErrors()){
			return "pages/registrationPage";
		}else {
			return regValidatorDb.regValidDb(model,userEntity);
		}
	}

	/**
	 * Метод GET метода регистрации
	 * @param model - объект нового пользователя
	 * @return - страница регистрации
	 */
	@RequestMapping( method = RequestMethod.GET)
	public String displayUser(Model model){

		model.addAttribute("user_info",new UserEntity());

		return "pages/registrationPage";
	}
}
