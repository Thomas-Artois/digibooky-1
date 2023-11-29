package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService
                .findAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getSingleBookById(@PathVariable String id) {
        return bookService.findSingleBookById(id);
    }

    @GetMapping("/isbn/{isbnNumber}")
    public List <BookDto> getBooksByIsbn(@PathVariable String isbnNumber){
        return bookService.findBooksByIsbn(isbnNumber);
    }
}
