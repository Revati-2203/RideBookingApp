package com.project.bookRide.app.strategies;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.project.bookRide.app.strategies.impl.DefaultRideFareCalculationStrategy;
import com.project.bookRide.app.strategies.impl.HighestRatedDriverMatchingStrategy;
import com.project.bookRide.app.strategies.impl.NearestDriverMatchingStrategy;
import com.project.bookRide.app.strategies.impl.SurgePricingRideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StrategyManager {
	
	private final HighestRatedDriverMatchingStrategy highestRatedDriverMatchingStrategy;
	private final NearestDriverMatchingStrategy nearestDriverMatchingStrategy;
	private final SurgePricingRideFareCalculationStrategy surgePricingRideFareCalculationStrategy;
	private final DefaultRideFareCalculationStrategy defaultRideFareCalculationStrategy;
	
	public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
		if(riderRating >= 4.8) {
			return highestRatedDriverMatchingStrategy;
		}else {
			return nearestDriverMatchingStrategy;
		}
	}
	
	public RideFareCalculationStrategy rideFareCalculationStrategy() {
		//6pm to 9pm
		LocalTime surgeStartTime =  LocalTime.of(18, 0);
		LocalTime surgeEndTime = LocalTime.of(21, 0);
		LocalTime currentTime = LocalTime.now();
		
		boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
		
		if(isSurgeTime) {
			return surgePricingRideFareCalculationStrategy;
		}else {
			return defaultRideFareCalculationStrategy;
		}
	}

}
