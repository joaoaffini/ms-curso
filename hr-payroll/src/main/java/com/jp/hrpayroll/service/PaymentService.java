package com.jp.hrpayroll.service;

import org.springframework.stereotype.Service;

import com.jp.hrpayroll.entity.Payment;

@Service
public class PaymentService {

	public Payment getPayment(Long workerId, Integer days) {
		return new Payment("Bob", 200.0, days);
	}
}
