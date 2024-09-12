package com.project.bookRide.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{
	
	Optional<Wallet> findByUser(User user);
}
