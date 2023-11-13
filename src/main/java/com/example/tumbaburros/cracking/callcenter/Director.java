package com.example.tumbaburros.cracking.callcenter;

public class Director extends Employee {

    public Director(CallHandler callHandler){
        super(callHandler);
        rank = Rank.Director;
    }
}
