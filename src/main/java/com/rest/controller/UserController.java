package com.rest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.entity.User;
import com.rest.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
    public Collection<User> getAll(){
		return userService.getAll();
	}
    
	@GetMapping("/id={id}")
	public User findById(@PathVariable("id") int id){
		return userService.getUserById(id);
	}

	@GetMapping("/login={login}/password={password}")
	public User findById(@PathVariable("login") String login,@PathVariable("password") String password){
		return userService.getUserByLoginAndPassword(login, password);
	}
	
	@DeleteMapping("/id={id}")
	public void deleteById(@PathVariable("id") int id){
		userService.deleteUserById(id);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE)
	public void saveOrUpdate(@RequestBody User user){
		userService.saveOrUpdate(user);
	}

}