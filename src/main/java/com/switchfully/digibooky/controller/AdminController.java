package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.exception.NotAnAdminException;
import com.switchfully.digibooky.exception.PasswordIsIncorrectException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Validated
@RequestMapping(path = "/admins")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createAdmin(@RequestHeader String email, @RequestHeader String password, @Valid @RequestBody CreateUserDto createUserDto) {
        userService.checkIfUserIsAdmin(email, password);

        return userService.createAdmin(createUserDto);
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
