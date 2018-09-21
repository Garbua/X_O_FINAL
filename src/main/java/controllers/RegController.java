package controllers;

import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.UserService;

@Controller
@RequestMapping("/reg")
public class RegController {

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/create")
	public String create() {

		UserEntity u = new UserEntity();
		u.setEmail("denisgarbuza@yandex.ru");
		u.setLogin("Garbua");
		u.setPassword("0913356");
		userService.createUser(u);
		return "saved";
	}
}
