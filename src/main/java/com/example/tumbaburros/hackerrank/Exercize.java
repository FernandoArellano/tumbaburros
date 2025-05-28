package com.example.tumbaburros.hackerrank;

import java.util.HashSet;
import java.util.Set;

public class Exercize {
    public static void main(String[] args) {
        int[] array = new int[] {1,5,67,7,3,5,6,8,4,4,67,78,2,6,8,9};

        Set<Integer> set = new HashSet<>();
        for(int i: array){
            if(!set.add(i)){
                System.out.println("duplicate number: " +i);
            }
        }
    }
}
