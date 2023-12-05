package com.switchfully.digibooky.domain;

import jakarta.validation.constraints.Email;

import java.util.Objects;
import java.util.UUID;

public class User {
    private String id;
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Role role;
    private String password;

    public User(String socialSecurityNumber, String firstName, String lastName, String email, Address address, Role role, String password) {
        this(UUID.randomUUID().toString(), socialSecurityNumber, firstName, lastName, email, address, role, password);
    }


    public User(String id, String socialSecurityNumber, String firstName, String lastName, String email, Address address, Role role, String password) {
        this.id = id;
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.role = role;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(socialSecurityNumber, user.socialSecurityNumber) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && role == user.role && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, socialSecurityNumber, firstName, lastName, email, address, role, password);
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;

        return this;
    }

    public Address getAddress() {
        return address;
    }

    public User setAddress(Address address) {
        this.address = address;

        return this;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
