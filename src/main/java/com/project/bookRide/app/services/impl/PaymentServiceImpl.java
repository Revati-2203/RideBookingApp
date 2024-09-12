package com.project.bookRide.app.services.impl;

import org.springframework.stereotype.Service;

import com.project.bookRide.app.entities.Payment;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.enums.PaymentStatus;
import com.project.bookRide.app.exceptions.ResourceNotFoundException;
import com.project.bookRide.app.repositories.PaymentRepository;
import com.project.bookRide.app.services.PaymentService;
import com.project.bookRide.app.strategies.PaymentStrategyManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final PaymentStrategyManager paymentStrategyManager;
	
	
	@Override
	public void processPayment(Ride ride) {
		Payment payment = paymentRepository.findByRide(ride).orElseThrow(
				()-> new ResourceNotFoundException("Payment not found for ride: "+ride.getId()));
		paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);;
	}

	@Override
	public Payment createNewPayment(Ride ride) {
		Payment payment = Payment.builder()
							.ride(ride)
							.paymentMethod(ride.getPaymentMethod())
							.amount(ride.getFare())
							.paymentStatus(PaymentStatus.PENDING)
							.build();
		return paymentRepository.save(payment);
	}

	@Override
	public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
		payment.setPaymentStatus(paymentStatus);
		paymentRepository.save(payment);
	}

}
