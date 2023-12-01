package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.domain.Loan;
import com.switchfully.digibooky.dto.CreateLoanDto;
import com.switchfully.digibooky.dto.LoanDto;
import org.springframework.stereotype.Component;

@Component
public class LoansMapper {

    public Loan mapCreateLoanDtoToLoan(CreateLoanDto createLoanDto) {
        return new Loan(createLoanDto.getMemberId(), createLoanDto.getIsbnNumber());
    }

    public LoanDto mapLoanToLoanDto(Loan loan) {
        return new LoanDto(loan.getMemberId(), loan.getIsbnNumber(), loan.getLoanId(), loan.getDueDate());
    }
}
