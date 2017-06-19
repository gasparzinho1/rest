package com.rest.helper;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.rest.entity.UserRole;

public class UserRoleHelper {

    public static List<UserRole> createRoleUser(int userId) {
        return asList(new UserRole(userId, "ROLE_USER"));
    }

    public static List<UserRole> createRoleAdmin(int userId) {
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole(userId, "ROLE_USER"));
        roles.add(new UserRole(userId, "ROLE_ADMIN"));
        return roles;
    }

}