package com.example.tumbaburros.java8;
import java.util.function.BiPredicate;

public class FunctionalBiPredicate {

    public static void main(String[] args) {

        BiPredicate<Integer, Integer> biPredicate = (a,b) -> (a+b)%2 ==0;
        System.out.println(biPredicate.test(1,11));
        System.out.println(biPredicate.test(1,100));

    }
}
