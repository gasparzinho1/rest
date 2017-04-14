package com.rest.controller;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.User;
import com.rest.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
    public String getAllUsers(Model model){
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
    
	@PostMapping("/getUserByid")
	public String getUserById(HttpServletRequest request, Model model){
		int id = parseInt(request.getParameter("id"));
		List<User> users = new ArrayList<>();
		users.add(userService.getUserById(id));
		model.addAttribute("users", users);
		return "users";
	}
	
	@PostMapping("/getUserByusername")
	public String getUserByUserName(HttpServletRequest request, Model model){
		String username = request.getParameter("username");
		model.addAttribute("users", userService.getUserByUserName(username));
		return "users";
	}
	
	@PostMapping("/getUserBylogin")
	public String getUserByEmail(HttpServletRequest request, Model model){
		String login = request.getParameter("login");
		List<User> users = new ArrayList<>();
		users.add(userService.getUserByLogin(login));
		model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("/deleteUser")
	public String deleteUserById(HttpServletRequest request, Model model){
		int id = parseInt(request.getParameter("id"));
		userService.deleteUserById(id);
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
	
	@PostMapping("/addUser")
	public String saveOrUpdate(HttpServletRequest request, Model model){
		int id;
		if(request.getParameter("id") != "")
			id = parseInt(request.getParameter("id"));
		else
			id = 0;
		String username = request.getParameter("username");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User user = new User(id, username, login, password, 1);
		List<User> users = new ArrayList<>();
		users.add(userService.saveOrUpdateUser(user));
		model.addAttribute("users", users);
		return "users";
	}
}