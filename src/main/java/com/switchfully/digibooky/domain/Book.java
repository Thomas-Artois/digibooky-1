package com.switchfully.digibooky.domain;

import java.util.UUID;

public class Book {
    private String id;
    private String isbnNumber;
    private String title;
    private String author;
    private String summary;

    public Book(String isbnNumber, String title, String author, String summary) {
        this(UUID.randomUUID().toString(), isbnNumber, title, author, summary);
    }

    private Book(String id, String isbnNumber, String title, String author, String summary) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public Book setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;

        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;

        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Book setSummary(String summary) {
        this.summary = summary;

        return this;
    }
}
