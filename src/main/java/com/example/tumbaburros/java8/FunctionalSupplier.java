package com.example.tumbaburros.java8;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

public class FunctionalSupplier {

    public Date fecha(){
        Supplier<Date> s = () ->  new Date();
        return s.get();
    }

    public Integer getRandomNumber(){
        Supplier<Integer> supplier = () -> (int)(Math.random()*4);
        return supplier.get();
    }

    public static void main(String[] args) {
        Supplier<Date> s = () ->  new Date();
        System.out.println(s.get());

        FunctionalSupplier supplier = new FunctionalSupplier();
        System.out.println(supplier.fecha());

        System.out.println(supplier.getRandomNumber());
        System.out.println(supplier.getRandomNumber());
        System.out.println(supplier.getRandomNumber());
    }
}
