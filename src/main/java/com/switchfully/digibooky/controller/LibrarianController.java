package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
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




}
