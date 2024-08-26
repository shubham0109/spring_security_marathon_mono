package com.example.spring_security_marathon_e2;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityMarathonE2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@DisplayName("""
			When /demo endpoint is called without authentication, return 401 Unauthorized
			""")
	void test1() throws Exception {
		mockMvc.perform(get("/demo")).andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("""
			When /demo endpoint is called with authentication but wrong authority, return 403 Forbidden
			""")
	@WithMockUser(authorities = "wrong")
	void test2() throws Exception {
		mockMvc.perform(get("/demo")).andExpect(status().isForbidden());
	}

	@Test
	@DisplayName("""
			When /demo endpoint is called with authentication and correct authority, return 200 OK
			""")
	@WithMockUser(authorities = "read")
	void test3() throws Exception {
		mockMvc.perform(get("/demo")).andExpect(status().isOk());
	}





}
