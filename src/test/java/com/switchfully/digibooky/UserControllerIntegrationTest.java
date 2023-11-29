package com.switchfully.digibooky;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void givenCreateUserDto_whenCreateUser_thenReturnUserDto() {
        //GIVEN
        String firstName = "Mark";
        String lastName = "Zuckerberg";
        String email = "mark@zuckerberg.com";
        Address address = new Address("streetname", "streetnumber", "postalcode", "city");
        CreateUserDto createUserDto = new CreateUserDto(firstName, lastName, email, address);

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

        assertThat(userDto.getFirstName()).isNotBlank();
        assertThat(userDto.getFirstName()).isEqualTo(createUserDto.getFirstName());

        assertThat(userDto.getLastName()).isNotBlank();
        assertThat(userDto.getLastName()).isEqualTo(createUserDto.getLastName());

        assertThat(userDto.getEmail()).isEqualTo(createUserDto.getEmail());

        assertThat(userDto.getAddress()).isNotNull();
        assertThat(userDto.getAddress()).isEqualTo(createUserDto.getAddress());
    }



}
