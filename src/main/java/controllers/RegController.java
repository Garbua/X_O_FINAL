package controllers;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class RegController {

	@Autowired
	UserService userService;


	@ResponseBody
	@RequestMapping(value = "/registr", method = RequestMethod.POST)
	public String Registration(Model model,
	                           @Valid @ModelAttribute("user_info") UserEntity userEntity, BindingResult result) {
		if(result.hasErrors()){
			return "pages/registrationPage";
		}else {
			if(userService.loginExists(userEntity.getLogin())){
				return "pages/registrationPage";
			}else {
				if(userService.emailExists(userEntity.getEmail())){
					return "pages/registrationPage";
				}else {
					try{
						userService.createUser(userEntity);
					}catch (Exception e){
						e.getStackTrace();
					}
					return "pages/index";
				}
			}
		}
	}


	@RequestMapping(value = "/registr", method = RequestMethod.GET)
	public String displayUser(Model model){

		model.addAttribute("user_info",new UserEntity());

		return "pages/registrationPage";
	}
}
