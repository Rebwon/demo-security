package com.rebwon.demosecurity.account;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void signUpForm() throws Exception {
		mockMvc.perform(get("/signup"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("_csrf")));
	}

	@Test
	void processSignUp_Fail() throws Exception {
		mockMvc.perform(post("/signup")
					.param("username", "rebwon")
					.param("password", "1234")
				)
			.andExpect(status().is4xxClientError())
			.andDo(print());
	}

	@Test
	void processSignUp() throws Exception {
		mockMvc.perform(post("/signup")
				.param("username", "rebwon")
				.param("password", "1234")
				.with(csrf()))
			.andDo(print())
			.andExpect(status().is3xxRedirection());
	}
}