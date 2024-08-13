package com.project.bookRide.app.dto;

import java.time.LocalDateTime;
import org.locationtech.jts.geom.Point;
import com.project.bookRide.app.entities.enums.PaymentMethod;
import com.project.bookRide.app.entities.enums.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {
	private Long id;
	private Point pickupLocation;
	private Point dropOffLocation;  
	private LocalDateTime createdTime;
	private RiderDto rider;
	private DriverDto driver;
	private PaymentMethod paymentMethod;
	private RideStatus rideStatus;
	private Double fare;
	private String otp;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
	
}
