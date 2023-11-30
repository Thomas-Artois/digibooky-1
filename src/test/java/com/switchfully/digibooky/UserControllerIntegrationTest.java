package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void givenCreateUserDto_whenCreateMemberUser_thenReturnMemberUserDto() {
        //GIVEN
        String socialSecurityNumber = "ABCDEFGH";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "mail@mail.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        String password = "password";
        CreateUserDto createUserDto = new CreateUserDto(socialSecurityNumber, firstName, lastName, email, address, password);

        //WHEN
        UserDto userDto =
                RestAssured
                        .given()
                        .body(createUserDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/users")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(UserDto.class);

        assertThat(userDto.getSocialSecurityNumber()).isNotBlank();
        assertThat(userDto.getSocialSecurityNumber()).isEqualTo(createUserDto.getSocialSecurityNumber());

        assertThat(userDto.getFirstName()).isNotBlank();
        assertThat(userDto.getFirstName()).isEqualTo(createUserDto.getFirstName());

        assertThat(userDto.getLastName()).isNotBlank();
        assertThat(userDto.getLastName()).isEqualTo(createUserDto.getLastName());

        assertThat(userDto.getEmail()).isEqualTo(createUserDto.getEmail());

        assertThat(userDto.getAddress()).isNotNull();
        assertThat(userDto.getAddress()).isEqualTo(createUserDto.getAddress());

        assertThat(userDto.getRole()).isEqualTo(Role.MEMBER);
    }

    @Test
    void givenCreateUserDto_whenCreateAdminUser_thenReturnAdminUserDto() {
        //GIVEN
        String socialSecurityNumber = "ABCDEFGH";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "mail@mail.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        String password = "password";
        CreateUserDto createUserDto = new CreateUserDto(socialSecurityNumber, firstName, lastName, email, address, password);

        //WHEN
        UserDto userDto =
                RestAssured
                        .given()
                        .body(createUserDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "admin@digibooky.com")
                        .header("password", "admin")
                        .when()
                        .port(port)
                        .post("/users/admin")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(UserDto.class);

        assertThat(userDto.getSocialSecurityNumber()).isNotBlank();
        assertThat(userDto.getSocialSecurityNumber()).isEqualTo(createUserDto.getSocialSecurityNumber());

        assertThat(userDto.getFirstName()).isNotBlank();
        assertThat(userDto.getFirstName()).isEqualTo(createUserDto.getFirstName());

        assertThat(userDto.getLastName()).isNotBlank();
        assertThat(userDto.getLastName()).isEqualTo(createUserDto.getLastName());

        assertThat(userDto.getEmail()).isEqualTo(createUserDto.getEmail());

        assertThat(userDto.getAddress()).isNotNull();
        assertThat(userDto.getAddress()).isEqualTo(createUserDto.getAddress());

        assertThat(userDto.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void givenCreateUserDto_whenCreateLibrarianUser_thenReturnLibrarianUserDto() {
        //GIVEN
        String socialSecurityNumber = "ABCDEFGH";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "mail@mail.com";
        Address address = new Address("streetName", "streetNumber", "postalCode", "city");
        String password = "password";
        CreateUserDto createUserDto = new CreateUserDto(socialSecurityNumber, firstName, lastName, email, address, password);

        //WHEN
        UserDto userDto =
                RestAssured
                        .given()
                        .body(createUserDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .header("email", "admin@digibooky.com")
                        .header("password", "admin")
                        .when()
                        .port(port)
                        .post("/users/librarian")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(UserDto.class);

        assertThat(userDto.getSocialSecurityNumber()).isNotBlank();
        assertThat(userDto.getSocialSecurityNumber()).isEqualTo(createUserDto.getSocialSecurityNumber());

        assertThat(userDto.getFirstName()).isNotBlank();
        assertThat(userDto.getFirstName()).isEqualTo(createUserDto.getFirstName());

        assertThat(userDto.getLastName()).isNotBlank();
        assertThat(userDto.getLastName()).isEqualTo(createUserDto.getLastName());

        assertThat(userDto.getEmail()).isEqualTo(createUserDto.getEmail());

        assertThat(userDto.getAddress()).isNotNull();
        assertThat(userDto.getAddress()).isEqualTo(createUserDto.getAddress());

        assertThat(userDto.getRole()).isEqualTo(Role.LIBRARIAN);
    }
}
