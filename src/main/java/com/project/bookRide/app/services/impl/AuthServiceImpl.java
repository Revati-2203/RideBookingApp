  package com.project.bookRide.app.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.SignupDto;
import com.project.bookRide.app.dto.UserDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.enums.Role;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.exceptions.RuntimeConflictException;
import com.project.bookRide.app.repositories.UserRepository;
import com.project.bookRide.app.services.AuthService;
import com.project.bookRide.app.services.DriverService;
import com.project.bookRide.app.services.RiderService;
import com.project.bookRide.app.services.WalletService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final RiderService riderService;
	private final WalletService walletService;
	private final DriverService driverService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public String[] login(String email, String password) {
	String tokens[] = new String[2];
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
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		
		// create user related entities
		riderService.createNewRider(savedUser);
		walletService.createNewWallet(savedUser);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public DriverDto onboradNewDriver(Long userId, String vehicalId) {
		User user = userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User not found with id : "+userId));
		
		if(user.getRoles().contains(Role.DRIVER)) {
				throw new RuntimeConflictException("Driver already exists with userId "+userId);
		}
		Driver driver = Driver.builder()
								.user(user)
								.vehicleId(vehicalId)
								.rating(0.0)
								.available(true)
								.build();
			user.getRoles().add(Role.DRIVER);
			userRepository.save(user);
		return modelMapper.map(driverService.createNewDriver(driver), DriverDto.class) ;
	}
	
}
