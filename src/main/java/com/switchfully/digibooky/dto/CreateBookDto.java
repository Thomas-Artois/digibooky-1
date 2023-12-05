package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.domain.IsbnValidation;
import com.switchfully.digibooky.exception.NotAValidIsbnException;
import jakarta.validation.constraints.NotEmpty;

public class CreateBookDto {
    @NotEmpty
    private final String isbnNumber;
    @NotEmpty
    private final String title;
    @NotEmpty
    private final String author;

    private final String summary;

    public CreateBookDto(String isbnNumber, String title, String author, String summary) {
        if (!IsbnValidation.isIsbn13(isbnNumber)) {
            throw new NotAValidIsbnException();
        }
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }
}
