package com.project.bookRide.app.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.dto.RideRequestDto;
import com.project.bookRide.app.dto.RiderDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.entities.Rider;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.enums.RideRequestStatus;
import com.project.bookRide.app.entities.enums.RideStatus;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.RideRequestRepository;
import com.project.bookRide.app.repositories.RiderRepository;
import com.project.bookRide.app.services.DriverService;
import com.project.bookRide.app.services.RideService;
import com.project.bookRide.app.services.RiderService;
import com.project.bookRide.app.strategies.DriverMatchingStrategy;
import com.project.bookRide.app.strategies.RideFareCalculationStrategy;
import com.project.bookRide.app.strategies.StrategyManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
	
	private final ModelMapper modelMapper;
	private final StrategyManager strategyManager;
	private final RideRequestRepository rideRequestRepository;
	private final RiderRepository riderRepository;
	private final RideService rideService;
	private final DriverService driverService;
	
	
	@Override
	@Transactional
	public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

		log.info(rideRequestDto.toString());
		Rider rider = getCurrentRider();
		RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);

		rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
		rideRequest.setRider(rider);
		Double fare = strategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
		rideRequest.setFare(fare);

		RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
		List<Driver> drivers = strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);
		
		log.info(modelMapper.map(savedRideRequest, RideRequestDto.class).toString());
		return modelMapper.map(savedRideRequest, RideRequestDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		Rider rider = getCurrentRider();

		if(!rider.equals(ride.getRider())) {
			throw new RuntimeException("Rider does not own ride with id :"+rideId);
			
		}
		
		if(!ride.getRideStatus().equals(RideStatus.ONGOING)) {
			throw new RuntimeException("Ride started");
		}
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
		driverService.updateDriverAvailibility(ride.getDriver(), false);
		
		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	public DriverDto rateDriver(Long rideId, Integer rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RiderDto getMyProfile() {
		Rider rider = getCurrentRider();
		return modelMapper.map(rider, RiderDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Rider rider = getCurrentRider();
		return rideService.getAllRidesOfRider(rider, pageRequest).map(
				ride -> modelMapper.map(ride, RideDto.class));
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
		log.info("rider - GETTING");
		return riderRepository.findById(1L).orElseThrow(()-> 
					new ResourceNotFoundException("Rider not found with id: "+1));
	}

}
