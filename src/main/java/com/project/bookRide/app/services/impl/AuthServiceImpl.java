package com.project.bookRide.app.services.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.SignupDto;
import com.project.bookRide.app.dto.UserDto;
import com.project.bookRide.app.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto signup(SignupDto signupDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDto onboradNewDriver(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
