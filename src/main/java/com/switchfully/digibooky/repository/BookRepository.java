package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    private Map<String, Book> books = new HashMap<>();

    public Collection<Book> findAllBooks(){
        return books.values();
    };

    public Book findSingleBookById(String id) {
        return books.get(id);
    }

    public List<Book> findBooksByIsbn(String isbnNumber) {
        return books.values().stream()
                .filter(book -> isbnNumber.equals(book.getIsbnNumber()))
                .collect(Collectors.toList());
    }
}
