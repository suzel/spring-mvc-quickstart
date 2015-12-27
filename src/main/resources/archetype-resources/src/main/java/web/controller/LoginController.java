package ${package}.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ${package}.domain.User;
import ${package}.form.SignupForm;
import ${package}.repository.UserRepository;
import ${package}.service.UserService;
import ${package}.web.support.MessageHelper;

@Controller
public class LoginController {

	// TODO : authenticate
	// TODO : lostpassword
	// TODO : reset

	private static final String SIGNIN_VIEW_NAME = "account/signin";
	private static final String SIGNUP_VIEW_NAME = "account/signup";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public String signin() {
		return SIGNIN_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute(new SignupForm());
		return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
		
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		
		User user = userRepository.save(signupForm.createUser());
		userService.signin(user);
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		
        return "redirect:/";
	}
	
	/*@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/";
	}*/

}