package com.switchfully.digibooky.dto;

import java.time.LocalDate;

public class CreateLoanDto {
    private String memberId;
    private String isbnNumber;

    private static final int DEFAULT_LOAN_DURATION_IN_WEEKS = 3;

    public CreateLoanDto(String memberId, String isbnNumber) {
        this.memberId = memberId;
        this.isbnNumber = isbnNumber;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public String getMemberId() {
        return memberId;
    }

}
