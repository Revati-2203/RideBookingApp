package com.project.bookRide.app.services;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.SignupDto;
import com.project.bookRide.app.dto.UserDto;

public interface AuthService {

	String[] login(String email, String password);
	
	UserDto signup(SignupDto signupDto);
	
	DriverDto onboradNewDriver(Long userId, String vehicalId);
}
