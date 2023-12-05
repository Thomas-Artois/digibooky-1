package com.switchfully.digibooky;

import com.switchfully.digibooky.controller.LoansController;
import com.switchfully.digibooky.domain.*;
import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.mapper.LoansMapper;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LoansRepository;
import com.switchfully.digibooky.repository.UserRepository;
import com.switchfully.digibooky.service.LoansService;
import com.switchfully.digibooky.service.UserService;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LoanControllerTest {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansService loansService;

//    @Autowired
//    private UserService userService;

    @Autowired
    private LoansController loansController;

    @Autowired
    private UserRepository userRepository;

//    UserRepository userRepository = new UserRepository();
//    LoansRepository loansRepository = new LoansRepository();
//    BookRepository bookRepository = new BookRepository();
//    LoansMapper loansMapper = new LoansMapper();
//    UserMapper userMapper = new UserMapper();
//    LoansService loansService = new LoansService(loansRepository, loansMapper, bookRepository );
//    UserService userService = new UserService(userMapper, userRepository);
//    LoansController loansController = new LoansController(loansService, userService);

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

        System.out.println(loansService.getAllLoans("8ec158fc-9b1b-46af-9e3f-5c99ee9752a9"));
        System.out.println(loansService.getSingleLoanByIsbnNumber("9781573226127"));

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
                        .post("/loans/returns?loanId="+loanId)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(Message.class);

        assertThat(message).isNotNull();
        assertThat(message.toString()).isEqualTo("Book was successfully returned");
    }



}
