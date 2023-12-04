package com.switchfully.digibooky.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Loan {
    private final String memberId;
    private final String isbnNumber;
    private final String loanId;
    private final LocalDate dueDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(memberId, loan.memberId) && Objects.equals(isbnNumber, loan.isbnNumber) && Objects.equals(loanId, loan.loanId) && Objects.equals(dueDate, loan.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, isbnNumber, loanId, dueDate);
    }
}
