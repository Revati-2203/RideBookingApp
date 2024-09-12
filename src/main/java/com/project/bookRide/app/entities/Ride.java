package com.project.bookRide.app.entities;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;
import com.project.bookRide.app.entities.enums.PaymentMethod;
import com.project.bookRide.app.entities.enums.RideStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {
		@Index(name = "idx_rider", columnList = "rider_id"),
		@Index(name = "idx_driver_id", columnList = "driver_id")
})
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(columnDefinition = "Geometry(Point, 4326)")
	private Point pickupLocation;
	
	@Column(columnDefinition = "Geometry(Point, 4326)")
	private Point dropOffLocation;  
	
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Rider rider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Driver driver;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private RideStatus rideStatus;
	
	private String otp;
	
	private Double fare;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
	
	
}
