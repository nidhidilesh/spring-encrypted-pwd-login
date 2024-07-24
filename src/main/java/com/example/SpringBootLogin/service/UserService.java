package com.example.SpringBootLogin.service;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.SpringBootLogin.DTO.UserDTO;
import com.example.SpringBootLogin.model.User;
import com.example.SpringBootLogin.repository.UserRepository;


@Service
public class UserService {
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

	UserRepository userRepo;
	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	public void addUser(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		userRepo.save(user);
	}
	public User login(UserDTO userDTO) throws CredentialNotFoundException {
		User user = userRepo.findByEmail(userDTO.getEmail());
		if(user!=null && bcrypt.matches(userDTO.getPassword(), user.getPassword())) {
			return user;
		}
		else {
			throw new CredentialNotFoundException("Not found");
		}
	}
}
