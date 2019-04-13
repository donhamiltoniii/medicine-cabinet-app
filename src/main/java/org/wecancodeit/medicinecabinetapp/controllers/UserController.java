package org.wecancodeit.medicinecabinetapp.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.medicinecabinetapp.base.classes.Login;
import org.wecancodeit.medicinecabinetapp.base.classes.User;
import org.wecancodeit.medicinecabinetapp.exceptions.UserNotFoundException;
import org.wecancodeit.medicinecabinetapp.repositories.UserRepository;

@Controller
public class UserController {
	
	@Resource 
	private UserRepository userRepo;
	
	@Resource
	private User user;
	
	@RequestMapping("/user")
	public String findOneUser(@RequestParam(value="id") long id, Model model)throws UserNotFoundException{
		Optional<User> user = userRepo.findById(id);


		if(user.isPresent()) {
			model.addAttribute("users", user.get());
			return "user";
			
		}
		
		throw new UserNotFoundException();
		
		}

	public User validateUser(Login login) {
		 return user;
	}
	
}


