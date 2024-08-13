package com.project.bookRide.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.enums.RideStatus;

public interface RideService {
	
	Ride getRideById(Long Ride);
	
	void matchWithDrivers(RideRequestDto rideRequestDto);
	
	Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);
	
	Ride updateRideStatus(Long rideId, RideStatus rideStatus);
	
	Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);
	
	Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
