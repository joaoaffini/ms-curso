package com.jp.hrpayroll.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jp.hrpayroll.entity.Payment;
import com.jp.hrpayroll.entity.Worker;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", workerId.toString());
		
		Worker worker = restTemplate.getForObject(workerHost+"/workers/{id}", Worker.class, pathVariables);
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
