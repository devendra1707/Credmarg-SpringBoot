package com.credmarg.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.model.User;
import com.credmarg.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User create(@RequestBody User user) {
		return userService.create(user);
	}

	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {

		return userService.getCurrentUser(principal);
	}

}
