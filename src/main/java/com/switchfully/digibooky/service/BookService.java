package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookMapper bookMapper;
    private BookRepository bookRepository;


    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookDto> findAllBooks() {
        return bookRepository.findAllBooks().stream()
                .map(book -> bookMapper.mapBookToBookDto(book))
                .collect(Collectors.toList());
    }

    public BookDto findSingleBookById(String id) {
        return bookMapper.mapBookToBookDto(bookRepository.findSingleBookById(id));
    }

    public List<BookDto> findBooksByIsbn(String isbnNumber) {
        return bookRepository.findBooksByIsbn(isbnNumber).stream()
                .map(book -> bookMapper.mapBookToBookDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title).stream()
                .map((book -> bookMapper.mapBookToBookDto(book)))
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksByAuthor(String author){
        return  bookRepository.findBooksByAuthor(author).stream()
                .map((book-> bookMapper.mapBookToBookDto(book)))
                .collect(Collectors.toList());
    }
}
