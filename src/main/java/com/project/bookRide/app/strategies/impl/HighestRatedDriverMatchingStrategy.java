package com.project.bookRide.app.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.strategies.DriverMatchingStrategy;

@Service
public class HighestRatedDriverMatchingStrategy implements DriverMatchingStrategy{


	@Override
	public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
		// TODO Auto-generated method stub
		return  List.of();
	}

}
