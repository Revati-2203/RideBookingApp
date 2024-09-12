package com.project.bookRide.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.entities.Rider;
import com.project.bookRide.app.entities.enums.RideStatus;

public interface RideService {
	
	Ride getRideById(Long ride);

	Ride createNewRide(RideRequest rideRequest, Driver driver);
	
	Ride updateRideStatus(Ride ride, RideStatus rideStatus);
	
	Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);
	
	Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
