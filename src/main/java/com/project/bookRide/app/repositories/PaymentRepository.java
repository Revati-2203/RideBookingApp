package com.project.bookRide.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookRide.app.entities.Payment;
import com.project.bookRide.app.entities.Ride;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Optional<Payment> findByRide(Ride ride);

}
