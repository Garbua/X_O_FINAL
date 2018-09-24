package entity.validator;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import service.UserService;

@Component
public class RegValidatorDb {

	@Autowired
	private UserService userService;

	/**
	 * Метод проверки пользователя и почты на уникальность в БД
	 * @param model - записываюся сообщения о результатах проверки
	 * @param userEntity - объект с проверяемыми полями  нового пользователя
	 * @return - если проверка пройдена - на страницу авторизации для входа по логину и паролю,
	 * если не пройдена - обратно на страницу регистрации.
	 */
	public String regValidDb(Model model, UserEntity userEntity){
		if(userService.loginExists(userEntity.getLogin())){
			model.addAttribute("reg_very", "0");
			return "pages/registrationPage";
		}else {
			if(userService.emailExists(userEntity.getEmail())){
				model.addAttribute("reg_very", "1");
				return "pages/registrationPage";
			}else {
				try{
					model.addAttribute("reg_very", "success");
					model.addAttribute("userDTO", new UserDTO());
					userService.createUser(userEntity);
				}catch (Exception e){
					e.getStackTrace();
				}
				return "pages/index";
			}
		}
	}
}
