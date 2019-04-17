package org.wecancodeit.medicinecabinetapp.controllers;

import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wecancodeit.medicinecabinetapp.base.classes.User;
import org.wecancodeit.medicinecabinetapp.password.GenericResponse;
import org.wecancodeit.medicinecabinetapp.repositories.UserRepository;
import org.wecancodeit.medicinecabinetapp.service.SecurityService;
import org.wecancodeit.medicinecabinetapp.service.UserService;
import org.wecancodeit.medicinecabinetapp.validator.UserValidator;

@Controller
public class UserController {

	@Resource
	UserRepository userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "register";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUserName(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		return "welcome";
	}

	@RequestMapping("/add-user")
	public String addUser(String userName, String firstName, String lastName, String userPassword, String userPhone,
			String userEmail) {

		User newUser = userRepo.findByUserName(userName);

		if (newUser == null) {
			newUser = new User();
			userRepo.save(newUser);
		}

		return "redirect:/dashboard";
	}

	@RequestMapping("/delete-user")
	public String deleteUserByName(String userName) {

		if (userRepo.findByUserName(userName) != null) {
			User deletedUser = userRepo.findByUserName(userName);
			userRepo.delete(deletedUser);
		}
		return "redirect:/goodbye";

	}
	
	@RequestMapping(value = "/user/resetPassword", 
            method = RequestMethod.POST)
	  @ResponseBody
		public GenericResponse resetPassword(HttpServletRequest request, 
       @RequestParam("email") String userEmail) {
         User user = userService.findUserByEmail(userEmail);
          if (user == null) {
            throw new UserNotFoundException();
}
      String token = UUID.randomUUID().toString();
       userService.createPasswordResetTokenForUser(user, token);
         MailSender.send(constructResetTokenEmail(getAppUrl(request), 
          request.getLocale(), token, user));
         return new GenericResponse(
             messages.getMessage("message.resetPasswordEmail", null, 
                    request.getLocale()));
	}
	

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public String showChangePasswordPage(Locale locale, Model model, 
	  @RequestParam("id") long id, @RequestParam("token") String token) {
	    String result = securityService.validatePasswordResetToken(id, token);
	    if (result != null) {
	        model.addAttribute("message", 
	          messages.getMessage("auth.message." + result, null, locale));
	        return "redirect:/login?lang=" + locale.getLanguage();
	    }
	    return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
	}
}