package com.switchfully.digibooky.dto;

import java.time.LocalDate;
import java.util.UUID;

public class LoanDto {
    private String memberId;
    private String isbnNumber;
    private String loanId;
    private LocalDate dueDate;

    private static final int DEFAULT_LOAN_DURATION_IN_WEEKS = 3;

    public LoanDto(String memberId, String isbnNumber, String loanId, LocalDate dueDate) {
        this.memberId = memberId;
        this.isbnNumber = isbnNumber;
        this.loanId = loanId;
        this.dueDate = dueDate;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getLoanId() {
        return loanId;
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "memberId='" + memberId + '\'' +
                ", isbnNumber='" + isbnNumber + '\'' +
                ", loanId='" + loanId + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
