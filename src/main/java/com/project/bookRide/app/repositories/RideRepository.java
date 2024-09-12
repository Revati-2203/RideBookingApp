package com.project.bookRide.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.Rider;

public interface RideRepository extends JpaRepository<Ride, Long>{

	Page<Ride> findByRider(Rider rider, Pageable pageRequest);

	Page<Ride> findByDriver(Driver driver, Pageable pageRequest);

}
