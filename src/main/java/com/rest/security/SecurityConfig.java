package com.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
	 
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
		 	.authorizeRequests()
		 		.antMatchers("/login", "/").permitAll()
		 		.antMatchers("/menu").access("hasRole('ROLE_USER')")
				.antMatchers("/books/**").access("hasRole('ROLE_ADMIN')")
		 		.antMatchers("/users/**").access("hasRole('ROLE_ADMIN')");
	}
}