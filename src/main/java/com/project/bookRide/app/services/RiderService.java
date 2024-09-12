package com.project.bookRide.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.dto.RiderDto;
import com.project.bookRide.app.entities.Rider;
import com.project.bookRide.app.entities.User;

public interface RiderService {
	
	RideRequestDto requestRide(RideRequestDto rideRequestDto);
	
	RideDto cancelRide(Long rideId);
	
	DriverDto rateDriver(Long rideId, Integer rating);
	
	RiderDto getMyProfile();
	
	Page<RideDto> getAllMyRides(PageRequest pageRequest);
	
	Rider createNewRider(User user);
	
	Rider getCurrentRider();
}
