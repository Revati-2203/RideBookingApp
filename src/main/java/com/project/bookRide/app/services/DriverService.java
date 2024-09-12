package com.project.bookRide.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.entities.Driver;

public interface DriverService {
	
	RideDto acceptRide(Long rideRequestId);
	
	RideDto cancelRide(Long rideId);
	
	RideDto startRide(Long rideId, String otp);
	
	RideDto endRide(Long rideId);
	
	RideDto rateRider(Long rideId, Integer rating);
	
	DriverDto getMyProfile();
	
	Page<RideDto> getAllMyRides(PageRequest pageRequest);
	
	Driver getCurrentDriver();
	
	Driver updateDriverAvailibility(Driver driver, boolean available);
	
	Driver createNewDriver(Driver driver);
}
