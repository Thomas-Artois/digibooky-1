package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if(!bookRepository.isBookIdPresent(id)){
            throw new BookNotFoundException("Book Not Found");
        }
        return bookMapper.mapBookToBookDto(bookRepository.findSingleBookById(id));

    }

    public List<BookDto> searchBooks(String isbnNumber, String title, String author) {
        Stream<Book> bookList = bookRepository.findAllBooks().stream();

        if (isbnNumber != null) {
            bookList = findBooksByIsbn(isbnNumber, bookList);
        }
        if (title != null) {
            bookList = findBooksByTitle(title, bookList);

        }
        return bookList
                .map(book -> bookMapper.mapBookToBookDto(book))
                .collect(Collectors.toList());

    }

    public Stream<Book> findBooksByIsbn(String isbnNumber, Stream<Book> stream) {
        return stream.filter(
                book -> book.getIsbnNumber().contains(isbnNumber)
        );
    }

    public Stream<Book> findBooksByTitle(String title, Stream<Book> stream) {
        return stream.filter(
                book -> book.getTitle().contains(title)
        );
    }

    public Stream<BookDto> findBooksByAuthor(String author){
        return  bookRepository.findBooksByAuthor(author).stream()
                .map((book-> bookMapper.mapBookToBookDto(book)));
    }

}
