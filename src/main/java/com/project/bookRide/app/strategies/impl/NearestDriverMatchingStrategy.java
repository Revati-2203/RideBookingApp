package com.project.bookRide.app.strategies.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.repositories.DriverRepository;
import com.project.bookRide.app.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NearestDriverMatchingStrategy implements DriverMatchingStrategy{

	private final DriverRepository driverRepository;

	@Override
	public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
		return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
	}
	
	

}
