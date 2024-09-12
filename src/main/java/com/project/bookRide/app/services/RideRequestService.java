package com.project.bookRide.app.services;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.RideRequest;

@Service
public interface RideRequestService {
	
	RideRequest findRideRequestById(Long rideRequestId);
	
	void update(RideRequest rideRequest);
}
