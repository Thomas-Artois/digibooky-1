package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.exception.EmailExistsException;
import com.switchfully.digibooky.exception.SocialSecurityNumberExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserRepositoryTest {
    @Test
    void givenUserRepositoryAndUser_whenCreateUser_thenUserIsInRepository() {
        // GIVEN
        UserRepository userRepository = new UserRepository();

        String socialSecurityNumber = "XXXXXXXX";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "firstName.lastName@digibooky.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        Role role = Role.MEMBER;
        String password = "password";

        User user = new User(socialSecurityNumber, firstName, lastName, email, address, role, password);

        // WHEN
        userRepository.create(user);

        // THEN
        assertThat(userRepository.getUserById(user.getId())).isEqualTo(user);
        assertThat(userRepository.getUserByEmail(user.getEmail())).isEqualTo(user);
    }

    @Test
    void givenUserRepositoryAndUser_whenCheckingIfSocialSecurityNumberExists_thenThrowSocialSecurityNumberExistsException() {
        UserRepository userRepository = new UserRepository();

        String socialSecurityNumber = "XXXXXXXX";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "firstName.lastName@digibooky.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        Role role = Role.MEMBER;
        String password = "password";

        User user = new User(socialSecurityNumber, firstName, lastName, email, address, role, password);
        userRepository.create(user);

        // WHEN + THEN
        assertThatThrownBy(() -> userRepository.checkIfSocialSecurityNumberExists(user.getSocialSecurityNumber())).isInstanceOf(SocialSecurityNumberExistsException.class);
    }

    @Test
    void givenUserRepositoryAndUser_whenCheckingIfEmailExists_thenThrowEmailExistsException() {
        UserRepository userRepository = new UserRepository();

        String socialSecurityNumber = "XXXXXXXX";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "firstName.lastName@digibooky.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        Role role = Role.MEMBER;
        String password = "password";

        User user = new User(socialSecurityNumber, firstName, lastName, email, address, role, password);
        userRepository.create(user);

        // WHEN + THEN
        assertThatThrownBy(() -> userRepository.checkIfEmailExists(user.getEmail())).isInstanceOf(EmailExistsException.class);
    }

    @Test
    void givenUserRepository_whenGetAllMembers_thenGetAllMembers() {
        // GIVEN
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserRepository userRepository = new UserRepository();

        // WHEN
        List<User> actual = userRepository.getAllMembers();

        // THEN
        assertThat(actual.size()).isEqualTo(3);
    }
}