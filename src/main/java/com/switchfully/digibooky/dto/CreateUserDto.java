package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.domain.Address;

import java.util.UUID;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    public CreateUserDto(String firstName, String lastName, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateUserDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateUserDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public Address getAddress() {
        return address;
    }

    public CreateUserDto setAddress(Address address) {
        this.address = address;

        return this;
    }
}
