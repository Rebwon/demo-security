package com.rebwon.demosecurity.form;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
	public void dashboard() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities =
			authentication.getAuthorities();
		Object credentials = authentication.getCredentials();
		boolean authenticated = authentication.isAuthenticated();
	}
}
