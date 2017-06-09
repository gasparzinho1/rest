package com.rest.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import com.rest.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    private static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    private User user1;
    private User user2;
    private List<UserRole> user1Roles = new ArrayList<>();
    private List<UserRole> user2Roles = new ArrayList<>();

    @Before
    public void setUp() {
        user1 = userRepository.save(new User("TESTING user name 1", "TESTING user login 1", "TESTING user password 1"));
        user1Roles.add(new UserRole(user1.getUserId(), "ROLE_USER"));
        user1Roles.add(new UserRole(user1.getUserId(), "ROLE_ADMIN"));
        userRoleRepository.save(user1Roles);

        user2 = userRepository.save(new User("TESTING user name 2", "TESTING user login 2", "TESTING user password 2"));
        user2Roles.add(new UserRole(user2.getUserId(), "ROLE_USER"));
        userRoleRepository.save(user2Roles);
    }

    @Test
    public void testAddUser_UserRoleCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        User userDb = userService.addUser(userName, login, password, USER);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(userDb.getUserId());

        assertNotNull(userDb);
        assertNotNull(userDbRoles);
        assertEquals(login, userDb.getLogin());
        assertEquals(1, userDbRoles.size());
        assertEquals(USER, userDbRoles.get(0).getRole());
    }

    @Test
    public void testAddUser_AdminRoleCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        User userDb = userService.addUser(userName, login, password, ADMIN);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(userDb.getUserId());

        assertNotNull(userDb);
        assertNotNull(userDbRoles);
        assertEquals(login, userDb.getLogin());
        assertEquals(2, userDbRoles.size());
    }

    @Test
    public void testUpdateUser_UserRoleCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        userService.updateUser(user1.getUserId(), userName, login, password, USER);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(user1.getUserId());

        assertNotNull(user1);
        assertNotNull(userDbRoles);
        assertEquals(user1.getLogin(), login);
        assertEquals(1, userDbRoles.size());
        assertEquals(USER, userDbRoles.get(0).getRole());
    }

    @Test
    public void testUpdateUser_AdminRoleCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        userService.updateUser(user2.getUserId(), userName, login, password, ADMIN);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(user2.getUserId());

        assertNotNull(user2);
        assertNotNull(userDbRoles);
        assertEquals(user2.getLogin(), login);
        assertEquals(2, userDbRoles.size());
    }

    @Test
    public void testGetUserByUserNameContaining_NormalCase() {
        String name = "TESTING";
        List<User> usersDb = userService.getUserByUserNameContaining(name);

        assertNotNull(usersDb);
        assertEquals(2, usersDb.size());
        assertTrue(usersDb.get(0).getUserName().contains(name));
        assertTrue(usersDb.get(1).getUserName().contains(name));
    }

    @Test
    public void testGetUserByUserNameContaining_FakeNameCase() {
        String name = "asdwqdx";
        List<User> usersDb = userService.getUserByUserNameContaining(name);

        assertNotNull(usersDb);
        assertTrue(usersDb.isEmpty());
    }

    @Test
    public void testGetUserByLogin_NormalCase() {
        User userDb = userService.getUserByLogin(user1.getLogin());

        assertNotNull(userDb);
        assertEquals(user1.getUserId(), userDb.getUserId());
        assertEquals(user1.getLogin(), userDb.getLogin());
    }

    @Test
    public void testGetUserByLogin_FakeLoginCase() {
        User userDb = userService.getUserByLogin("asdhjw");

        assertNull(userDb);
    }

    @Test
    public void testGetUserById_NormalCase() {
        User userDb = userService.getUserById(user2.getUserId());

        assertNotNull(userDb);
        assertTrue(userDb.equals(user2));
    }

    @Test
    public void testGetUserById_FakeIdCase() {
        User userDb = userService.getUserById(-10);

        assertNull(userDb);
    }
}