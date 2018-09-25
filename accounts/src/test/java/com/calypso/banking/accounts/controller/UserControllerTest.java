package com.calypso.banking.accounts.controller;

import java.awt.PageAttributes.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration.AnnotationConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.calypso.banking.accounts.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootApplication
@WebAppConfiguration
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserControllerTest {
	
	private final String GET_USER_RES="/v1/user/data/24";
	
	MockMvc mvc;
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		this.mvc=MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testGetUseryId() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.get(GET_USER_RES).content(org.springframework.http.MediaType.APPLICATION_JSON_VALUE.toString()).
		accept(org.springframework.http.MediaType.APPLICATION_JSON_VALUE.toString())).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
