package com.example.tumbaburros.java8;

import java.util.function.*;

public class LambdaBasicExamples {

    int x = 10;

    public static void main(String[] args) {

        // no args no result
        Runnable runnable = () -> System.out.println("Hello");
        runnable.run();

        //custom functional interface with default and static methods
        MyFunctionalInterface functionalInterface = () -> System.out.println("Another Hello");
        functionalInterface.test();
        functionalInterface.test2();
        MyFunctionalInterface.test3();

        //2 args and result
        BiFunction<Integer,Integer,Integer> biFunction = (a, b) -> a+b;
        System.out.println(biFunction.apply(4,6));

        //1 arg 1 result
        Function<String, Integer> function = (s) -> s.length();
        System.out.println(function.apply("Fernando"));

        new LambdaBasicExamples().threadExample();

        new LambdaBasicExamples().varsExample();

    }

    public void threadExample(){

        Runnable runnable = () -> {
            for(int i =0; i<10; i++){
                System.out.println("Child Thread");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        for(int i= 0; i<10; i++){
            System.out.println("Main thread");
        }
    }

    public void varsExample(){

        int y = 20;

        MyFunctionalInterface i = () -> {
            //can access instance variables
            System.out.println(++x);
            //can access outer variables
            System.out.println(y);
        };

        i.test();

    }
}
