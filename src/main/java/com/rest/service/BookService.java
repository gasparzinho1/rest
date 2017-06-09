package com.rest.service;

import java.util.List;

import com.rest.entity.Book;

public interface BookService {

    public List<Book> getAllBooks();

    public Book getBookById(int id);

    public List<Book> getBooksByAuthor(String author);

    public List<Book> getBooksByName(String name);

    public void deleteBookById(int id);

    public Book addBook(String author, String name, double price);

    public Book updateBook(int id, String author, String name, double price);

}