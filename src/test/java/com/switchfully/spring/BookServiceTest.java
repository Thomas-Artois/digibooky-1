package com.switchfully.spring;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {
    BookService bookService;
    BookMapper bookMapper;
    BookRepository bookRepositoryMock;

    @BeforeEach
    void setUpBookService() {
        bookRepositoryMock = Mockito.mock(BookRepository.class);
        bookMapper = new BookMapper();
        bookService = new BookService(bookMapper, bookRepositoryMock);
    }

    @Test
    void whenFindAllBooks_thenReturnBookDtoList() {
        // given
        List<Book> bookList= new ArrayList<>(
                List.of(
                        new Book("54531313", "Book Title", "Book Author", "Book Summary"),
                        new Book("46546", "Book Title 2", "Book Author 2", "Book Summary 2")
                ));

        // when
        bookService.findAllBooks();

        // then
        Mockito.verify(bookRepositoryMock).findAllBooks();

    }

    @Test
    void whenFindOneBook_thenReturnBook(){
        //given
        Book newBook = new Book("54531313", "Book Title", "Book Author", "Book Summary");
        Mockito.when(bookRepositoryMock.findSingleBookById(newBook.getId())).thenReturn(newBook);

        //when
        BookDto actual = bookService.findSingleBookById(newBook.getId());


        //then
        assertThat(actual.getId()).isEqualTo(newBook.getId());

    }
}
