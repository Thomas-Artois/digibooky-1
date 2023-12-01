package com.switchfully.digibooky;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.CreateBookDto;
import com.switchfully.digibooky.dto.UpdateBookDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LibrarianControllerIntegrationTest {

    @LocalServerPort
    private int port;
    
    @Test
    void givenCreateBookDto_whenCreateBook_thenReturnBookDto(){

        //GIVEN
        String isbnNumber = "9780596528126";
        String title = "Fly";
        String author = "Heminghway";
        String summary= "AADf fivif sdfsd";

        CreateBookDto createBookDto = new CreateBookDto(isbnNumber, title, author, summary);
        
        //WHEN
        BookDto bookDto =
                RestAssured
                        .given()
                        .body(createBookDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "librarian@digibooky.com")
                        .header("password", "librarian")
                        .when()
                        .port(port)
                        .post("/manage-books")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(BookDto.class);

        assertThat(bookDto.getIsbnNumber()).isNotBlank();
        assertThat(bookDto.getIsbnNumber()).isEqualTo(createBookDto.getIsbnNumber());

        assertThat(bookDto.getTitle()).isNotBlank();
        assertThat(bookDto.getTitle()).isEqualTo(createBookDto.getTitle());

        assertThat(bookDto.getAuthor()).isNotBlank();
        assertThat(bookDto.getAuthor()).isEqualTo(createBookDto.getAuthor());

        assertThat(bookDto.getSummary()).isNotBlank();
        assertThat(bookDto.getSummary()).isEqualTo(createBookDto.getSummary());
    }

    @Test
    void givenUpdateBookDto_whenUpdateBook_thenReturnBookDto(){

        //GIVEN
        String id = "ab6b699e-21e3-4624-b236-9f8d9f6a22cf";
        String isbnNumber = "9785744653941";
        String title = "Fly";
        String author = "Heminghway";
        String summary= "AADf fivif sdfsd";

        UpdateBookDto updateBookDto = new UpdateBookDto(title, author, summary);

        //WHEN
        BookDto bookDto =
                RestAssured
                        .given()
                        .body(updateBookDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "librarian@digibooky.com")
                        .header("password", "librarian")
                        .when()
                        .port(port)
                        .put("/manage-books/" + id)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(BookDto.class);


        assertThat(bookDto.getTitle()).isNotBlank();
        assertThat(bookDto.getTitle()).isEqualTo(updateBookDto.getTitle());

        assertThat(bookDto.getAuthor()).isNotBlank();
        assertThat(bookDto.getAuthor()).isEqualTo(updateBookDto.getAuthor());

        assertThat(bookDto.getSummary()).isNotBlank();
        assertThat(bookDto.getSummary()).isEqualTo(updateBookDto.getSummary());
    }

    @Test
    void givenBookId_whenDeleteBook_thenBookIsDeleted() {

        //GIVEN
        String id = "ab6b699e-21e3-4624-b236-9f8d9f6a22cf";
        //WHEN
        RestAssured
                .given()
                .header("email", "librarian@digibooky.com")
                .header("password", "librarian")
                .when()
                .port(port)
                .delete("/manage-books/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
        //THEN
        RestAssured
                .given()
                .when()
                .port(port)
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
