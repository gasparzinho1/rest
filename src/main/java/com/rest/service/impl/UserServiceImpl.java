package com.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.config.SecurityConfig;
import com.rest.entity.CustomUserDetails;
import com.rest.entity.User;
import com.rest.entity.UserRole;
import com.rest.repository.UserRepository;
import com.rest.repository.UserRoleRepository;
import com.rest.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> getUsersByUserNameContaining(String userName) {
        return userRepository.findByUserNameContaining(userName);
    }

    @Override
    public void deleteUserById(int id) {
        if (id != SecurityConfig.getCurrentUser().getUserId()) {
            List<UserRole> deleteRoles = userRoleService.gerUserRolesByUserId(id);

            for (UserRole userRole : deleteRoles)
                userRoleService.deleteUserRole(userRole);

            userRepository.delete(id);
        }
    }

    @Override
    public User addUser(String userName, String login, String password, String role) {
        User savedUser = userRepository.save(new User(userName, login, password));

        UserRole userRole = new UserRole(savedUser.getUserId(), "ROLE_USER");
        userRoleService.saveOrUpdateUserRole(userRole);

        if (role.equals("ROLE_ADMIN")) {
            userRole = new UserRole(savedUser.getUserId(), role);
            userRoleService.saveOrUpdateUserRole(userRole);
        }

        return savedUser;
    }

    @Override
    public User updateUser(int userId, String userName, String login, String password, String role) {
        User user = userRepository.findOne(userId);
        user.setUserName(userName);
        user.setLogin(login);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        UserRole userRole;

        if (role.equals("ROLE_ADMIN")) {
            userRole = new UserRole(savedUser.getUserId(), role);
            userRoleService.saveOrUpdateUserRole(userRole);
        } else {
            List<UserRole> userRoles = userRoleService.gerUserRolesByUserId(savedUser.getUserId());
            for (UserRole currentRole : userRoles) {
                if (currentRole.getRole().equals("ROLE_ADMIN"))
                    userRoleService.deleteUserRole(currentRole);
            }
        }

        return savedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("No user with this login: " + login);
        } else {
            List<UserRole> userRoles = userRoleRepository.findByUserId(user.getUserId());
            List<String> roles = new ArrayList<>();

            for (UserRole role : userRoles) {
                roles.add(role.getRole());
            }
            return new CustomUserDetails(user, roles);
        }
    }

}