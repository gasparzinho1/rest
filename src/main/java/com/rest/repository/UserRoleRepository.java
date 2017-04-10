package com.rest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Serializable>{
	
	public List<String> findRolesByUserId(int userId);
	
}