package com.switchfully.digibooky.domain;

import java.util.UUID;

public class Book {
    private String id;
    private String isbnNumber;
    private String title;
    private String author;
    private String summary;
    private boolean deleted = false;

    public Book(String isbnNumber, String title, String author, String summary) {
        this(UUID.randomUUID().toString(), isbnNumber, title, author, summary);
    }

    public Book(String id, String isbnNumber, String title, String author, String summary) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }

    public Book(String id, String isbnNumber, String title, String author, String summary, boolean deleted) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
