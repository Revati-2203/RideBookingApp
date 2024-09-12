package com.project.bookRide.app.services.impl;

import org.springframework.stereotype.Service;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.Wallet;
import com.project.bookRide.app.entities.WalletTransaction;
import com.project.bookRide.app.entities.enums.TransactionMethod;
import com.project.bookRide.app.entities.enums.TransactionType;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.WalletRepository;
import com.project.bookRide.app.services.WalletService;
import com.project.bookRide.app.services.WalletTransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{

	private final WalletRepository walletRepository;
	private final WalletTransactionService walletTransactionService;
	
	@Override
	@Transactional
	public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
		Wallet wallet = walletRepository.findByUser(user).orElseThrow(
				()-> new ResourceNotFoundException("Wallet not found with userId :"+user.getId()));
		wallet.setBalance(wallet.getBalance()+amount);
		
		WalletTransaction walletTransaction = WalletTransaction.builder()
													.transactionId(transactionId)
													.ride(ride)
													.transactionMethod(TransactionMethod.BANKING)
													.transactionType(TransactionType.CREDIT)
													.transactionMethod(transactionMethod)
													.amount(amount)
													.build();
		wallet.getTransactions().add(walletTransaction);
		return walletRepository.save(wallet);
	}
	
	@Override
	@Transactional
	public Wallet deductMoneyfromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
		Wallet wallet = walletRepository.findByUser(user).orElseThrow(
				()-> new ResourceNotFoundException("Wallet not found with userId : "+user.getId()));
		wallet.setBalance(wallet.getBalance()-amount);
		
		WalletTransaction walletTransaction = WalletTransaction.builder()
				.transactionId(transactionId)
				.ride(ride)
				.transactionMethod(TransactionMethod.BANKING)
				.transactionType(TransactionType.DEBIT)
				.transactionMethod(transactionMethod)
				.amount(amount)
				.build();
		walletTransactionService.createNewWalletTransaction(walletTransaction);

		return walletRepository.save(wallet);
	}

	@Override
	public void withrawAllMyMoneyFromWallet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Wallet findWalletById(Long walletId) {
		return walletRepository.findById(walletId).orElseThrow(
				()-> new ResourceNotFoundException("Wallet not found with id :"+walletId));
	}

	@Override
	public Wallet createNewWallet(User user) {
		Wallet wallet = new Wallet();
		wallet.setUser(user);
		wallet.setBalance(0.0);
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findByUser(User user) {
		
		return null;
	}

	


}
