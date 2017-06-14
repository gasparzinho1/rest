package com.rest.service;

import java.util.List;

import com.rest.entity.UserRole;

public interface UserRoleService {

    void deleteUserRole(UserRole userRole);

    void saveOrUpdateUserRole(UserRole userRole);

    List<UserRole> gerUserRolesByUserId(int userId);

    String getStringWithUserRolesByUserId(int userId);

}