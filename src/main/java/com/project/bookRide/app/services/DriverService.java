package com.project.bookRide.app.services;

import java.util.List;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;

public interface DriverService {
	
	RideDto acceptRide(Long rideId);
	
	RideDto cancelRide(Long rideId);
	
	RideDto startRide(Long rideId);
	
	RideDto endRide(Long rideId);
	
	RideDto rateRider(Long rideId, Integer rating);
	
	DriverDto getMyProfile();
	
	List<RideDto> getAllMyRides();
	
	
}
