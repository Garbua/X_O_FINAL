package validators.login;

import dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValid implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return UserDTO.class.isAssignableFrom(aClass);
	}

	/**
	 * Метод проверки введённых данных при авторизации
	 * @param o - объект для проверки с полями : пользователь и пароль
	 * @param errors - содержит сообщение о некорректно введённых данных
	 */
	@Override
	public void validate(Object o, Errors errors) {
	UserDTO userDTO = (UserDTO) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty",
				"Login must not be empty!");

		String login = userDTO.getLogin();
		if((login.length()) > 16){
			errors.rejectValue("login", "login.tooLong", "Login must not more than 16 characters.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty",
				"Password must not be empty!");

		String password = userDTO.getPassword();
		if((password.length()) < 6){
			errors.rejectValue("password", "password.tooLong",
					"The password must contain at least 6 characters.");
		}
	}
}
