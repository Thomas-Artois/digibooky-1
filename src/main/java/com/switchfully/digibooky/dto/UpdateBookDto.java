package com.switchfully.digibooky.dto;

public class UpdateBookDto {

    private final String title;
    private final String author;
    private final String summary;

    public UpdateBookDto(String title, String author, String summary) {
        this.title = title;
        this.author = author;
        this.summary = summary;
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
