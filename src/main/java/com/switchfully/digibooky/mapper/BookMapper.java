package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.dto.CreateBookDto;
import org.springframework.stereotype.Component;



@Component
public class BookMapper {

    public BookDto mapBookToBookDto(Book book){
        return new BookDto(book.getId(), book.getIsbnNumber(), book.getTitle(), book.getAuthor(), book.getSummary());
    }

    public Book mapCreateBookDtoToBook(CreateBookDto createBookDto){
        return new Book(createBookDto.getIsbnNumber(), createBookDto.getTitle(), createBookDto.getAuthor(), createBookDto.getSummary());
    }
}
