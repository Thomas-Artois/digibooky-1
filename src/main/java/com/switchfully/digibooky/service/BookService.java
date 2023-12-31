package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.domain.IsbnValidation;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.dto.UpdateBookDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.DuplicateIsbnNumberException;
import com.switchfully.digibooky.exception.NotAValidIsbnException;
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
        if (!bookRepository.isBookIdPresent(id)) {
            throw new BookNotFoundException();
        }
        Book book = bookRepository.findSingleBookById(id);
        if (book.isDeleted()) {
            throw new BookNotFoundException();
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
        if (author != null) {
            bookList = findBooksByAuthor(author, bookList);
        }
        return bookList
                .map(book -> bookMapper.mapBookToBookDto(book))
                .collect(Collectors.toList());
    }

    private Stream<Book> findBooksByIsbn(String isbnNumber, Stream<Book> stream) {
        return stream.filter(
                book -> book.getIsbnNumber().contains(isbnNumber)
        );
    }

    private Stream<Book> findBooksByTitle(String title, Stream<Book> stream) {
        return stream.filter(
                book -> book.getTitle().contains(title)
        );
    }

    private Stream<Book> findBooksByAuthor(String author, Stream<Book> stream) {
        return stream.filter(
                book -> book.getAuthor().contains(author)
        );
    }

    public BookDto createBook(CreateBookDto createBookDto) throws DuplicateIsbnNumberException, NotAValidIsbnException {
        if (!IsbnValidation.isIsbn13(createBookDto.getIsbnNumber())) {
            throw new NotAValidIsbnException();
        }

        bookRepository.checkIfIsbnNumberIsDuplicate(createBookDto.getIsbnNumber());

        Book book = bookRepository.create(bookMapper.mapCreateBookDtoToBook(createBookDto));

        return bookMapper.mapBookToBookDto(book);
    }

    public BookDto updateBook(BookDto bookDto, UpdateBookDto updateBookDto) {
        Book book = bookRepository.update(bookMapper.mapUpdateBookDtoToBook(bookDto, updateBookDto));
        return bookMapper.mapBookToBookDto(book);
    }

    public void deleteBook(BookDto bookDto) {
        bookRepository.delete(bookDto.getId());
    }

    public BookDto findDeletedBookById(String id) {
        if (!bookRepository.isBookIdPresent(id)) {
            throw new BookNotFoundException();
        }
        return bookMapper.mapBookToBookDto(bookRepository.findSingleBookById(id));
    }

    public void restoreBook(BookDto bookDto) {
        bookRepository.restore(bookDto.getId());
    }
}
