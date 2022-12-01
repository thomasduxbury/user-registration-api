package com.thomasduxbury.userregistrationapi.controllers;

import com.thomasduxbury.userregistrationapi.domain.User;
import com.thomasduxbury.userregistrationapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;

	@BeforeAll
	public void setup() {
		User user = new User("Test", "test@test.com", "M1 1TY", "password");
		User user2 = new User("Test 2", "test2@test.com", "M1 1TY", "password");
		List<User> userList = Arrays.asList(user, user2);
		when(userRepository.findAll()).thenReturn(userList);
	}

	@Test
	public void given_UsersExist_when_GettingAllUsers_then_UsersShouldBeReturned() throws Exception {
		this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("[{\"name\":\"Test\",\"email\":\"test@test.com\",\"postcode\":\"M1 1TY\",\"password\":\"password\"},{\"name\":\"Test 2\",\"email\":\"test2@test.com\",\"postcode\":\"M1 1TY\",\"password\":\"password\"}]"));
	}

	@Test
	public void when_SavingUser_then_UserShouldBeInserted() throws Exception {
		this.mockMvc.perform(post("/users").contentType("application/json").content("{\"email\": \"test@test.com\", \"password\": \"password\"}")).andDo(print()).andExpect(status().isOk());
	}

}
