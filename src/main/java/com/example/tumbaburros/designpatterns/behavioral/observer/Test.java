package com.example.tumbaburros.designpatterns.behavioral.observer;

public class Test {

    public static void main(String[] args) {
        Thread thread = new Thread(new EventSource());
        thread.start();
    }
}
