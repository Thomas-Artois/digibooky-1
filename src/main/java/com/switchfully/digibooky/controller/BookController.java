package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService
                .findAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getSingleBookById(@PathVariable String id) {
        try {
            return bookService.findSingleBookById(id);
        } catch (BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/search")
    public List<BookDto> searchBooks(@RequestParam(required = false) String isbnNumber, @RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        if (isbnNumber == null && title == null && author == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return bookService.searchBooks(isbnNumber, title, author);
    }
}
