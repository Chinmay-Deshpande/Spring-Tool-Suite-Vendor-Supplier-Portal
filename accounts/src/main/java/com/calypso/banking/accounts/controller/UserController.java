package com.calypso.banking.accounts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calypso.banking.accounts.model.User;
import com.calypso.banking.accounts.persistence.PersonPersistence;
import com.calypso.banking.accounts.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {
	
		//@Autowired         //this is for setter injection
	public UserService userService;
	
	@Autowired               //constructor injection
	public UserController(UserService userService) {
		//super();
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/data")
	public List<User> getUser() {
		
		List<User> userlist=new ArrayList<>();
		
		User userobj= new User();
		userobj.setUserid(1);
		userobj.setName("chinmay");
		userobj.setCity("fremont");
		
		userlist.add(userobj);
		return userlist;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/data/{id}")
											                                // user
	public User getUserbyid(@PathVariable(value = "id") int userid) {
		User userObj=userService.getuser(userid);
		return userObj;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dataparam")
	public String getUserbyparam(@RequestParam(value = "id") int userid,
			@RequestHeader(value = "authtoken") String token) {
		System.out.println("auth token is: " + token);
		return "Chinmay param" + userid;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/data")
	public PersonPersistence createtUser(@RequestBody User user) {
		
		//user.setUserid(2);
		
		return userService.createUser(user);
		//return user;	
	}
	
	//---------------------------------------
	@RequestMapping(method=RequestMethod.GET, value="/dataAllUser")
	public List<PersonPersistence> findAllUsers()
	{
		List<PersonPersistence> list=userService.findAllUsers();
		return list;
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/data")
	public PersonPersistence updateUser(@RequestBody User user)
	{
		return userService.updateUser(user.getUserid(), user.getCity()); 
	}

	
	//not working, check for it.   {name}&{city}
	@RequestMapping(method=RequestMethod.GET, value="/databynameandcity/{name}&{city}")
	public List<PersonPersistence> findByNameAndCity(@PathVariable(value="name") String name, @PathVariable(value="city") String city)
	{
		System.out.println("sdd");
		return userService.findUserByNameAndCity(name,city);
	}
}
