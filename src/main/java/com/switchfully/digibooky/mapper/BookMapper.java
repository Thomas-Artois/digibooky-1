package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.dto.UpdateBookDto;
import org.springframework.stereotype.Component;



@Component
public class BookMapper {

    public BookDto mapBookToBookDto(Book book){
        return new BookDto(book.getId(), book.getIsbnNumber(), book.getTitle(), book.getAuthor(), book.getSummary());
    }

    public Book mapCreateBookDtoToBook(CreateBookDto createBookDto){
        return new Book(createBookDto.getIsbnNumber(), createBookDto.getTitle(), createBookDto.getAuthor(), createBookDto.getSummary());
    }

    public Book mapUpdateBookDtoToBook(BookDto bookDto, UpdateBookDto updateBookDto){
        return new Book(bookDto.getId(), bookDto.getIsbnNumber(), updateBookDto.getTitle(), updateBookDto.getAuthor(), updateBookDto.getSummary());
    }
}
