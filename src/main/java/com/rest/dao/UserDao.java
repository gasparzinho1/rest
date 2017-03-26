package com.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.User;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer>{

	public void delete(User user);
	
	public User findOne(Integer id);
	
	public User findOneByLoginAndPassword(String login, String password);
	
	public List<User> findAll();
	
	public <S extends User> User save(User user);
	
}