package com.project.bookRide.app.services.impl;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.project.bookRide.app.services.DistanceService;

import lombok.Data;

@Service
public class DistanceServiceOSRMImpl implements DistanceService{

	private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";
	
	@Override
	public double calculateDistance(Point src, Point dest) {
		try {
			String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
			ORSMResponseDto responseDto = RestClient.builder()
				.baseUrl(OSRM_API)
				.build()
				.get()
				.uri(uri)
				.retrieve()
				.body(ORSMResponseDto.class);
		
				return responseDto.getRoutes().get(0).getDistance()/1000.0;
				
		}catch (Exception e) {
			throw new RuntimeException("error getting data from OSRM "+e.getMessage());
		} 
	}
	
	@Data
	class ORSMResponseDto {
		private List<OSRMRoute> routes;
	}
	
	@Data
	class OSRMRoute {
		private Double distance;
	}
	
	

}
