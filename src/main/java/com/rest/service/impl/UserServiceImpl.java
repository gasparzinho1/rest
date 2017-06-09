package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.entity.CustomUserDetails;
import com.rest.entity.User;
import com.rest.entity.UserRole;
import com.rest.repository.UserRepository;
import com.rest.repository.UserRoleRepository;

@Service
@Transactional
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
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
	
	public User getUserByLogin(String login){
		return userRepository.findByLogin(login);
	}

	public List<User> getUserByUserName(String userName){
		return userRepository.findByUserNameContaining(userName);
	}
	
	public void deleteUserById(int id){
		userRepository.delete(id);
	}
	
	public User addOrUpdateUser(User user){	
		return userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(login);
		if(user == null){
			throw new UsernameNotFoundException("No user with this email: " + login);
		} else {
			List<UserRole> userRoles = userRoleRepository.findByUserId(user.getUserId());
			List<String> roles = new ArrayList<>();
			
			for (UserRole role : userRoles) {
				roles.add(role.getRole());
			}
			return new CustomUserDetails(user, roles);
		}
	}
}