package com.rest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByUserNameContaining(String userName);
	
	User findByLogin(String login);
	
}