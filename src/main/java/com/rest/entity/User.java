package com.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "id", unique = true)
	private int id;
	
	@NotNull
	@Column(name = "login")
	private String login;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User() {
		super();
	}

	public User(int id, String login, String password, Role role) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public enum Role {USER, ADMIN}
}