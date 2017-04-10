package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.entity.User;
import com.rest.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			users.add(user);
		}
		return users;
	}	

	public User getUserById(int id){
		return userRepository.findOne(id);
	}
	
	public User getUserByEmail(String email){
		return userRepository.findByEmail(email);
	}

	public List<User> getUserByUsername(String username){
		return userRepository.findByUsernameContaining(username);
	}
	
	public void deleteUserById(int id){
		userRepository.delete(id);
	}
	
	public User saveOrUpdateUser(User user){
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}