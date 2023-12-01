package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.mapper.MemberMapper;
import com.switchfully.digibooky.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public LoanDto lendBook(String memberId, String isbnNumber) {
        return memberMapper.mapLoanToLoanDto(memberRepository.lendBook(memberId,isbnNumber));
    }

}
