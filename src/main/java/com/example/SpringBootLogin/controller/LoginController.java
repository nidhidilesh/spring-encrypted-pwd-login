package com.example.SpringBootLogin.controller;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringBootLogin.DTO.UserDTO;
import com.example.SpringBootLogin.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public String login(UserDTO userDTO) {
		try {
			if(userService.login(userDTO)!=null) {
				return "redirect:/home.html";
			}
			return "redirect:/invalid.html";
		} catch (CredentialNotFoundException e) {
			// TODO Auto-generated catch block
			return "redirect:/invalid.html";
		}
	}
}
