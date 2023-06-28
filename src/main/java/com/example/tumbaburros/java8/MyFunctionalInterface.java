package com.example.tumbaburros.java8;

@FunctionalInterface
public interface MyFunctionalInterface {

    public void test();

    default void test2(){
        System.out.println("Default method must be implemented");
    }

    static void test3(){
        System.out.println("Static method must be implemented as well");
    }
}
