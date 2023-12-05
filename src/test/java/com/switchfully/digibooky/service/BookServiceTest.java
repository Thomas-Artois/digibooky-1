package com.switchfully.digibooky.service;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.NotAValidIsbnException;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {
    BookService bookService;
    BookMapper bookMapper;
    BookRepository bookRepository;

    @BeforeEach
    void setUpBookService() {
        bookRepository = new BookRepository();
        bookMapper = new BookMapper();
        bookService = new BookService(bookMapper, bookRepository);
    }

    @Test
    void whenFindAllBooks_thenReturnBookDtoList() {
        // when
        List<BookDto> actual = bookService.findAllBooks();

        // then
        assertNotNull(actual);
        assertThat(actual.get(0)).isInstanceOf(BookDto.class);
        assertEquals(5, actual.size());
        assertEquals("Heaven", actual.get(0).getTitle());
        assertEquals("AAA", actual.get(0).getAuthor());
        assertEquals("SADD dFF", actual.get(0).getSummary());
    }

    @Test
    void givenValidBook_whenFindSingleBookById_thenReturnBookDto() {
        //given
        Book newBook = new Book("54531313", "Book Title", "Book Author", "Book Summary");
        bookRepository.create(newBook);

        //when
        BookDto actual = bookService.findSingleBookById(newBook.getId());

        //then
        assertThat(actual.getId()).isEqualTo(newBook.getId());
    }

    @Test
    void givenInvalidId_whenFindSingleBookById_ThenThrowBookNotFound() {
        // given
        String id = "this is definitely not a book id";

        // when + then
        assertThatThrownBy(() -> bookService.findSingleBookById(id)).isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void givenDeletedBook_whenFindSingleBookById_thenThrowBookNotFound() {
        // given + when
        Book deletedBook = new Book("54531313", "Book Title", "Book Author", "Book Summary");
        bookRepository.create(deletedBook);
        bookRepository.delete(deletedBook.getId());

        // then
        assertThatThrownBy(()->
                bookService.findSingleBookById(deletedBook.getId()
                )).isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void givenValidQuery_whensearchBooks_thenReturnBookDtoList() {
        // given
        Book newBook = new Book("978-7-3161-1316-7", "Book Title", "Book Author", "Book Summary");
        bookRepository.create(newBook);

        // when
        List<BookDto> actual = bookService.searchBooks(newBook.getIsbnNumber(), newBook.getTitle(), newBook.getAuthor());

        // then
        assertThat(actual.get(0).getId()).isEqualTo(newBook.getId());
    }

    @Test
    void givenCreateBookDto_whenCreateBook_thenReturnBookDtoOfNewBook() {
        // given
        CreateBookDto createBookDto = new CreateBookDto("978-2-4659-3832-6","title", "author", "summary");

        // when
        BookDto bookDto = bookService.createBook(createBookDto);

        // then
        assertThat(bookDto.getIsbnNumber()).isEqualTo(createBookDto.getIsbnNumber());
    }

    @Test
    void givenCreateBookDtoWithInvalidIsbn_whenCreateBook_thenReturnNovtAValidIsbn()  {
        // given
        CreateBookDto createBookDto = new CreateBookDto("this is not correct","title", "author", "summary");

        // then
        assertThatThrownBy(() -> bookService.createBook(createBookDto)).isInstanceOf(NotAValidIsbnException.class);
    }
}
