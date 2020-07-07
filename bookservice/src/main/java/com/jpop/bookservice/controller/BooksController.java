package com.jpop.bookservice.controller;

import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BooksController
{
    @Autowired
    BooksService booksService;

    @GetMapping("/book")
    private List<Book> getAllBooks()
    {
        return booksService.getAllBooks();
    }

    @GetMapping("/book/{bookId}")
    private Book getBooks(@PathVariable("bookId") int bookId)
    {
        return booksService.getBooksById(bookId);
    }

    @DeleteMapping("/book/{bookId}")
    private void deleteBook(@PathVariable("bookId") int bookId)
    {
        booksService.delete(bookId);
    }

    @PostMapping("/book")
    private int saveBook(@RequestBody Book book)
    {
        booksService.saveOrUpdate(book);
        return book.getBookId();
    }

    @PutMapping("/book/{bookId}")
    private Book update(@PathVariable("bookId") int bookId,
                        @RequestBody Book book)
    {
        booksService.update(book, bookId);
        return book;
    }
}
