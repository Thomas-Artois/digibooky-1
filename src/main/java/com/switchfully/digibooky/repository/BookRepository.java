package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    public Collection<Book> findAllBooks() {
        return books.values();
    }

    public Book findSingleBookById(String id) {
        return books.get(id);
    }

    public boolean isBookIdPresent(String id) {
        return books.containsKey(id);
    }
}
