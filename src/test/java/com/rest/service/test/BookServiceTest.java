package com.rest.service.test;

import static helper.BookHelper.createBook;
import static helper.BookHelper.createBooks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.entity.Book;
import com.rest.repository.BookRepository;
import com.rest.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetBookById_NormalCase() {
        Book savedBook = bookRepository.save(createBook());
        Book bookFromDb = bookService.getBookById(savedBook.getBookId());

        assertNotNull(bookFromDb);
        assertEquals(bookFromDb.getAuthor(), savedBook.getAuthor());
        assertEquals(bookFromDb.getName(), savedBook.getName());
        assertEquals(Double.valueOf(bookFromDb.getPrice()), Double.valueOf(savedBook.getPrice()));

        bookRepository.delete(savedBook);
    }

    @Test
    public void testGetBookById_FakeIdCase() {
        Book bookFromDb = bookService.getBookById(-16);
        assertNull(bookFromDb);
    }

    @Test
    public void testFindByAuthorContaining_NormalCase() {
        List<Book> savedBooks = bookRepository.save(createBooks());

        String author = "testing";
        List<Book> booksFromDb = bookService.getBooksByAuthor(author);

        assertNotNull(booksFromDb);
        assertEquals(2, booksFromDb.size());
        assertThat(booksFromDb.get(0).getAuthor()).contains(author);
        assertThat(booksFromDb.get(1).getAuthor()).contains(author);

        bookRepository.delete(savedBooks);
    }

    @Test
    public void testFindByAuthorContaining_FakeAuthorCase() {
        String author = "asdqwdxsze";
        List<Book> booksFromDb = bookService.getBooksByAuthor(author);

        assertNotNull(booksFromDb);
        assertTrue(booksFromDb.isEmpty());
    }

    @Test
    public void testFindByNameContaining_NormalCase() {
        List<Book> savedBooks = bookRepository.save(createBooks());

        String name = "testing";
        List<Book> booksFromDb = bookService.getBooksByName(name);

        assertNotNull(booksFromDb);
        assertEquals(2, booksFromDb.size());
        assertThat(booksFromDb.get(0).getName()).contains(name);
        assertThat(booksFromDb.get(1).getName()).contains(name);

        bookRepository.delete(savedBooks);
    }

    @Test
    public void testFindByNameContaining_FakeNameCase() {
        String name = "asdqwcsde";
        List<Book> booksFromDb = bookService.getBooksByName(name);

        assertNotNull(booksFromDb);
        assertTrue(booksFromDb.isEmpty());
    }

    @Test
    public void testDeleteBookById_NormalCase() {
        Book savedBook = bookRepository.save(createBook());
        bookService.deleteBookById(savedBook.getBookId());

        Book bookFromDb = bookRepository.findOne(savedBook.getBookId());
        assertNull(bookFromDb);
    }

    @Test
    public void testUpdateBook_NormalCase() {
        Book savedBook = bookRepository.save(createBook());

        String author = "New author";
        String name = "New name";
        double price = 117.23;

        savedBook = bookService.updateBook(savedBook.getBookId(), author, name, price);

        assertEquals(author, savedBook.getAuthor());
        assertEquals(name, savedBook.getName());
        assertEquals(Double.valueOf(price), Double.valueOf(savedBook.getPrice()));

        bookRepository.delete(savedBook);
    }

}