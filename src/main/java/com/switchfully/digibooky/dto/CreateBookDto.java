package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.domain.IsbnValidation;

public class CreateBookDto {
    private final String isbnNumber;
    private final String title;
    private final String author;
    private final String summary;

    public CreateBookDto(String isbnNumber, String title, String author, String summary) {
        if (!IsbnValidation.isIsbn13(isbnNumber)) {
            throw new IllegalArgumentException("Incorrect ISBN13 format");
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
