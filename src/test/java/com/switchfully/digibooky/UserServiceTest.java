package com.switchfully.digibooky;

import com.switchfully.digibooky.exception.NotAMemberException;
import com.switchfully.digibooky.exception.NotAnAdminException;
import com.switchfully.digibooky.exception.PasswordIsIncorrectException;
import com.switchfully.digibooky.exception.UserNotFoundException;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.repository.UserRepository;
import com.switchfully.digibooky.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService userService;
    UserMapper userMapper;
    UserRepository userRepository;

    @BeforeEach
    void setUpUserService() {
        userMapper = new UserMapper();
        userRepository = new UserRepository();
        userService = new UserService(userMapper, userRepository);
    }

    @Test
    void givenAdmin_whenCheckIfUserIsMember_thenThrowNotAMemberException() {
        //GIVEN

        //WHEN & THEN
        assertThrows(NotAMemberException.class, () -> userService.checkIfUserIsMember("admin@digibooky.com", "admin"));
    }

    @Test
    void whenCheckIfLibrarianIsAdmin_thenThrowNotAnAdminException() {
        //GIVEN

        //WHEN & THEN
        assertThrows(NotAnAdminException.class, () -> userService.checkIfUserIsAdmin("librarian@digibooky.com", "librarian"));
    }

    @Test
    void givenInvalidEmail_whenCheckIfUserExists_thenThrowUserNotFoundException() {
        //GIVEN

        //WHEN & THEN
        assertThrows(UserNotFoundException.class, () -> userService.checkIfUserExists(userRepository.getUserByEmail("switchfully@digibooky.com").getId()));
    }

    @Test
    void givenExistingMember_whenCheckIfPasswordIsCorrectWithIncorrectPassword_thenThrowPasswordIsIncorrectException() {
        //GIVEN

        //WHEN & THEN
        assertThrows(PasswordIsIncorrectException.class, () -> userService.checkIfUserIsMember("one@digibooky.com", "wrongpassword"));
    }




}
