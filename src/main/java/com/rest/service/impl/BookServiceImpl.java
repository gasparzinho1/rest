package com.rest.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Book;
import com.rest.repository.BookRepository;
import com.rest.service.BookService;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return bookRepository.findByNameContaining(name);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.delete(id);
    }

    @Override
    public Book addBook(String author, String name, double price) {
        Book book = new Book(author, name, price);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(int bookId, String author, String name, double price) {
        Book book = bookRepository.findOne(bookId);
        book.setAuthor(author);
        book.setName(name);
        book.setPrice(price);
        return bookRepository.save(book);
    }

}