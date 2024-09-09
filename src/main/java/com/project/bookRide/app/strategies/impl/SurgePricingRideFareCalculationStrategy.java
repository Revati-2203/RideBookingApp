package com.project.bookRide.app.strategies.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.services.DistanceService;
import com.project.bookRide.app.strategies.RideFareCalculationStrategy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurgePricingRideFareCalculationStrategy implements RideFareCalculationStrategy {

	private final DistanceService distanceService;
	private static final double SURGE_FACTOR = 2; 
	@Override
	public double calculateFare(RideRequest rideRequest) {
		Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
		
		return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
	}

}
