package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.dto.UpdateBookDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.NotALibrarianException;
import com.switchfully.digibooky.exception.PasswordIsIncorrectException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.service.BookService;
import com.switchfully.digibooky.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestHeader String email, @RequestHeader String password, @Valid @RequestBody CreateBookDto createBookDto) {
        userService.checkIfUserIsLibrarian(email, password);

        return bookService.createBook(createBookDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@RequestHeader String email, @RequestHeader String password,@PathVariable String id, @Valid @RequestBody UpdateBookDto updateBookDto) {
        userService.checkIfUserIsLibrarian(email, password);
        BookDto bookDto = bookService.findSingleBookById(id);

        return bookService.updateBook(bookDto, updateBookDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@RequestHeader String email, @RequestHeader String password,@PathVariable String id){
        userService.checkIfUserIsLibrarian(email, password);
        BookDto bookDto = bookService.findSingleBookById(id);
        bookService.deleteBook(bookDto);
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restoreBook(@RequestHeader String email, @RequestHeader String password,@PathVariable String id){
        userService.checkIfUserIsLibrarian(email, password);
        BookDto bookDto = bookService.findDeletedBookById(id);
        bookService.restoreBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.findAllBooks();
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
