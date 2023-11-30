package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createMember(@Valid @RequestBody CreateUserDto createUserDto) {
        return userService.createMember(createUserDto);
    }

    @PostMapping(path = "/admin", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createAdmin(@RequestHeader String email, @RequestHeader String password, @Valid @RequestBody CreateUserDto createUserDto) {
        userService.checkIfUserIsAdmin(email, password);

        return userService.createAdmin(createUserDto);
    }

    @PostMapping(path = "/librarian", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createLibrarian(@RequestHeader String email, @RequestHeader String password, @Valid @RequestBody CreateUserDto createUserDto) {
        userService.checkIfUserIsAdmin(email, password);

        return userService.createLibrarian(createUserDto);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> viewAllMembers(@RequestHeader String email, @RequestHeader String password) {
        userService.checkIfUserIsAdmin(email, password);

        return userService.getAllMembers();

    }
}
