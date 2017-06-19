package com.rest.service.test;

import static com.rest.helper.UserHelper.createUser;
import static com.rest.helper.UserHelper.createUsers;
import static com.rest.helper.UserRoleHelper.createRoleAdmin;
import static com.rest.helper.UserRoleHelper.createRoleUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.entity.User;
import com.rest.entity.UserRole;
import com.rest.repository.UserRepository;
import com.rest.repository.UserRoleRepository;
import com.rest.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static final String USER = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser_UserRoleCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        User userFromDb = userService.addUser(userName, login, password, USER);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(userFromDb.getUserId());

        assertNotNull(userFromDb);
        assertNotNull(userDbRoles);
        assertEquals(login, userFromDb.getLogin());
        assertEquals(1, userDbRoles.size());
        assertEquals(USER, userDbRoles.get(0).getRole());

        userRoleRepository.delete(userDbRoles);
        userRepository.delete(userFromDb);
    }

    @Test
    public void testAddUser_AdminRoleCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        User userFromDb = userService.addUser(userName, login, password, ADMIN);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(userFromDb.getUserId());

        assertNotNull(userFromDb);
        assertNotNull(userDbRoles);
        assertEquals(login, userFromDb.getLogin());
        assertEquals(2, userDbRoles.size());

        userRoleRepository.delete(userDbRoles);
        userRepository.delete(userFromDb);
    }

    @Test
    public void testUpdateUser_FromAdminToUserCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        User savedUser = userRepository.save(createUser());
        userRoleRepository.save(createRoleAdmin(savedUser.getUserId()));

        savedUser = userService.updateUser(savedUser.getUserId(), userName, login, password, USER);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(savedUser.getUserId());

        assertNotNull(savedUser);
        assertNotNull(userDbRoles);
        assertEquals(savedUser.getLogin(), login);
        assertEquals(savedUser.getUserName(), userName);
        assertEquals(savedUser.getPassword(), password);
        assertEquals(1, userDbRoles.size());
        assertEquals(USER, userDbRoles.get(0).getRole());

        userRoleRepository.delete(userDbRoles);
        userRepository.delete(savedUser);
    }

    @Test
    public void testUpdateUser_FromUserToAdminCase() {
        String userName = "Mike";
        String login = "TestMikeLogin";
        String password = "pass";

        User savedUser = userRepository.save(createUser());
        userRoleRepository.save(createRoleUser(savedUser.getUserId()));

        savedUser = userService.updateUser(savedUser.getUserId(), userName, login, password, ADMIN);
        List<UserRole> userDbRoles = userRoleRepository.findByUserId(savedUser.getUserId());

        assertNotNull(savedUser);
        assertNotNull(userDbRoles);
        assertEquals(savedUser.getLogin(), login);
        assertEquals(savedUser.getUserName(), userName);
        assertEquals(savedUser.getPassword(), password);
        assertEquals(2, userDbRoles.size());

        userRoleRepository.delete(userDbRoles);
        userRepository.delete(savedUser);
    }

    @Test
    public void testGetUserByUserNameContaining_NormalCase() {
        List<User> savedUsers = userRepository.save(createUsers());

        String name = "testing";
        List<User> usersDb = userService.getUsersByUserNameContaining(name);

        assertNotNull(usersDb);
        assertEquals(2, usersDb.size());
        assertTrue(usersDb.get(0).getUserName().contains(name));
        assertTrue(usersDb.get(1).getUserName().contains(name));

        userRepository.delete(savedUsers);
    }

    @Test
    public void testGetUserByUserNameContaining_FakeNameCase() {
        List<User> usersDb = userService.getUsersByUserNameContaining("asdwqdx");

        assertNotNull(usersDb);
        assertTrue(usersDb.isEmpty());
    }

    @Test
    public void testGetUserByLogin_NormalCase() {
        User savedUser = userRepository.save(createUser());

        User userFromDb = userService.getUserByLogin(savedUser.getLogin());

        assertNotNull(userFromDb);
        assertEquals(savedUser.getUserId(), userFromDb.getUserId());
        assertEquals(savedUser.getLogin(), userFromDb.getLogin());

        userRepository.delete(savedUser);
    }

    @Test
    public void testGetUserByLogin_FakeLoginCase() {
        User userDb = userService.getUserByLogin("asdhjw");

        assertNull(userDb);
    }

    @Test
    public void testGetUserById_NormalCase() {
        User savedUser = userRepository.save(createUser());

        User userFromDb = userService.getUserById(savedUser.getUserId());

        assertNotNull(userFromDb);
        assertTrue(userFromDb.equals(savedUser));

        userRepository.delete(savedUser);
    }

    @Test
    public void testGetUserById_FakeIdCase() {
        User userDb = userService.getUserById(-10);

        assertNull(userDb);
    }
}