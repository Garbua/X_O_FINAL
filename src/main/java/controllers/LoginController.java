package controllers;
import dto.UserDTO;
import dto.validator.LoginValid;
import dto.validator.LoginValidDb;
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

	@Autowired
	private LoginValid loginValid;

	@Autowired
	private LoginValidDb loginValidDb;


	@RequestMapping(method = RequestMethod.GET)
	public String outUser(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		return "pages/index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String checkUser(Model model, HttpSession session, @Valid @ModelAttribute("userDTO") UserDTO userDTO,
	                        BindingResult result ) {

		loginValid.validate(userDTO, result);

		if (result.hasErrors()) {
			return "pages/gindex";

		} else {
			return loginValidDb.validLoginDb(model, session, userDTO);

		}

	}
}

