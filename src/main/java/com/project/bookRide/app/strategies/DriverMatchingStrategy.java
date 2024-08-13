package com.project.bookRide.app.strategies;

import java.util.List;

import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.RideRequest;

public interface DriverMatchingStrategy {
	
	List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
