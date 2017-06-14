package com.rest.service;

import java.util.List;

import com.rest.entity.Book;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(int id);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByName(String name);

    void deleteBookById(int id);

    Book addBook(String author, String name, double price);

    Book updateBook(int id, String author, String name, double price);

}