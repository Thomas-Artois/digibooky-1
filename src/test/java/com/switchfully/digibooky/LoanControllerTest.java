//package com.switchfully.digibooky;
//
//import com.switchfully.digibooky.controller.LoansController;
//import com.switchfully.digibooky.service.LoansService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.test.annotation.DirtiesContext;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class LoanControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Test
//    void givenloansController_whenLendBook_thenLoansServiceLendBookIsCalled() {
//        LoansService loansService = Mockito.mock(LoansService.class);
//
//        //WHEN
//        LoansController loansController = new LoansController(loansService);
//        loansController.lendBook("testing", "doesntmatter");
//
//        //THEN
//        Mockito.verify(loansService).lendBook("testing", "doesntmatter");
//    }
//
//    @Test
//    void givenloansRepositoryWithAvailableBook_whenCheckingIfBookIsLentOutAlready_thenLendBook() {
//
//
//    }
//
//}
