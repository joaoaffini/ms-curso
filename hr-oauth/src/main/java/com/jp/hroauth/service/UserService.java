package com.jp.hroauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.hroauth.entity.User;
import com.jp.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		
		if(user == null) {
			LOGGER.error("Email not foud: "+email);
			throw new IllegalArgumentException("Email not found");
		}
		LOGGER.info("Email foud: "+email);
		return user;
	}

}
