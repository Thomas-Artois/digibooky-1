package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.exception.IsbnNumberExistsException;
import com.switchfully.digibooky.exception.SocialSecurityNumberExistsException;
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

    public void checkIfIsbnNumberExists(String isbnNumber) throws IsbnNumberExistsException {
        if (books.values().stream().anyMatch(book -> book.getIsbnNumber().equals(isbnNumber))) {
            throw new IsbnNumberExistsException();
        }
    }
    public Book create(Book book) {
        books.put(book.getId(), book);
        return book;
    }
}
