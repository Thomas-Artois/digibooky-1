package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Book;
import com.switchfully.digibooky.exception.BookNotFoundException;
import com.switchfully.digibooky.exception.DuplicateIsbnNumberException;
import com.switchfully.digibooky.exception.IsbnNumberNotFoundException;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest {

    private BookRepository bookRepository;
    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    void givenBookRepository_whenFindAllBooks_thenGetAllBooks() {
        Collection<Book> books = bookRepository.findAllBooks();
        assertEquals(5, books.size());
    }

    @Test
    void givenBookRepository_whenFindSingleBookById_thenGetSingleBook(){
        Book book = bookRepository.findSingleBookById("ab6b699e-21e3-4624-b236-9f8d9f6a22cf");
        assertNotNull(book);
        assertEquals("Fly", book.getTitle());
    }

    @Test
    void givenBookRepository_whenFindSingleBookByIsbnNumber_thenGetSingleBookByIsbnNumber() {
        try{
            Book book = bookRepository.findSingleBookByIsbnNumber("9785744653941");
            assertNotNull(book);
            assertEquals("Fly", book.getTitle());
        } catch(BookNotFoundException e) {
            System.out.println("Book not founded");
        }
    }

    @Test
    void whenFindSingleBookBy_whenNonExistingIsbnNumber_thenBookNotFound() {
        assertThrows(BookNotFoundException.class, () ->
                bookRepository.findSingleBookByIsbnNumber("non-existing-isbn"));
    }

    @Test
    void givenBookRepository_whenBookIdIsPresent_thenGetBookId() {
        assertTrue(bookRepository.isBookIdPresent("488f2c99-6cad-405c-8ac3-99ab96575f9d"));
    }


    @Test
    void givenBookRepository_whenBookIdIsNotPresent_thenDontGetBookId(){
        assertFalse(bookRepository.isBookIdPresent("not-existing-id"));
    }
    @Test
    void givenBookRepository_whenIsbnNumberIsDuplicate_thenCheckIsbnNumber() {
        assertThrows(DuplicateIsbnNumberException.class, () ->
                bookRepository.checkIfIsbnNumberIsDuplicate("9785744653941"));

    }

    @Test
    void givenIsbnNumber_whenIsbnNumberExists_thenCheckIsbnNumberExists() {
        assertDoesNotThrow(() -> bookRepository.checkIfIsbnNumberExists("9785744653941"));
    }
    @Test
    void givenIsbnNumber_whenIsbnNumberDoesntExist_thenCheckIsbnNumberDoesntExist() {
        assertThrows(IsbnNumberNotFoundException.class, () ->
                bookRepository.checkIfIsbnNumberExists("non-existing-isbn"));

    }
    @Test
    void  givenNewBook_whenCreate_thenBookIsCreatedWithId() {
        Book newBook = new Book("new-book-id", "1234567890", "New Book", "Author", "Description");
        Book createdBook = bookRepository.create(newBook);
        assertNotNull(createdBook);
        assertEquals("new-book-id", createdBook.getId());
    }

    @Test
    void givenBookT_whenUpdate_thenBookIsUpdatedWithTitle() {
        Book bookToUpdate = new Book("ab6b699e-21e3-4624-b236-9f8d9f6a22cf", "9785744653941", "Updated Title", "Author", "Description");
        Book updatedBook = bookRepository.update(bookToUpdate);
        assertNotNull(updatedBook);
        assertEquals("Updated Title", updatedBook.getTitle());
    }

    @Test
    void givenBookId_whenDelete_thenBookIsMarkedAsDeleted() {
        bookRepository.delete("ab6b699e-21e3-4624-b236-9f8d9f6a22cf");
        assertTrue(bookRepository.findSingleBookById("ab6b699e-21e3-4624-b236-9f8d9f6a22cf").isDeleted());
    }

    @Test
    void givenBookId_whenRestore_thenBookIsRestored() {
        bookRepository.restore("ab6b699e-21e3-4624-b236-9f8d9f6a22cf");
        assertFalse(bookRepository.findSingleBookById("ab6b699e-21e3-4624-b236-9f8d9f6a22cf").isDeleted());
    }
}



