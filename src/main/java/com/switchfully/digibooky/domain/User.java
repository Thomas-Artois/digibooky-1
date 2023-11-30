package com.switchfully.digibooky.domain;

import jakarta.validation.constraints.Email;

import java.util.UUID;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Role role;
    private String password;

    public User(String firstName, String lastName, String email, Address address, Role role, String password) {
        this(UUID.randomUUID().toString(), firstName, lastName, email, address, role, password);
    }

    private User(String id, String firstName, String lastName, String email, Address address, Role role, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.role = role;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
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
