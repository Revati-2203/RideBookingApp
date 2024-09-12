package com.project.bookRide.app.controllers;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RatingDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.dto.RideStartDto;
import com.project.bookRide.app.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
	
	private final DriverService driverService;
	
	@PostMapping("/acceptRide/{rideRequestId}")
	public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
		return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
	}
	
	@PostMapping("/startRide/{rideRequestId}")
	public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto){
		return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
	}
	
	@PostMapping("/endRide/{rideRequestId}")
	public ResponseEntity<RideDto> endRide(@PathVariable Long rideRequestId){
		return ResponseEntity.ok(driverService.endRide(rideRequestId));
	}
	
	@PostMapping("/cancelRide/{rideId}")
	public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
		return ResponseEntity.ok(driverService.cancelRide(rideId));
	};
	
	@PostMapping("/rateRider")
	public ResponseEntity<RideDto> rateRider(@RequestBody RatingDto ratingDto){
		return ResponseEntity.ok(driverService.rateRider(ratingDto.getRideId(), ratingDto.getRating()));
	}
	
	@GetMapping("/getMyProfile")
	public ResponseEntity<DriverDto> getMyProfile(){
		return ResponseEntity.ok(driverService.getMyProfile());
	}
	
	@GetMapping("/getAllMyRides")
	public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
														@RequestParam(defaultValue = "10", required = false) Integer pageSize){
		PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, Sort.by(Direction.DESC, "createdAt", "id"));
		return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
	}
}
