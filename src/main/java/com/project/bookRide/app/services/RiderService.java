package com.project.bookRide.app.services;

import java.util.List;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.dto.RiderDto;

public interface RiderService {
	
	RideRequestDto requestRide(RideRequestDto rideRequestDto);
	
	RideDto cancelRide(Long rideId);
	
	DriverDto rateRider(Long rideId, Integer rating);
	
	RiderDto getMyProfile();
	
	List<RideDto> getAllMyRides();
	
	
}
