package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User2;
import com.example.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/getuser/{id}")
	public Optional<User2> findId(@PathVariable int id)
	{
		
		return userService.findOne(id);
	}

	@RequestMapping(method=RequestMethod.POST,value="/user")
	public void saveUser(@RequestBody User2 user)
	{
		
		userService.save(user);
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/getusers")
	public Iterable<User2> getUsers()
	{
		
		return  userService.findAll();
		
	}
	
@RequestMapping(method=RequestMethod.DELETE,value="/delectuser/{id}")
	public String delectUser(@PathVariable int id)
	{
		userService.delectUser(id);
		return "success";
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/updateuser/{id}")
	public String updateUser(@RequestBody User2 user, @PathVariable int id)
	{
		userService.updateUser(user,id);
		return "Success";
		
	}
	
	
	
}
