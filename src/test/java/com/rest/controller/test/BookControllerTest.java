package com.rest.controller.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.rest.controller.BookController;
import com.rest.entity.Book;
import com.rest.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookServiceMock;

    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        book1 = new Book("TESTING Author 1", "TESTING Name 1", 11.1);
        book2 = new Book("TESTING Author 2", "TESTING Name 2", 22.2);
        book3 = new Book("Author 3", "Name 3", 33.3);
    }

    @Test
    public void testGetAllBooks_NormalCase() throws Exception {
        when(bookServiceMock.getAllBooks()).thenReturn(Arrays.asList(book1, book2, book3));

        mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(view().name("books"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp"));

        verify(bookServiceMock, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookServiceMock);
    }

}