package com.project.bookRide.app.services.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.WalletTransaction;
import com.project.bookRide.app.repositories.WalletTransactionRepository;
import com.project.bookRide.app.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService{

	private final WalletTransactionRepository walletTransactionRepository;
	
	
	@Override
	public void createNewWalletTransaction(WalletTransaction walletTransaction) {
		walletTransactionRepository.save(walletTransaction);
	}

}
