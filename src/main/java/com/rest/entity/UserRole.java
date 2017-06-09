package com.rest.entity;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "role_id", unique = true)
    private int roleId;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Column(name = "role")
    private String role;

    public UserRole() {
        super();
    }

    public UserRole(int userId, String role) {
        super();
        this.userId = userId;
        this.role = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}