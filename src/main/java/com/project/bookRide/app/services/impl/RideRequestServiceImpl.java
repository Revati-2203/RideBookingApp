package com.project.bookRide.app.services.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.RideRequest;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.RideRequestRepository;
import com.project.bookRide.app.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService{
	
	private final RideRequestRepository rideRequestRepository;
	@Override
	public RideRequest findRideRequestById(Long rideRequestId) {
		return rideRequestRepository.findById(rideRequestId)
				.orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: "+rideRequestId));
	}
	@Override
	public void update(RideRequest rideRequest) {
		RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
				.orElseThrow(()-> new ResourceNotFoundException("RideRequest not found with id : "+rideRequest.getId()));
		rideRequestRepository.save(rideRequest);
	}

}
