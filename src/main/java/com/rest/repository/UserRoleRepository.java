package com.rest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Serializable>{
	
	public List<UserRole> findByUserId(int id);
	
//	@Query("select a.role from UserRole a, User b where b.userName=?1 and a.userId=b.userId")
//    public List<String> findRoleByUserName(String username);
	
}