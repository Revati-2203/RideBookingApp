package com.project.bookRide.app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.dto.RiderDto;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.entities.enums.RideRequestStatus;
import com.project.bookRide.app.repositories.RideRequestRepository;
import com.project.bookRide.app.services.RiderService;
import com.project.bookRide.app.strategies.DriverMatchingStrategy;
import com.project.bookRide.app.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
	
	private final ModelMapper modelMapper;
	private final RideFareCalculationStrategy rideFareCalculationStrategy;
	private final DriverMatchingStrategy driverMatchingStrategy;
	private final RideRequestRepository rideRequestRepository;
	
	@Override
	public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
		RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
		
		Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
		rideRequest.setFare(fare);
		
		RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
		driverMatchingStrategy.findMatchingDrivers(rideRequest);
		
		
		log.info(rideRequest.toString());
		return modelMapper.map(savedRideRequest, RideRequestDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDto rateRider(Long rideId, Integer rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RiderDto getMyProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RideDto> getAllMyRides() {
		// TODO Auto-generated method stub
		return null;
	}

}
