package com.project.bookRide.app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.dto.RiderDto;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.entities.Rider;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.enums.RideRequestStatus;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.RideRequestRepository;
import com.project.bookRide.app.repositories.RiderRepository;
import com.project.bookRide.app.services.RiderService;
import com.project.bookRide.app.strategies.DriverMatchingStrategy;
import com.project.bookRide.app.strategies.RideFareCalculationStrategy;
import com.project.bookRide.app.strategies.StrategyManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
	
	private final ModelMapper modelMapper;
	private final RideFareCalculationStrategy rideFareCalculationStrategy;
	private final StrategyManager strategyManager;
	private final DriverMatchingStrategy driverMatchingStrategy;
	private final RideRequestRepository rideRequestRepository;
	private final RiderRepository riderRepository;
	
	@Override
	public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
		
		Rider rider = getCurrentRider();
		RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
		
		Double fare = strategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
		rideRequest.setFare(fare);
		
		RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
		strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
		
		
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

	@Override
	public Rider createNewRider(User user) {
		Rider rider = Rider.builder()
					.user(user)
					.rating(0.0)
					.build(); 
		return riderRepository.save(rider);
	}

	@Override
	public Rider getCurrentRider() {
		// TODO Auto-generated method stub
		return riderRepository.findById(1L).orElseThrow(()-> 
					new ResourceNotFoundException("Rider not found with id: "+1));
	}

}
