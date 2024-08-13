package com.project.bookRide.app.strategies;

import com.project.bookRide.app.entities.RideRequest;

public interface RideFareCalculationStrategy {
	
	double RIDE_FARE_MULTIPLIER = 10;
	double calculateFare(RideRequest rideRequest);
}
