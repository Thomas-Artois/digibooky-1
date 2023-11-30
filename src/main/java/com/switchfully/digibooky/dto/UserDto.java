package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.domain.Address;
import com.switchfully.digibooky.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class UserDto {
    private String id;
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Role role;

    public UserDto(String id, String socialSecurityNumber, String firstName, String lastName, String email, Address address, Role role) {
        this.id = id;
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public Address getAddress() {
        return address;
    }

    public UserDto setAddress(Address address) {
        this.address = address;

        return this;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
