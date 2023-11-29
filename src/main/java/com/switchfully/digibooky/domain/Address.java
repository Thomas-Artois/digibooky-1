package com.switchfully.digibooky.domain;

public class Address {
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;

    public Address(String streetName, String streetNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }
}
