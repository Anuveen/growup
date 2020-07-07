package com.jpop.bookservice.repository;


import com.jpop.bookservice.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BooksRepository extends CrudRepository<Book, Integer>
{
}
