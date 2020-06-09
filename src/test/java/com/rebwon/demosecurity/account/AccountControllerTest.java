package com.rebwon.demosecurity.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccountService accountService;

	@Test
	@WithAnonymousUser
	void index_anonymous() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk());
	}


	@Test
	@WithUser
	public void index_user() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@WithUser
	public void admin_user() throws Exception {
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "rebwon", roles = "ADMIN")
	public void admin_admin() throws Exception {
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void login_success() throws Exception {
		String username = "rebwon";
		String password = "123";
		Account user = this.createUser(username, password);
		mockMvc.perform(formLogin().user(user.getUsername()).password(password))
			.andExpect(authenticated());
	}

	@Test
	@Transactional
	public void login_success2() throws Exception {
		String username = "rebwon";
		String password = "123";
		Account user = this.createUser(username, password);
		mockMvc.perform(formLogin().user(user.getUsername()).password(password))
			.andExpect(authenticated());
	}

	@Test
	@Transactional()
	public void login_fail() throws Exception {
		String username = "rebwon";
		String password = "123";
		Account user = this.createUser(username, password);
		mockMvc.perform(formLogin().user(user.getUsername()).password("12345"))
			.andExpect(unauthenticated());
	}

	private Account createUser(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setRole("USER");
		return accountService.create(account);
	}
}