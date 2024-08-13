package com.project.bookRide.app.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.project.bookRide.app.entities.enums.TransactionMethod;
import com.project.bookRide.app.entities.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class WalletTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	private TransactionType transactionType;
	
	private TransactionMethod transactionMethod;
	
	@OneToOne
	private Ride ride;
	
	private String transactionId;
	
    @ManyToOne
    private Wallet wallet;
	
	@CreationTimestamp
	private LocalDateTime timeStamp;
}
