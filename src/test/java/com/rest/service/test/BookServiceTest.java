package com.rest.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rest.entity.Book;
import com.rest.repository.BookRepository;
import com.rest.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        book1 = bookRepository.save(new Book("TESTING Author 1", "TESTING Name 1", 11.1));
        book2 = bookRepository.save(new Book("TESTING Author 2", "TESTING Name 2", 22.2));
        book3 = bookRepository.save(new Book("Author 3", "Name 3", 33.3));
    }

    @Test
    public void testGetBookById_NormalCase() {
        Book bookDb = bookService.getBookById(book1.getBookId());

        assertNotNull(bookDb);
        assertEquals(bookDb.getAuthor(), book1.getAuthor());
        assertEquals(bookDb.getName(), book1.getName());
        assertEquals(Double.valueOf(bookDb.getPrice()), Double.valueOf(book1.getPrice()));
    }

    @Test
    public void testGetBookById_FakeIdCase() {
        Book book = bookService.getBookById(-16);
        assertNull(book);
    }

    @Test
    public void testFindByAuthorContaining_NormalCase() {
        String author = "TESTING";
        List<Book> bookDb = bookService.getBooksByAuthor(author);

        assertNotNull(bookDb);
        assertEquals(2, bookDb.size());
        assertThat(bookDb.get(0).getAuthor()).contains(author);
        assertThat(bookDb.get(1).getAuthor()).contains(author);
    }

    @Test
    public void testFindByAuthorContaining_FakeAuthorCase() {
        String author = "asdqwdxsze";
        List<Book> bookDb = bookService.getBooksByAuthor(author);

        assertNotNull(bookDb);
        assertTrue(bookDb.isEmpty());
    }

    @Test
    public void testFindByNameContaining_NormalCase() {
        String name = "TESTING";
        List<Book> bookDb = bookService.getBooksByName(name);

        assertNotNull(bookDb);
        assertEquals(2, bookDb.size());
        assertThat(bookDb.get(0).getName()).contains(name);
        assertThat(bookDb.get(1).getName()).contains(name);
    }

    @Test
    public void testFindByNameContaining_FakeNameCase() {
        String name = "asdqwcsde";
        List<Book> bookDb = bookService.getBooksByName(name);

        assertNotNull(bookDb);
        assertTrue(bookDb.isEmpty());
    }

    @Test
    public void testDeleteBookById_NormalCase() {
        bookService.deleteBookById(book2.getBookId());

        Book bookDb = bookService.getBookById(book2.getBookId());
        assertNull(bookDb);
    }

    @Test
    public void testUpdateBook_NormalCase() {
        Book oldBook = new Book(book3.getAuthor(), book3.getName(), book3.getPrice());

        bookService.updateBook(book3.getBookId(), "New author", "New name", 117.23);

        assertNotEquals(book3.getAuthor(), oldBook.getAuthor());
        assertNotEquals(book3.getName(), oldBook.getName());
        assertNotEquals(book3.getPrice(), oldBook.getPrice());
    }

}