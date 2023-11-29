package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private Map<String, Book> books = new HashMap<>();

    public Collection<Book> findAllBooks() {
        return books.values();
    }

    public Book findSingleBookById(String id) {
        return books.get(id);
    }

    public List<Book> findBooksByIsbn(String isbnNumber) {
        return books.values().stream()
                .filter(book -> book.getIsbnNumber().contains(isbnNumber))
                .collect(Collectors.toList());
    }
}
