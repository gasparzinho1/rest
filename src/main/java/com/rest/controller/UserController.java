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
import com.rest.entity.UserRole;
import com.rest.service.UserRoleService;
import com.rest.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping
    public String getAllUsers(Model model){
		List<User> users = userService.getAllUsers();
		List<String> roles = new ArrayList<String>();
		for (User user : users) {
			roles.add(userRoleService.gerStringWithUserRolesByUserId(user.getUserId()));
		}
		model.addAttribute("users", users);
		model.addAttribute("roles", roles);
		return "users";
	}
    
	@PostMapping("/getUserByid")
	public String getUserById(HttpServletRequest request, Model model){
		int id = parseInt(request.getParameter("id"));
		List<User> users = new ArrayList<>();
		User user = userService.getUserById(id);
		if(user != null)
			users.add(user);
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
	public String getUserByLogin(HttpServletRequest request, Model model){
		String login = request.getParameter("login");
		List<User> users = new ArrayList<>();
		User user = userService.getUserByLogin(login);
		if(user != null)
			users.add(user);
		model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("/deleteUser")
	public String deleteUserById(HttpServletRequest request, Model model){
		int id = parseInt(request.getParameter("id"));
		List<UserRole> roles = userRoleService.gerUserRolesByUserId(id);
		for (UserRole userRole : roles) {
			userRoleService.deleteUserRole(userRole);
		}
		userService.deleteUserById(id);
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
	
	@PostMapping("/addUser")
	public String addOrUpdate(HttpServletRequest request, Model model){
		int id = parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		User user = new User(id, username, login, password, 1);
		userService.addOrUpdateUser(user);
		
		User savedUser = userService.getUserByLogin(user.getLogin());
		UserRole userRole;
		
		if(request.getParameter("update").equals("true")){
			if(role.equals("ROLE_ADMIN")){
				userRole = new UserRole(savedUser.getUserId(), role);
				userRoleService.saveOrUpdateUserRole(userRole);
			} else {
				List<UserRole> userRoles = userRoleService.gerUserRolesByUserId(savedUser.getUserId());
				for(UserRole userRole2 : userRoles) {
				if(userRole2.getRole().equals("ROLE_ADMIN"))
					userRoleService.deleteUserRole(userRole2);
				}
			}
		} else {
			if(role.equals("ROLE_ADMIN")){
				userRole = new UserRole(savedUser.getUserId(), role);
				userRoleService.saveOrUpdateUserRole(userRole);
			} 
			userRole = new UserRole(savedUser.getUserId(), "ROLE_USER");
			userRoleService.saveOrUpdateUserRole(userRole);
		}
		
		model.addAttribute("users", userService.getAllUsers());
		return "users";
	}
}