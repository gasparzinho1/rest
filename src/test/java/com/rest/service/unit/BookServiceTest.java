package com.rest.service.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.rest.entity.Book;
import com.rest.repository.BookRepository;
import com.rest.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookService bookService;

	private List<Book> books = new ArrayList<>();
	
	@Before
	public void setUp(){
		books.add(new Book(0, "bookAuthor0", "bookName0", 22.3));
		books.add(new Book(1, "bookAuthor1", "bookName1", 44.6));
	}
	
	@After
	public void clear(){
		books.clear();
	}
	
	@Test
	public void testGetAllBooks() {
		when(bookRepository.findAll()).thenReturn(books);
		
		List<Book> returnedBooks = bookService.getAllBooks();
		verify(bookRepository).findAll();
		
		assertNotNull(returnedBooks);
		assertEquals(returnedBooks.size(), 2);
		assertEquals(returnedBooks.get(0).getBookId(), books.get(0).getBookId());
		assertEquals(returnedBooks.get(0).getAuthor(), books.get(0).getAuthor());
		assertEquals(returnedBooks.get(1).getName(), books.get(1).getName());
		assertEquals(returnedBooks.get(1).getPrice(), books.get(1).getPrice());
	}

	@Test
	public void testGetBookByIdNormalCase() {
		int id = 1;
		when(bookRepository.findOne(id)).thenReturn(books.get(id));
		
		Book returnedBook = bookService.getBookById(id);
		verify(bookRepository).findOne(id);

		assertNotNull(returnedBook);
		assertEquals(returnedBook.getBookId(), id);
	}
	
	@Test
	public void testGetBookByIdFakeIdCase() {
		int id = 2;
		when(bookRepository.findOne(id)).thenReturn(null);
		
		Book returnedBook = bookService.getBookById(id);
		verify(bookRepository).findOne(id);

		assertNull(returnedBook);
	}
	
	@Test
	public void testGetBookByAuthorWithOneBookNormalCase() {
		String author = "bookAuthor0";
		List<Book> booksByAuthor = new ArrayList<>();
		booksByAuthor.add(books.get(0));
		when(bookRepository.findByAuthorContaining(author)).thenReturn(booksByAuthor);
		
		List<Book> returnedBooks = bookService.getBooksByAuthor(author);
		verify(bookRepository).findByAuthorContaining(author);

		assertNotNull(returnedBooks);
		assertEquals(returnedBooks.size(), 1);
		assertThat(returnedBooks.get(0).getAuthor()).isEqualTo(author);
	}
	
	@Test
	public void testGetBookByAuthorWithFewBooksNormalCase() {
		String author = "bookAuthor";
		List<Book> booksByAuthor = new ArrayList<>();
		booksByAuthor.add(books.get(0));
		booksByAuthor.add(books.get(1));
		when(bookRepository.findByAuthorContaining(author)).thenReturn(booksByAuthor);
		
		List<Book> returnedBooks = bookService.getBooksByAuthor(author);
		verify(bookRepository).findByAuthorContaining(author);

		assertNotNull(returnedBooks);
		assertEquals(returnedBooks.size(), 2);
		assertThat(returnedBooks.get(0).getAuthor()).contains(author);
		assertThat(returnedBooks.get(1).getAuthor()).contains(author);
	}
	
	@Test
	public void testGetBookByAuthorFakeAuthorCase() {
		String author = "asdsd";
		when(bookRepository.findByAuthorContaining(author)).thenReturn(null);
		
		List<Book> returnedBooks = bookService.getBooksByAuthor(author);
		verify(bookRepository).findByAuthorContaining(author);

		assertNull(returnedBooks);
	}
	
	@Test
	public void testGetBookByNameOneBookNormalCase() {
		String name = "bookName0";
		List<Book> booksByName = new ArrayList<>();
		booksByName.add(books.get(0));
		when(bookRepository.findByNameContaining(name)).thenReturn(booksByName);
		
		List<Book> returnedBooks = bookService.getBooksByName(name);
		verify(bookRepository).findByNameContaining(name);

		assertNotNull(returnedBooks);
		assertEquals(returnedBooks.size(), 1);
		assertThat(returnedBooks.get(0).getName()).isEqualTo(name);
	}
	
	@Test
	public void testGetBookByNameFewBooksNormalCase() {
		String name = "bookName";
		List<Book> booksByName = new ArrayList<>();
		booksByName.add(books.get(0));
		booksByName.add(books.get(1));
		when(bookRepository.findByNameContaining(name)).thenReturn(booksByName);
		
		List<Book> returnedBooks = bookService.getBooksByName(name);
		verify(bookRepository).findByNameContaining(name);

		assertNotNull(returnedBooks);
		assertEquals(returnedBooks.size(), 2);
		assertThat(returnedBooks.get(0).getName()).contains(name);
		assertThat(returnedBooks.get(1).getName()).contains(name);
	}
	
	@Test
	public void testGetBookByNameFakeNameCase() {
		String name = "asdsd";
		when(bookRepository.findByNameContaining(name)).thenReturn(null);
		
		List<Book> returnedBooks = bookService.getBooksByName(name);
		verify(bookRepository).findByNameContaining(name);

		assertNull(returnedBooks);
	}
	
	@Test
	public void testDeleteBookByIdNormalCase() {
		bookService.deleteBookById(0);
		verify(bookRepository).delete(0);
	}
	
	@Test
	public void testAddBookNormalCase() {
		Book book = new Book(2, "bookAuthor2", "bookName2", 66.9);
		when(bookRepository.save(book)).thenReturn(book);
		
		Book returnedBook = bookService.addOrUpdateBook(book);
		verify(bookRepository).save(book);
		
		assertNotNull(returnedBook);
		assertEquals(returnedBook.getBookId(), book.getBookId());
		assertEquals(returnedBook.getAuthor(), book.getAuthor());
		assertEquals(returnedBook.getName(), book.getName());
		assertEquals(returnedBook.getPrice(), book.getPrice());
	}
	
	@Test
	public void testUpdateBookNormalCase() {
		Book book = new Book(2, "bookAuthor2", "bookName2", 66.9);
		when(bookRepository.save(book)).thenReturn(book);
		
		Book returnedBook = bookService.addOrUpdateBook(book);
		String newAuthor = "newAuthor";
		returnedBook.setAuthor(newAuthor);
		when(bookRepository.save(returnedBook)).thenReturn(returnedBook);
		
		Book updatedBook = bookService.addOrUpdateBook(returnedBook);
		verify(bookRepository, times(2)).save(any(Book.class));
		
		assertNotNull(updatedBook);
		assertEquals(updatedBook.getBookId(), returnedBook.getBookId());
		assertEquals(updatedBook.getAuthor(), newAuthor);
		assertEquals(updatedBook.getName(), returnedBook.getName());
		assertEquals(updatedBook.getPrice(), returnedBook.getPrice());
	}
}