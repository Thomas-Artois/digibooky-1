package com.switchfully.digibooky;

import com.switchfully.digibooky.controller.LoansController;
import com.switchfully.digibooky.domain.*;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.repository.LoansRepository;
import com.switchfully.digibooky.repository.UserRepository;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoanControllerTest {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansController loansController;

    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Test
    void givenloansController_whenLendBook_thenUserHasSuccesfullyLentABook() {
        //GIVEN

        //WHEN
        LoanDto loanDto =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "one@digibooky.com")
                        .header("password", "passwordOne")
                        .when()
                        .port(port)
                        .post("/loans?isbnNumber=9781573226127")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(LoanDto.class);

        assertThat(loanDto.getLoanId()).isNotBlank();
        assertEquals(loanDto.getMemberId(), userRepository.getUserByEmail("one@digibooky.com").getId());
        assertThat(loansRepository
                .getAllLoansByMember(userRepository.getUserByEmail("one@digibooky.com").getId()))
                .allSatisfy(loan -> assertThat(loan).isInstanceOf(Loan.class));
    }

    @Test
    void givenloansController_whenReturnBookThatIsLoaned_thenBookIsSuccesfullyReturned() {
        //GIVEN
        loansController.lendBook("one@digibooky.com", "passwordOne", "9781573226127");
        String loanId = loansRepository.findSingleLoanByIsbnNumber("9781573226127").getLoanId();

        //WHEN
        Message message =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "one@digibooky.com")
                        .header("password", "passwordOne")
                        .when()
                        .port(port)
                        .post("/loans/returns?loanId=" + loanId)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(Message.class);

        assertThat(message).isNotNull();
        assertThat(message.toString()).isEqualTo("Book was successfully returned");
    }

    @Test
    void givenLoansControllerWithLoans_whenLibrarianGetsAllLoansForMember_thenReturnAllLoansForThisMember() {
        //GIVEN
        loansController.getAllLoans("librarian@digibooky.com", "librarian", userRepository.getUserByEmail("three@digibooky.com").getId(), false);
        String memberId = userRepository.getUserByEmail("three@digibooky.com").getId();

        //WHEN
        List<LoanDto> listOfLoans =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "librarian@digibooky.com")
                        .header("password", "librarian")
                        .when()
                        .port(port)
                        .get("/loans?memberId=" + memberId)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", LoanDto.class);

        assertThat(listOfLoans).allSatisfy(loanDto -> assertThat(loanDto).isInstanceOf(LoanDto.class));
        assertThat(listOfLoans).hasSize(2);

    }
}
