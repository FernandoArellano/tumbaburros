package com.example.tumbaburros.java8;

import java.util.function.BiFunction;

public class FunctionalBiFunction {

    public static void main(String[] args) {

        FunctionalBiFunction functionalBiFunction = new FunctionalBiFunction();

        BiFunction<Integer, Integer, Integer> bi = (a,b) -> a+b ;
        System.out.println(bi.apply(10,10));
    }
}
