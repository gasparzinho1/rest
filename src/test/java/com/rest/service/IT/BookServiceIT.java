package com.rest.service.IT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rest.entity.Book;
import com.rest.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookServiceIT {
	
	@Autowired
	private BookService bookService;

	@Test
	public void testGetAllBooks(){
		List<Book> books = bookService.getAllBooks();
		assertNotNull(books);
		assertEquals(books.size(), 12);
	}
	
}