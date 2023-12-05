package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.dto.UpdateBookDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class BookMapperTest {
    private final BookMapper bookMapper = new BookMapper();

    @Test
    void givenBook_whenMapBookToBookDto_thenGetBookDto() {
        // GIVEN
        String isbnNumber = "9781234567897";
        String title = "Title";
        String author = "Author";
        String summary = "Summary";

        Book book = new Book(isbnNumber, title, author, summary);

        // WHEN
        BookDto actual = bookMapper.mapBookToBookDto(book);

        //THEN
        assertThat(actual).isInstanceOf(BookDto.class);
        assertThat(actual.getIsbnNumber()).isEqualTo(isbnNumber);
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getAuthor()).isEqualTo(author);
        assertThat(actual.getSummary()).isEqualTo(summary);
    }

    @Test
    void givenCreateBookDto_whenMapCreateBookDtoToBook_thenGetBook() {
        // GIVEN
        String isbnNumber = "9781234567897";
        String title = "Title";
        String author = "Author";
        String summary = "Summary";

        CreateBookDto createBookDto = new CreateBookDto(isbnNumber, title, author, summary);

        // WHEN
        Book actual = bookMapper.mapCreateBookDtoToBook(createBookDto);

        //THEN
        assertThat(actual).isInstanceOf(Book.class);
        assertThat(actual.getIsbnNumber()).isEqualTo(isbnNumber);
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getAuthor()).isEqualTo(author);
        assertThat(actual.getSummary()).isEqualTo(summary);
    }

    @Test
    void givenUpdateBookDto_whenMapUpdateBookDtoToBook_thenGetBook() {
        // GIVEN
        String id = "2079eed5-a2ca-4375-8f41-f677de957a91";
        String isbnNumber = "9781234567897";
        String title = "Title";
        String author = "Author";
        String summary = "Summary";

        BookDto bookDto = new BookDto(id, isbnNumber, title, author, summary);
        UpdateBookDto updateBookDto = new UpdateBookDto(title, author, summary);

        // WHEN
        Book actual = bookMapper.mapUpdateBookDtoToBook(bookDto, updateBookDto);

        //THEN
        assertThat(actual).isInstanceOf(Book.class);
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getIsbnNumber()).isEqualTo(isbnNumber);
        assertThat(actual.getTitle()).isEqualTo(title);
        assertThat(actual.getAuthor()).isEqualTo(author);
        assertThat(actual.getSummary()).isEqualTo(summary);
    }
}