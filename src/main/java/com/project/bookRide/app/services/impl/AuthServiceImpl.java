  package com.project.bookRide.app.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.SignupDto;
import com.project.bookRide.app.dto.UserDto;
import com.project.bookRide.app.entities.Rider;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.enums.Role;
import com.project.bookRide.app.exceptions.RuntimeConflictException;
import com.project.bookRide.app.repositories.UserRepository;
import com.project.bookRide.app.services.AuthService;
import com.project.bookRide.app.services.RiderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RiderService riderService;
	@Override
	public String login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public UserDto signup(SignupDto signupDto) {
		User user =userRepository.findByEmail(signupDto.getEmail()).orElse(null);
		if(user!=null) {
			throw new RuntimeConflictException("Connot find the user");
		}
		
		user = modelMapper.map(signupDto,User.class);
		user.setRoles(Set.of(Role.RIDER));
		User savedUser = userRepository.save(user);
		
		// create user related entities
		riderService.createNewRider(savedUser);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onboradNewDriver(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
