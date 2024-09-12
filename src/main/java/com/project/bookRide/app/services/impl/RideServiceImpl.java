package com.project.bookRide.app.services.impl;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.entities.Rider;
import com.project.bookRide.app.entities.enums.RideRequestStatus;
import com.project.bookRide.app.entities.enums.RideStatus;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.RideRepository;
import com.project.bookRide.app.services.RideRequestService;
import com.project.bookRide.app.services.RideService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
	
	private final RideRepository rideRepository;
	private final RideRequestService rideRequestService;
	private final ModelMapper modelMapper;
	
	@Override
	public Ride getRideById(Long ride) {
		return rideRepository.findById(ride).orElseThrow(()-> 
			new ResourceNotFoundException("Ride not found with id : "+ride ) );
	}

	@Override
	public Ride createNewRide(RideRequest rideRequest, Driver driver) {
		rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
		Ride ride = modelMapper.map(rideRequest, Ride.class);
		ride.setRideStatus(RideStatus.CONFIRMED);
		ride.setDriver(driver);
		ride.setOtp(generateRandomOTP());
		ride.setId(null);
		
		rideRequestService.update(rideRequest);
		return rideRepository.save(ride);
	}
	
	private String generateRandomOTP() {
		Random random = new Random();
		int otpInt = random.nextInt(10000);
		return String.format("%04d", otpInt);
	}

	@Override
	public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
		ride.setRideStatus(rideStatus);
		return rideRepository.save(ride);
	}

	@Override
	public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
		return rideRepository.findByRider(rider, pageRequest);
	}

	@Override
	public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
		return rideRepository.findByDriver(driver, pageRequest);
	}

	
}
