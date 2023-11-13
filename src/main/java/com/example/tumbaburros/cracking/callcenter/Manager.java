package com.example.tumbaburros.cracking.callcenter;

public class Manager extends Employee{
    public Manager(CallHandler callHandler){
        super(callHandler);
        rank = Rank.Manager;
    }
}
