package com.example.tumbaburros.cracking.callcenter;

public class Respondant extends Employee{

    public Respondant(CallHandler callHandler){
        super(callHandler);
        rank = Rank.Responder;
    }
}
