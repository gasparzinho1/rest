package com.rest.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
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
@Transactional
public class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private List<UserRole> user1Roles = new ArrayList<>();
    private List<UserRole> user2Roles = new ArrayList<>();

    @Before
    public void setUp() {
        user1 = userRepository.save(new User("TESTING name 1", "TESTING login 1", "TESTING password 1"));
        user1Roles.add(new UserRole(user1.getUserId(), "ROLE_USER"));
        user1Roles.add(new UserRole(user1.getUserId(), "ROLE_ADMIN"));
        userRoleRepository.save(user1Roles);

        user2 = userRepository.save(new User("TESTING name 2", "TESTING login 2", "TESTING password 2"));
        user2Roles.add(new UserRole(user2.getUserId(), "ROLE_USER"));
        userRoleRepository.save(user2Roles);
    }

    @Test
    public void testGetUserRolesByUserId_AdminRoleCase() {
        List<UserRole> userDbRoles = userRoleService.gerUserRolesByUserId(user1.getUserId());

        assertNotNull(userDbRoles);
        assertEquals(2, userDbRoles.size());
    }

    @Test
    public void testGetUserRolesByUserId_UserRoleCase() {
        List<UserRole> userDbRoles = userRoleService.gerUserRolesByUserId(user2.getUserId());

        assertNotNull(userDbRoles);
        assertEquals(1, userDbRoles.size());
        assertEquals("ROLE_USER", userDbRoles.get(0).getRole());
    }

    @Test
    public void testGetUserRolesByUserId_FakeIdCase() {
        List<UserRole> userDbRoles = userRoleService.gerUserRolesByUserId(-12);

        assertTrue(userDbRoles.isEmpty());
    }

    @Test
    public void testGetStringWithUserRolesByUserId_AdminRoleCase() {
        String roles = userRoleService.getStringWithUserRolesByUserId(user1.getUserId());

        assertTrue(roles.contains("user; "));
        assertTrue(roles.contains("admin; "));
    }

    @Test
    public void testGetStringWithUserRolesByUserId_UserRoleCase() {
        String roles = userRoleService.getStringWithUserRolesByUserId(user2.getUserId());

        assertEquals("user; ", roles);
    }

    @Test
    public void testGetStringWithUserRolesByUserId_FakeIdCase() {
        String roles = userRoleService.getStringWithUserRolesByUserId(-12);

        assertTrue(roles.isEmpty());
    }
}