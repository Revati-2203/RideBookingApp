package com.project.bookRide.app.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.bookRide.app.dto.DriverDto;
import com.project.bookRide.app.dto.RideDto;
import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.entities.enums.RideRequestStatus;
import com.project.bookRide.app.entities.enums.RideStatus;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.DriverRepository;
import com.project.bookRide.app.services.DriverService;
import com.project.bookRide.app.services.PaymentService;
import com.project.bookRide.app.services.RideRequestService;
import com.project.bookRide.app.services.RideService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{

	private final RideRequestService rideRequestService;
	private final DriverRepository driverRepository;
	private final RideService rideService;
	private final ModelMapper modelMapper;
	private final PaymentService paymentService;
	
	@Override
	public RideDto acceptRide(Long rideRequestId) {
		RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
		if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
			throw new RuntimeException("RideRequest cannot be accepted "+rideRequest.getRideRequestStatus());
		}
		Driver currentDriver = getCurrentDriver();
		if(!currentDriver.getAvailable()) {
			throw new RuntimeException("driver cannot be accept due to unavailibility");
		}
		Driver savedDriver = updateDriverAvailibility(currentDriver, false);
		Ride ride = rideService.createNewRide(rideRequest, savedDriver);
		
		return modelMapper.map(ride, RideDto.class);
	}

	@Override
	public RideDto cancelRide(Long rideId) {
		
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();

		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot cancel a ride as he has not accepted it earlier ");
		}

		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride cannot be cancel, status: "+ride.getRideStatus());	
		}
		rideService.updateRideStatus(ride,RideStatus.CANCELLED);
		updateDriverAvailibility(driver, true);
		
		return modelMapper.map(ride, RideDto.class);
	}

	@Override
	public RideDto startRide(Long rideId, String otp) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier ");
		}
		
		if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
			throw new RuntimeException("Ride status is not confirmed hence cannot be started, status: "+ride.getRideStatus());	
		}
		
		if(!otp.equals(ride.getOtp())) {
			throw new RuntimeException("Invalid OTP");	
		}
		
		ride.setStartedAt(LocalDateTime.now());
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
		
		paymentService.createNewPayment(savedRide);
		
		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	@Transactional
	public RideDto endRide(Long rideId) {
		Ride ride = rideService.getRideById(rideId);
		Driver driver = getCurrentDriver();
		
		if(!driver.equals(ride.getDriver())) {
			throw new RuntimeException("Driver cannot start a ride as he has not accepted it earlier ");
		}
		
		if(!ride.getRideStatus().equals(RideStatus.ONGOING)) {
			throw new RuntimeException("Ride status is not ONGOING hence cannot be ENDED, status: "+ride.getRideStatus());	
		}
		ride.setEndedAt(LocalDateTime.now());
		Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
		updateDriverAvailibility(driver, true);

		paymentService.processPayment(ride);
		
		return modelMapper.map(savedRide, RideDto.class);
	}

	@Override
	public RideDto rateRider(Long rideId, Integer rating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDto getMyProfile() {
		Driver driver = getCurrentDriver();
		return modelMapper.map(driver, DriverDto.class);
	}

	@Override
	public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
		Driver driver = getCurrentDriver();
		return rideService.getAllRidesOfDriver(driver,pageRequest).map(
				ride -> modelMapper.map(ride, RideDto.class));
	}

	@Override
	public Driver getCurrentDriver() {
		return driverRepository.findById(2L).orElseThrow(()->new ResourceNotFoundException("currentDriver not found with id "+2));
	}

	@Override
	public Driver updateDriverAvailibility(Driver driver, boolean available) {
		driver.setAvailable(available);
		 driverRepository.save(driver);
		 return driver;
		}

	@Override
	public Driver createNewDriver(Driver driver) {
		return driverRepository.save(driver);
	}

}
