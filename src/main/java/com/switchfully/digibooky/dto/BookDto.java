package com.switchfully.digibooky.dto;

import java.util.UUID;

public class BookDto {
    private final UUID id;
    private final String isbnNumber;
    private final String title;
    private final String author;
    private final String summary;

    public BookDto(UUID id, String isbnNumber, String title, String author, String summary) {
        this.id = id;
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
