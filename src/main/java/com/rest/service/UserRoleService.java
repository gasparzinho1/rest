package com.rest.service;

import java.util.List;

import com.rest.entity.UserRole;

public interface UserRoleService {

    public void deleteUserRole(UserRole userRole);

    public void saveOrUpdateUserRole(UserRole userRole);

    public List<UserRole> gerUserRolesByUserId(int userId);

    public String getStringWithUserRolesByUserId(int userId);

}