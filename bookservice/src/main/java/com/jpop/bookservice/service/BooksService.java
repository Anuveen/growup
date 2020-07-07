package com.jpop.bookservice.service;


import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BooksService
{
    @Autowired
    BooksRepository booksRepository;
    public List<Book> getAllBooks()
    {
        List<Book> books = new ArrayList<Book>();
        booksRepository.findAll().forEach(book1 -> books.add(book1));
        return books;
    }
    public Book getBooksById(int id)
    {
        return booksRepository.findById(id).get();
    }
    public void saveOrUpdate(Book book)
    {
        booksRepository.save(book);
    }
    public void delete(int id)
    {
        booksRepository.deleteById(id);
    }
    public void update(Book books, int bookId)
    {
        Book book = booksRepository.findById(bookId).get();
        book.setBookCategory(books.getBookCategory());
        booksRepository.save(book);
    }
}