package com.rest.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dao.UserDao;
import com.rest.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public Collection<User> getAll(){
		return userDao.findAll();
	}	

	public User getUserById(int id){
		return userDao.findOne(id);
	}
	
	public User getUserByLoginAndPassword(String login, String password){
		return userDao.findOneByLoginAndPassword(login, password);
	}
	
	public void deleteUserById(int id){
		userDao.delete(id);
	}
	
	public void saveOrUpdate(User user){
		userDao.save(user);
	}
}