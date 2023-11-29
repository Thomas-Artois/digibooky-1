package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Component;



@Component
public class BookMapper {

    public BookDto mapBookToBookDto(Book book){
        return new BookDto(book.getId(), book.getIsbnNumber(), book.getTitle(), book.getAuthor(), book.getSummary());
    }
}
