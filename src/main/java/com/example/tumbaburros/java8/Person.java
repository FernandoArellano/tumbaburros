package com.example.tumbaburros.java8;

public class Person {

    Address address;

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "address=" + address +
                '}';
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
