package com.example.spring_security_marathon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityMarathonApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("""
			When calling the /hello endpoint authenticated return 200 OK
			
			""")
	@WithMockUser
	void helloAuthenticatedTest() throws Exception {

		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello"));
	}

	@Test
	@DisplayName("""
			When calling the /hello endpoint unauthenticated return 401 Unauthorized
			
			""")
	void helloUnAuthenticatedTest() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpect(status().isUnauthorized());
	}



}
