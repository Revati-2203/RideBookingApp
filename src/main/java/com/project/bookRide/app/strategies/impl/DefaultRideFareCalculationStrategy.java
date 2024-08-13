package com.project.bookRide.app.strategies.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.services.DistanceService;
import com.project.bookRide.app.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultRideFareCalculationStrategy implements RideFareCalculationStrategy{
	
	private final DistanceService distanceService;
	@Override
	public double calculateFare(RideRequest rideRequest) {
		Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
		
		return distance*RIDE_FARE_MULTIPLIER;
	}

}
