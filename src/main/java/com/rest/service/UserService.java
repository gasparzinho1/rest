package com.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rest.entity.User;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByLogin(String login);

    List<User> getUsersByUserNameContaining(String userName);

    void deleteUserById(int id);

    User addUser(String userName, String login, String password, String role);

    User updateUser(int userId, String userName, String login, String password, String role);

}