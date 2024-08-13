package com.project.bookRide.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {
	
	private final RiderService riderService;
	
	@PostMapping("/requestRide")
	public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
		
		return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
		
	}

}
