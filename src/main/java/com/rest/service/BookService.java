package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entity.Book;
import com.rest.repository.BookRepository;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<Book>();
		for (Book book : bookRepository.findAll()) {
			books.add(book);
		}
		return books;
	}	

	public Book getBookById(int id){
		return bookRepository.findOne(id);
	}
	
	public List<Book> getBooksByAuthor(String author){
		return bookRepository.findByAuthorContaining(author);
	}
	
	public List<Book> getBooksByName(String name){
		return bookRepository.findByNameContaining(name);
	}
	
	public void deleteBookById(int id){
		bookRepository.delete(id);
	}
	
	public Book saveOrUpdateBook(Book book){
		if (book == null)
			return null;
		return bookRepository.save(book);
	}
}