package com.example.SpringBootLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootLogin.model.User;
import com.example.SpringBootLogin.service.EmailSenderService;
import com.example.SpringBootLogin.service.UserService;

@Controller
@RequestMapping
public class RegistrationController {
	
	UserService userService;
	
	EmailSenderService senderService;
	@Autowired
	public void setSenderService(EmailSenderService senderService) {
		this.senderService = senderService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String showPage() {
		return "login.html";
	}
	@GetMapping("/register")
	public String showRegistrationPage() {
		return "register.html";
	}
	
	@PostMapping("/registration")
	public String register(@RequestParam(name = "first_name") String firstName,
			@RequestParam(name = "last_name") String lastName,User user) {
		user.setFirstName(firstName);
		user.setLastName(lastName);
		userService.addUser(user);
		String body = "Congratulations!! you have registered to this web application";
		String subject = "Registration Complete";
		senderService.sendMessage(user.getEmail(), body, subject);
		return "redirect:/login.html";
	}
}
