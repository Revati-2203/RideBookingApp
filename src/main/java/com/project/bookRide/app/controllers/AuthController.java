package com.project.bookRide.app.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.LoginRequestDto;
import com.project.bookRide.app.dto.LoginResponseDto;
import com.project.bookRide.app.dto.SignupDto;
import com.project.bookRide.app.dto.UserDto;
import com.project.bookRide.app.dto.OnBoardDriverDto;
import com.project.bookRide.app.services.AuthService;
import com.project.bookRide.app.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/signup")
	ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
		return new ResponseEntity<>(authService.signup(signupDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
		String[] tokens = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
		return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
	}
	
	@PostMapping("/onBoardDriver/{userId}")
	public ResponseEntity<DriverDto> onBoardDriver(@PathVariable Long userId, @RequestBody OnBoardDriverDto onBoardDriverDto){
		return new ResponseEntity<>(authService.onboradNewDriver(userId, onBoardDriverDto.getVehicalId()), HttpStatus.CREATED);
	}
	
}
