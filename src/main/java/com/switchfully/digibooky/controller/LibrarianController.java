package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.*;
import com.switchfully.digibooky.service.BookService;
import com.switchfully.digibooky.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/manage-books")
public class LibrarianController {

    private BookService bookService;
    private UserService userService;

    public LibrarianController(BookService bookService, UserService userService) {
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




}
