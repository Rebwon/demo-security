package com.rebwon.demosecurity.form;

import org.springframework.stereotype.Service;

import com.rebwon.demosecurity.account.Account;
import com.rebwon.demosecurity.account.AccountContext;

@Service
public class SampleService {
	public void dashboard() {
		Account account = AccountContext.getAccount();
		System.out.println("===============");
		System.out.println(account.getUsername());
	}
}
