package com.project.bookRide.app.strategies;

import com.project.bookRide.app.entities.Payment;

public interface PaymentStrategy {

	static final Double PLATFORM_COMISSION = 0.3;
	void processPayment(Payment payment);
	
}
