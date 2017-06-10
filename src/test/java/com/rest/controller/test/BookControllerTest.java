package com.rest.controller.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rest.entity.Book;
import com.rest.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookService bookService;

    private MockMvc mvc;

    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        book1 = bookService.addBook("testAuthorBook1", "testName1", 11.1);
        book2 = bookService.addBook("testAuthorBook2", "testName2", 22.2);
    }

    @After
    public void clear() {
        if (bookService.getBookById(book1.getBookId()) != null)
            bookService.deleteBookById(book1.getBookId());
        if (bookService.getBookById(book2.getBookId()) != null)
            bookService.deleteBookById(book2.getBookId());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetBookById_NormalCase() throws Exception {
        mvc.perform(get("/books/getBookByid/?id=" + book1.getBookId())).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("bookId", is(book1.getBookId()))))));
    }

    @Test
    public void testGetBookById_FakeIdCase() throws Exception {
        mvc.perform(get("/books/getBookByid/?id=-4123")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(0)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetBookByAuthor_NormalCase() throws Exception {
        mvc.perform(get("/books/getBookByauthor/?author=testAuthorBook1")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("author", is(book1.getAuthor()))))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetBookByAuthor_FewBooksWithOneAuthorCase() throws Exception {
        mvc.perform(get("/books/getBookByauthor/?author=testAuthorBook")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(2)))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("author", is(book1.getAuthor()))))))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("author", is(book2.getAuthor()))))));
    }

    @Test
    public void testGetBookByAuthor_FakeAuthorCase() throws Exception {
        mvc.perform(get("/books/getBookByauthor/?author=fakeBookAuthor")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(0)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetBookByName_NormalCase() throws Exception {
        mvc.perform(get("/books/getBookByname/?name=testName1")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(1)))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("name", is(book1.getName()))))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetBookByName_FewBooksWithOneNameCase() throws Exception {
        mvc.perform(get("/books/getBookByname/?name=testName")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(2)))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("name", is(book1.getName()))))))
                .andExpect(model().attribute("books", hasItem(allOf(hasProperty("name", is(book2.getName()))))));
    }

    @Test
    public void testGetBookByName_FakeNameCase() throws Exception {
        mvc.perform(get("/books/getBookByname/?name=fakeBookName")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp")).andExpect(model().attribute("books", hasSize(0)));
    }

    @Test
    public void deleteBookById_NormalCase() throws Exception {
        int booksSize = bookService.getAllBooks().size();
        mvc.perform(post("/books/deleteBook/").with(csrf()).param("id", String.valueOf(book1.getBookId())))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp"))
                .andExpect(model().attribute("books", hasSize(booksSize - 1)));
    }

}