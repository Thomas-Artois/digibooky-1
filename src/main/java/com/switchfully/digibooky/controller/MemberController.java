package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.exception.*;
import com.switchfully.digibooky.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/members")
public class MemberController {
    private UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createMember(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.createMember(createUserDto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> viewAllMembers(@RequestHeader String email, @RequestHeader String password) {
        userService.checkIfUserIsAdmin(email, password);

        return userService.getAllMembers();
    }

    @ExceptionHandler(EmailExistsException.class)
    private void emailExistsException(EmailExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(SocialSecurityNumberExistsException.class)
    private void socialSecurityNumberExistsException(SocialSecurityNumberExistsException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(PasswordIsIncorrectException.class)
    private void passwordIsIncorrectException(PasswordIsIncorrectException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    private void userNotFoundException(UserNotFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NotAnAdminException.class)
    private void notAnAdminException(NotAnAdminException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
}