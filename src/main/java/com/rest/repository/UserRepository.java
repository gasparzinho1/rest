package com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUserNameContaining(String userName);

    User findByLogin(String login);

}