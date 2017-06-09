package com.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.UserRole;
import com.rest.repository.UserRoleRepository;

@Service
@Transactional
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	public void deleteUserRole(UserRole userRole){
		userRoleRepository.delete(userRole);
	}
	
	public void saveOrUpdateUserRole(UserRole userRole){	
		userRoleRepository.save(userRole);
	}

	public List<UserRole> gerUserRolesByUserId(int userId){
		return userRoleRepository.findByUserId(userId);
	}
	
	public String gerStringWithUserRolesByUserId(int userId){
		List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
		String roles = "";
		for (UserRole role : userRoles) {
			roles = roles + role.getRole().substring(5).toLowerCase() + "; ";
		}
		return roles;
	}
}