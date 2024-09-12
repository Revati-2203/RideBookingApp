package com.project.bookRide.app.strategies.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.Driver;
import com.project.bookRide.app.entities.Payment;
import com.project.bookRide.app.entities.Wallet;
import com.project.bookRide.app.entities.enums.PaymentStatus;
import com.project.bookRide.app.entities.enums.TransactionMethod;
import com.project.bookRide.app.repositories.PaymentRepository;
import com.project.bookRide.app.services.PaymentService;
import com.project.bookRide.app.services.WalletService;
import com.project.bookRide.app.strategies.PaymentStrategy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy{

	private final WalletService walletService;
	private final PaymentRepository paymentRepository;
	
	@Override
	public void processPayment(Payment payment) {
		Driver driver = payment.getRide().getDriver();
		double platformComission = payment.getAmount()*PLATFORM_COMISSION;
		
		walletService.deductMoneyfromWallet(driver.getUser(), platformComission,null, payment.getRide(), TransactionMethod.RIDE);

		payment.setPaymentStatus(PaymentStatus.CONFIRMED);
		paymentRepository.save(payment);
	}

}
