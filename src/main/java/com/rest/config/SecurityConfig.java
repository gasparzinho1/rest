package com.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/users").hasRole("ADMIN")
				.antMatchers("/addUser").hasRole("ADMIN")
				.antMatchers("/menu").authenticated()
				.antMatchers("/books").authenticated()
				.antMatchers("/addBook").authenticated()
				.antMatchers("/403").authenticated()
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/menu", true)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout") 
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.csrf();
	}
}