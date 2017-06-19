package com.rest.service.test;

import static com.rest.helper.UserHelper.createUser;
import static com.rest.helper.UserRoleHelper.createRoleAdmin;
import static com.rest.helper.UserRoleHelper.createRoleUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rest.entity.User;
import com.rest.entity.UserRole;
import com.rest.repository.UserRepository;
import com.rest.repository.UserRoleRepository;
import com.rest.service.UserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetStringWithUserRolesByUserId_AdminRoleCase() {
        User savedUser = userRepository.save(createUser());
        List<UserRole> savedUserRoles = userRoleRepository.save(createRoleAdmin(savedUser.getUserId()));

        String roles = userRoleService.getStringWithUserRolesByUserId(savedUser.getUserId());

        assertTrue(!roles.isEmpty());
        assertTrue(roles.contains("user; "));
        assertTrue(roles.contains("admin; "));

        userRoleRepository.delete(savedUserRoles);
        userRepository.delete(savedUser);
    }

    @Test
    public void testGetStringWithUserRolesByUserId_UserRoleCase() {
        User savedUser = userRepository.save(createUser());
        List<UserRole> savedUserRoles = userRoleRepository.save(createRoleUser(savedUser.getUserId()));

        String roles = userRoleService.getStringWithUserRolesByUserId(savedUser.getUserId());

        assertTrue(!roles.isEmpty());
        assertEquals("user; ", roles);

        userRoleRepository.delete(savedUserRoles);
        userRepository.delete(savedUser);
    }

    @Test
    public void testGetStringWithUserRolesByUserId_FakeIdCase() {
        String roles = userRoleService.getStringWithUserRolesByUserId(-12);

        assertTrue(roles.isEmpty());
    }
}