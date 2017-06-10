package com.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rest.entity.Book;
import com.rest.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/getBookByid")
    public String getBookById(int id, Model model) {
        List<Book> books = new ArrayList<>();
        Book book = bookService.getBookById(id);
        if (book != null)
            books.add(book);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/getBookByauthor")
    public String getBooksByAuthor(String author, Model model) {
        model.addAttribute("books", bookService.getBooksByAuthor(author));
        return "books";
    }

    @GetMapping("/getBookByname")
    public String getBooksByName(String name, Model model) {
        model.addAttribute("books", bookService.getBooksByName(name));
        return "books";
    }

    @PostMapping("/deleteBook")
    public String deleteBookById(int id, Model model) {
        bookService.deleteBookById(id);
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @PostMapping("/addBook")
    public String addOrUpdateBook(int id, String author, String name, Double price, String update, Model model) {
        List<Book> books = new ArrayList<>();

        if (update.equals("true"))
            books.add(bookService.updateBook(id, author, name, price));
        else
            books.add(bookService.addBook(author, name, price));

        model.addAttribute("books", books);
        return "books";
    }

}