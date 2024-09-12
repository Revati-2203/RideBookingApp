package com.project.bookRide.app.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.Wallet;
import com.project.bookRide.app.entities.enums.TransactionMethod;
import com.project.bookRide.app.entities.enums.TransactionType;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletTransactionDto {
	
	private Long id;
	private Double amount;
	private TransactionType transactionType;
	private TransactionMethod transactionMethod;
	private RideDto ride;
	private String transactionId;
    private WalletDto wallet;
	private LocalDateTime timeStamp;
	
}
