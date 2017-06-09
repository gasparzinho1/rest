package com.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rest.entity.User;

public interface UserService extends UserDetailsService {

    public List<User> getAllUsers();

    public User getUserById(int id);

    public User getUserByLogin(String login);

    public List<User> getUserByUserNameContaining(String userName);

    public void deleteUserById(int id);

    public User addUser(String userName, String login, String password, String role);

    public User updateUser(int userId, String userName, String login, String password, String role);

}