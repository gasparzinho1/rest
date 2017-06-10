package helper;

import java.util.ArrayList;
import java.util.List;

import com.rest.entity.Book;

public class BookHelper {

    public static Book createBook() {
        return new Book("testing author", "testing name", 11.1);
    }

    public static List<Book> createBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("testing author 1", "testing name 1", 11.1));
        books.add(new Book("testing author 2", "testing name 2", 22.2));
        books.add(new Book("author 3", "name 3", 33.3));
        return books;
    }

}