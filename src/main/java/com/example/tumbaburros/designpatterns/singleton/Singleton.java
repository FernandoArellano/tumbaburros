package com.example.tumbaburros.designpatterns.singleton;

// Problem: Implement a lazy-loaded singleton safely.

// Explanation:

//volatile prevents instruction reordering and ensures visibility.
//Double-checked locking minimizes synchronization overhead after initialization.
//Avoid reflection-based construction or guard against it if necessary.

public class Singleton {

    private static volatile Singleton singleton = null;

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }

}
