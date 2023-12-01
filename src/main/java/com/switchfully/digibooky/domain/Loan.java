package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private String memberId;
    private String isbnNumber;
    private String loanId;
    private LocalDate dueDate;

    private static final int DEFAULT_LOAN_DURATION_IN_WEEKS = 3;

    public Loan(String memberId, String isbnNumber) {
        this.memberId = memberId;
        this.isbnNumber = isbnNumber;
        loanId = UUID.randomUUID().toString();
        dueDate = LocalDate.now().plusWeeks(DEFAULT_LOAN_DURATION_IN_WEEKS);
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

    public LocalDate getDueDate() {
        return dueDate;
    }
}
