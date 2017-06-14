package com.rest.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.UserRole;
import com.rest.repository.UserRoleRepository;
import com.rest.service.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void deleteUserRole(UserRole userRole) {
        userRoleRepository.delete(userRole);
    }

    @Override
    public void saveOrUpdateUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> gerUserRolesByUserId(int userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public String getStringWithUserRolesByUserId(int userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        StringBuilder roles = new StringBuilder();
        for (UserRole role : userRoles) {
            roles.append(role.getRole().substring(5).toLowerCase());
            roles.append("; ");
        }
        return roles.toString();
    }

}