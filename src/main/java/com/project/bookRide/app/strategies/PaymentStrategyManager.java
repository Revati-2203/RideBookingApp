package com.project.bookRide.app.strategies;

import org.springframework.stereotype.Component;

import com.project.bookRide.app.entities.enums.PaymentMethod;
import com.project.bookRide.app.strategies.impl.CashPaymentStrategy;
import com.project.bookRide.app.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
	
	private final WalletPaymentStrategy walletPaymentStrategy;
	private final CashPaymentStrategy cashPaymentStrategy;
	
	public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
		return switch(paymentMethod) {
		case WALLET -> walletPaymentStrategy;
		case CASH -> cashPaymentStrategy;
		};
	}
}
