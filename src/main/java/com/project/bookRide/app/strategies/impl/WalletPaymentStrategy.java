package com.project.bookRide.app.strategies.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.Payment;
import com.project.bookRide.app.entities.User;
import com.project.bookRide.app.entities.enums.PaymentStatus;
import com.project.bookRide.app.entities.enums.TransactionMethod;
import com.project.bookRide.app.repositories.PaymentRepository;
import com.project.bookRide.app.services.WalletService;
import com.project.bookRide.app.strategies.PaymentStrategy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy{
	
	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	@Override
	@Transactional
	public void processPayment(Payment payment) {
		User rider = payment.getRide().getRider().getUser();
		User driver = payment.getRide().getDriver().getUser();
		
		walletService.deductMoneyfromWallet(rider, payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);
		
		double driverCut = payment.getAmount()*(1- PLATFORM_COMISSION);
		walletService.addMoneyToWallet(driver, driverCut, null, payment.getRide(), TransactionMethod.RIDE);
		
		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
		
	}

}
