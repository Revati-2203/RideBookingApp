package com.project.bookRide.app.dto;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import com.project.bookRide.app.entities.enums.PaymentMethod;
import com.project.bookRide.app.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
	private Long id;
	private PointDto pickupLocation;
	private PointDto dropOffLocation;
	private LocalDateTime requestedTime;
	private RiderDto rider;
	private PaymentMethod paymentMethod;
	private RideRequestStatus rideRequestStatus;
	
	

}
