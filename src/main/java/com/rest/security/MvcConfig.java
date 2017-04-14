package com.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/books").setViewName("books");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/addBook").setViewName("addBook");
        registry.addViewController("/addUser").setViewName("addUser");
        registry.addViewController("/menu").setViewName("menu");
    }

}