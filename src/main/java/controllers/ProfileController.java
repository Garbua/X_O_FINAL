package controllers;


import entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.profile.ProfileService;
import service.user.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProfileService profileService;


	@ResponseBody
	@RequestMapping(value = "/edit")
	public String profileEdit(){
		Profile profile = new Profile();
		profile.setAge(31);
		profile.setCity("Minsk");
		profile.setFhoneNumber("+375297578098");
		profile.setSex("male");
		profile.setEmail("denisgarbuza@yandex.ru");
		profile.setName("Denis");
		profile.setLastName("Harbuza");
		profileService.createProfile(profile);

		return "profile saved";
	}


}
