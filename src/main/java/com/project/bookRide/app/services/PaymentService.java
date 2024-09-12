package com.project.bookRide.app.services;


import com.project.bookRide.app.entities.Payment;
import com.project.bookRide.app.entities.Ride;
import com.project.bookRide.app.entities.enums.PaymentStatus;

public interface PaymentService {
	
	void processPayment(Ride ride);
	
	Payment createNewPayment(Ride ride);
	
	void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
