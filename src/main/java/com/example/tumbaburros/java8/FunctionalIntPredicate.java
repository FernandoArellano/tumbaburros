package com.example.tumbaburros.java8;

import java.util.function.IntPredicate;

public class FunctionalIntPredicate {

    public static void main(String[] args) {
        //no need to type and no need of autoboxing and autounboxing
        IntPredicate p = (i) -> i%2==0;

        for(int i=0; i<10; i++){
            System.out.println(p.test(i));
        }
    }
}
