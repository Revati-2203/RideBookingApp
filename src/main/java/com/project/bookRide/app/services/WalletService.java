package com.project.bookRide.app.services;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.Wallet;
import com.project.bookRide.app.entities.enums.TransactionMethod;

@Service
public interface WalletService {
	
	Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
	
	Wallet deductMoneyfromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
	
	void withrawAllMyMoneyFromWallet();
	
	Wallet findWalletById(Long walletId);
	
	Wallet createNewWallet(User user);
	
	Wallet findByUser(User user);
}
