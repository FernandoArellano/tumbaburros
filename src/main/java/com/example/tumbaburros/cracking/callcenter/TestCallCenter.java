package com.example.tumbaburros.cracking.callcenter;

public class TestCallCenter {

    public static void main(String[] args) throws InterruptedException {
        CallHandler callHandler = new CallHandler();
        callHandler.fillAllEmployees();
        callHandler.dispatchCall(new Caller("Fer"));
    }
}
