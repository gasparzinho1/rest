package com.rest.entity;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "user_id", unique = true)
    private int userId;

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    public User() {
        super();
    }

    public User(String userName, String login, String password) {
        super();
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public User(int userId, String userName, String login, String password) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

}