package com.project.bookRide.app.controllers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookRide.app.dto.SignupDto;
import com.project.bookRide.app.dto.UserDto;
import com.project.bookRide.app.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/signup")
	UserDto signup(@RequestBody SignupDto signupDto) {
		
		return authService.signup(signupDto);
	}
	
}
