package validators.profile;

import entity.UserEntity;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserEntityValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return UserEntity.class.isAssignableFrom(aClass);
	}

	/**
	 * Метод проверки введённых данных нового пользователя на корректность
	 * @param o - объект нового пользователя
	 * @param errors - сообщения о некорректно введённых данных
	 */
	@Override
	public void validate(Object o, Errors errors) {
		UserEntity userEntity = (UserEntity) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty",
				"Login must not be empty!");
		String login = userEntity.getLogin();
		if((login.length()) > 16){
			errors.rejectValue("login", "login.tooLong", "Login must not more than 16 characters.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty",
				"Password must not be empty!");

		String password = userEntity.getPassword();
		if((password.length()) < 6){
			errors.rejectValue("password", "password.tooLong",
					"The password must contain at least 6 characters.");
		}

		if( !EmailValidator.getInstance().isValid( userEntity.getEmail() ) ){
			errors.rejectValue("email", "email.notValid", "Email address is not valid.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty",
				"FirstName must not be empty!");
		String firstName = userEntity.getFirstName();
		if((firstName.length()) > 16){
			errors.rejectValue("firstName", "firstName.tooLong", "FirstName must not more than 16 characters.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty",
				"LastName must not be empty!");
		String lastName = userEntity.getLastName();
		if((lastName.length()) > 16){
			errors.rejectValue("lastName", "lastName.tooLong", "LastName must not more than 16 characters.");
		}
	}
}
