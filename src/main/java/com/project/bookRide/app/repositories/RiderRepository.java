package com.project.bookRide.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookRide.app.entities.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long>{

}
