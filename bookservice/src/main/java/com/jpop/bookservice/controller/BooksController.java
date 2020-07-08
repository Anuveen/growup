package com.jpop.bookservice.controller;

import com.jpop.bookservice.exception.BookException;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.service.BooksService;
import com.jpop.bookservice.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
