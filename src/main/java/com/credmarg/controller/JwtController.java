package com.credmarg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.model.JwtRequest;
import com.credmarg.model.JwtResponse;
import com.credmarg.service.JwtService;

@RestController
public class JwtController {

	@Autowired
	private JwtService jwtService;

	@PostMapping("/authentication")

	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) {

		return jwtService.createJwtToken(jwtRequest);
	}

	@GetMapping("/authentications")
	public String test() {
		return "Working fine";
	}
}
