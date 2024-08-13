package com.project.bookRide.app.repositories;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.bookRide.app.entities.Driver;


//ST_DISTANCE(POINT1, POINT2)
//ST_DWITHIN(POINT1, 10000)

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{

	@Query(" SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distamce " +
			" FROM drivers as d " +
			"where available=true AND ST_DWithin(d,current_location, :pickupLocation, 10000) " +
			"ORDER BY distance "+
			"LIMIT 10")
	List<Driver> findMatchingDrivers(Point pickupLocation);

}
