package com.example.tumbaburros.exercizes.memoization;

/*
    Problem: Cache expensive computations safely.
    Explanation:

        computeIfAbsent is atomic and avoids redundant computations.

    execute result:
    received:10
    100
    100
 */

import java.util.HashMap;
import java.util.function.Function;

public class Memoizer<K,V> {

    HashMap<K,V> cache = new HashMap<>();
    Function<K,V> function;

    public Memoizer(Function<K,V> function){
        this.function= function;
    }

    public V compute(K key){
        return cache.computeIfAbsent(key, function);
    }

    public static void main(String[] args) {
        Memoizer<Integer, Integer> memoizer = new Memoizer<>(x-> {
            System.out.println("received:" +x);
            return x*x;
        });
        System.out.println(memoizer.compute(10));
        System.out.println(memoizer.compute(10));

    }
}
