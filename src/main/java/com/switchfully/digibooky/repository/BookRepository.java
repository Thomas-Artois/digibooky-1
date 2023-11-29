package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {
    private Map<String, Book> books = new HashMap<>();
}
