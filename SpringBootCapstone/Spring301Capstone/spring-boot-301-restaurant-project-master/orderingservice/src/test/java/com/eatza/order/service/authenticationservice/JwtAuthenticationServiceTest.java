package com.eatza.order.service.authenticationservice;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.order.dto.UserDto;
import com.eatza.order.exception.UnauthorizedException;
import com.eatza.order.service.authenticationservice.JwtAuthenticationService;

@RunWith(SpringRunner.class)
public class JwtAuthenticationServiceTest {
	

	@InjectMocks
	JwtAuthenticationService jwtAuthenticationService;
	
	@Before
	public void setup() {
		org.springframework.test.util.ReflectionTestUtils.setField(jwtAuthenticationService, "username", "user");
		org.springframework.test.util.ReflectionTestUtils.setField(jwtAuthenticationService, "password", "password");
	}
	@Test(expected=UnauthorizedException.class)
	public void authenticateUser_invalidname() throws UnauthorizedException {
		UserDto user = new UserDto();
		user.setPassword("password");
		user.setUsername("invalid");
		String jwt= jwtAuthenticationService.authenticateUser(user);

	}
	
	@Test(expected=UnauthorizedException.class)
	public void authenticateUser_invalidpassword() throws UnauthorizedException {
		UserDto user = new UserDto();
		user.setPassword("invalid");
		user.setUsername("user");
		String jwt= jwtAuthenticationService.authenticateUser(user);

		
	}
	
	@Test
	public void authenticateUser() throws UnauthorizedException {
		UserDto user = new UserDto();
		user.setPassword("password");
		user.setUsername("user");
		String jwt= jwtAuthenticationService.authenticateUser(user);
		assertNotNull(jwt);
	}

	

}
