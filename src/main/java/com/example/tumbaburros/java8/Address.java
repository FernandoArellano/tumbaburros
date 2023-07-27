package com.example.tumbaburros.java8;

public class Address {
    private String street;
    private String state;
    private int cp;



    public Address(String street, String state, int cp) {
        this.street = street;
        this.state = state;
        this.cp = cp;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", cp=" + cp +
                '}';
    }
}
