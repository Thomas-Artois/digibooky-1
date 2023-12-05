package com.switchfully.digibooky;

import com.switchfully.digibooky.controller.MemberController;
import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberControllerTest {
    @Autowired
    private MemberController memberController;

    @Test
    void givenCreateUserDto_whenCreateMember_thenGetMember() {
        // GIVEN
        String socialSecurityNumber = "IIIIIIII";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "e@mail.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        Role role = Role.MEMBER;
        String password = "password";

        CreateUserDto createUserDto = new CreateUserDto(socialSecurityNumber, firstName, lastName, email, address, password);

        // WHEN
        UserDto actual = memberController.createMember(createUserDto);

        // THEN
        assertThat(actual).isInstanceOf(UserDto.class);
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getEmail()).isEqualTo(email);
        assertThat(actual.getAddress()).isEqualTo(address);
        assertThat(actual.getRole()).isEqualTo(role);
    }

    @Test
    void givenAdminCredentials_whenGetAllMembers_thenGetAllMembers() {
        // GIVEN
        String email = "admin@digibooky.com";
        String password = "admin";

        // WHEN
        List<UserDto> actual = memberController.viewAllMembers(email, password);

        // THEN
        assertThat(actual).extracting("role").containsOnly(Role.MEMBER);
    }
}