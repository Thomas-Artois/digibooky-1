package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.domain.Address;

public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    public UpdateUserDto(String firstName, String lastName, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
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
}
