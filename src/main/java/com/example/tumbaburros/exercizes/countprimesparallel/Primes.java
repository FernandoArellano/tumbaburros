package com.example.tumbaburros.exercizes.countprimesparallel;

import java.util.stream.Stream;

/*
    Problem: Count primes up to N using parallel streams.
 */

public class Primes {

    static boolean isPrime(int n){

        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        int max = (int) Math.sqrt(n);
        for (int i = 3; i <= max; i += 2) if (n % i == 0) return false;
        return true;

    }

    public static void main(String[] args) {
        int number = 100;
        long count = Stream.iterate(0, i-> i<=number ,i->i+1).parallel().filter(Primes::isPrime).count();
        System.out.println(count);
    }
}
