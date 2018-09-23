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


	public String regValidDb(Model model, UserEntity userEntity){
		if(userService.loginExists(userEntity.getLogin())){
			model.addAttribute("reg_very", "Логин уже существует!");
			return "pages/registrationPage";
		}else {
			if(userService.emailExists(userEntity.getEmail())){
				model.addAttribute("reg_very", "Пароль уже существует!");
				return "pages/registrationPage";
			}else {
				try{
					model.addAttribute("reg_very", String.format("Пользователь с логином <b>%s </b>" +
							"был создан", userEntity.getLogin() ));
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
