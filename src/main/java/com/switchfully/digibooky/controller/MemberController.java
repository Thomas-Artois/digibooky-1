package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.LoanDto;
import com.switchfully.digibooky.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(path = "/lend", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public LoanDto lendBook(@RequestParam String memberId, @RequestParam String isbnNumber) {
        return memberService.lendBook(memberId,isbnNumber);
    }

}
