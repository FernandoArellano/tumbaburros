package com.example.tumbaburros.exercizes.comparator;

/*
    Problem: Sort strings by length, then case-insensitive lexicographic, nulls last.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RobustComparator {

    public static void main(String[] args) {
        List<String> items = new ArrayList<>(Arrays.asList("Banana", "apple", null, "pear", "Peach"));

        List<String> sorted = items.stream()
                .sorted(Comparator.nullsLast(
                        Comparator.comparing(String::length)
                                .thenComparing(String::compareToIgnoreCase)
                                .thenComparing(Comparator.naturalOrder())
                )).collect(Collectors.toList());
        System.out.println(sorted);
    }
}
