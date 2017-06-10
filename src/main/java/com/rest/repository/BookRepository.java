package com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthorContaining(String author);

    List<Book> findByNameContaining(String name);

}