package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.domain.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UpdateUserDto {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @Valid
    private Address address;
    @NotEmpty
    private String password;

    public UpdateUserDto(String firstName, String lastName, String email, Address address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public UpdateUserDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UpdateUserDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public UpdateUserDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public Address getAddress() {
        return address;
    }

    public UpdateUserDto setAddress(Address address) {
        this.address = address;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
