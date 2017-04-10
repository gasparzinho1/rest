package com.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.User;
import com.rest.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
    public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
    
	@GetMapping("/get/id={id}")
	public User getUserById(@PathVariable("id") int id){
		return userService.getUserById(id);
	}
	
	@GetMapping("/get/username={username}")
	public List<User> getUserByUsername(@PathVariable("username") String username){
		return userService.getUserByUsername(username);
	}

	@DeleteMapping("/delete/id={id}")
	public void deleteById(@PathVariable("id") int id){
		userService.deleteUserById(id);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public User saveOrUpdate(@RequestBody User user){
		return userService.saveOrUpdateUser(user);
	}
}