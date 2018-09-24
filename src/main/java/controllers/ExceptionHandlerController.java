package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public abstract class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public String handlerException(Exception ex, Model model){
		model.addAttribute("ex", ex);
		return "/pages/error/exceptionPage";
	}
}
