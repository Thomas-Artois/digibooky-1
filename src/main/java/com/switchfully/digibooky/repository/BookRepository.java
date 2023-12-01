package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.DuplicateIsbnNumberException;
import com.switchfully.digibooky.exception.IsbnNumberNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    public BookRepository() {
        List<Book> listOfBooks = List.of(
                new Book("ab6b699e-21e3-4624-b236-9f8d9f6a22cf","9785744653941", "Fly", "FFFF", "JJJJ"),
               new Book("488f2c99-6cad-405c-8ac3-99ab96575f9d","9784578421634","Heaven", "AAA", "SADD dFF")
        );
        listOfBooks.forEach(this::create);
    }

    public Collection<Book> findAllBooks() {
        return books.values();
    }

    public Book findSingleBookById(String id) {
        return books.get(id);
    }

    public Book findSingleBookByIsbnNumber(String isbnNumberToFind) throws BookNotFoundException{
        System.out.println("bookRepository: " + isbnNumberToFind);
        return books.values().stream()
                .filter(book -> isbnNumberToFind.equals(book.getIsbnNumber()))
                .findFirst()
                .orElseThrow(BookNotFoundException::new);
    }

    public boolean isBookIdPresent(String id) {
        return books.containsKey(id);
    }

    public void checkIfIsbnNumberIsDuplicate(String isbnNumber) throws DuplicateIsbnNumberException {
        System.out.println("Check isbn number is duplicate" + isbnNumber);
        if (books.values().stream().anyMatch(book ->  book.getIsbnNumber().equals(isbnNumber))) {
            throw new DuplicateIsbnNumberException();
        }
    }

    public void checkIfIsbnNumberExists(String isbnNumber) throws IsbnNumberNotFoundException {
        System.out.println("Check isbn number exists" + isbnNumber);
        if (books.values().stream().noneMatch(book ->  book.getIsbnNumber().equals(isbnNumber))) {
            throw new IsbnNumberNotFoundException();
        }
    }

    public Book create(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    public Book update(Book book) {
        books.put(book.getId(), book);
        return book;
    }
}
