package com.rebwon.demosecurity.form;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.rebwon.demosecurity.common.SecurityLogger;

@Service
public class SampleService {
	public void dashboard() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		System.out.println("=============");
		System.out.println(authentication);
		System.out.println(userDetails.getUsername());
	}

	@Async
	public void async() {
		SecurityLogger.log("Async Service");
		System.out.println("Async service is called.");
	}
}
