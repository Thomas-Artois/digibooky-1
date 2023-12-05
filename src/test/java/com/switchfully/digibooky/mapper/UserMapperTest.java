package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.domain.User;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {
    private UserMapper userMapper = new UserMapper();

    @Test
    void givenUser_whenMapUserToUserDto_thenGetUserDto() {
        // GIVEN
        String socialSecurityNumber = "IIIIIIII";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "e@mail.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        Role role = Role.MEMBER;
        String password = "password";

        User user = new User(socialSecurityNumber, firstName, lastName, email, address, role, password);

        // WHEN
        UserDto actual = userMapper.mapUserToUserDto(user);

        // THEN
        assertThat(actual).isInstanceOf(UserDto.class);
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getEmail()).isEqualTo(email);
        assertThat(actual.getAddress()).isEqualTo(address);
        assertThat(actual.getRole()).isEqualTo(role);
    }

    @Test
    void givenCreateUserDto_whenMapCreateUserDtoToUser_thenGetUser() {
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
        User actual = userMapper.mapCreateUserDtoToUser(createUserDto, role);

        // THEN
        assertThat(actual).isInstanceOf(User.class);
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getEmail()).isEqualTo(email);
        assertThat(actual.getAddress()).isEqualTo(address);
        assertThat(actual.getRole()).isEqualTo(role);
    }
}