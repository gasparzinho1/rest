package com.rest.controller;

import static com.rest.config.SecurityConfig.getCurrentUser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.User;
import com.rest.service.UserService;
import com.rest.service.impl.UserRoleServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<String> roles = new ArrayList<String>();
        for (User user : users) {
            roles.add(userRoleService.getStringWithUserRolesByUserId(user.getUserId()));
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("currentId", getCurrentUser().getUserId());
        return "users";
    }

    @PostMapping("/getUserByid")
    public String getUserById(int id, Model model) {
        List<User> users = new ArrayList<>();
        List<String> roles = new ArrayList<String>();
        User user = userService.getUserById(id);
        if (user != null) {
            users.add(user);
            roles.add(userRoleService.getStringWithUserRolesByUserId(user.getUserId()));
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("currentId", getCurrentUser().getUserId());
        return "users";
    }

    @PostMapping("/getUserByusername")
    public String getUserByUserName(String username, Model model) {
        List<User> users = userService.getUserByUserNameContaining(username);
        List<String> roles = new ArrayList<String>();
        for (User user : users) {
            roles.add(userRoleService.getStringWithUserRolesByUserId(user.getUserId()));
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "users";
    }

    @PostMapping("/getUserBylogin")
    public String getUserByLogin(String login, Model model) {
        List<User> users = new ArrayList<>();
        List<String> roles = new ArrayList<String>();
        User user = userService.getUserByLogin(login);
        if (user != null) {
            users.add(user);
            roles.add(userRoleService.getStringWithUserRolesByUserId(user.getUserId()));
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("currentId", getCurrentUser().getUserId());
        return "users";
    }

    @PostMapping("/deleteUser")
    public String deleteUserById(int id, Model model) {
        userService.deleteUserById(id);

        List<User> users = userService.getAllUsers();
        List<String> roles = new ArrayList<String>();
        for (User user : users) {
            roles.add(userRoleService.getStringWithUserRolesByUserId(user.getUserId()));
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("currentId", getCurrentUser().getUserId());
        return "users";
    }

    @PostMapping("/addUser")
    public String addOrUpdate(int id, String username, String login, String password, String role, String update,
            Model model) {

        User savedUser;

        if (update.equals("true"))
            savedUser = userService.updateUser(id, username, login, password, role);
        else
            savedUser = userService.addUser(username, login, password, role);

        List<User> users = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        users.add(savedUser);
        roles.add(userRoleService.getStringWithUserRolesByUserId(savedUser.getUserId()));

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("currentId", getCurrentUser().getUserId());

        return "users";
    }

}