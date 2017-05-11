package com.rest.repository.IT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.entity.Book;
import com.rest.repository.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class BookRepositoryIT {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testFindOneNormalCase(){
		int id = 1;
		Book book = bookRepository.findOne(id);
		assertNotNull(book);
	}
	
	@Test
	public void testFindOneFakeIdCase(){
		int id = 0;
		Book book = bookRepository.findOne(id);
		assertNull(book);
	}
	
	@Test
	public void testFindAllNormalCase(){
		List<Book> books = bookRepository.findAll();
		assertNotNull(books);
	}
	
	@Test
	public void testDeleteBookNormalCase(){
		entityManager.persist(new Book(0, "auth", "name", 56.3));
	}
	
	@Test
	public void testFindByAuthorBookNormalCase() {
		String author = "Jim Beglin";
		List<Book> books = bookRepository.findByAuthorContaining(author);
		assertNotNull(books);
		for (Book book : books)
			assertThat(book.getAuthor()).containsIgnoringCase(author);
	}
	
	@Test
	public void testFindByAuthorFakeAuthorCase() {
		String author = "smth";
		List<Book> books = bookRepository.findByAuthorContaining(author);
		assertNotNull(books);
		assertEquals(books.size(), 0);
	}
	
	@Test
	public void testFindByNameOneBookNormalCase() {
		String name = "Racer";
		List<Book> books = bookRepository.findByNameContaining(name);
		assertNotNull(books);
		for (Book book : books)
			assertThat(book.getName()).containsIgnoringCase(name);
	}
		
	@Test
	public void testFindByNameFakeNameCase() {
		String name = "smth";
		List<Book> books = bookRepository.findByNameContaining(name);
		assertNotNull(books);
		assertEquals(books.size(), 0);
	}
}