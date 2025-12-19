package com.example.tumbaburros.exercizes.customcollector;

/*
    Problem: Build a custom Collector that joins strings with a delimiter, prefix, and suffix.
    [alpha, beta, gamma]
 */

import java.util.List;
import java.util.stream.Collectors;

public class CustomJoiner {

    public static void main(String[] args) {

        String result = List.of("alpha", "beta", "gamma")
                .stream().collect(Collectors.joining(", ", "[","]"));

        System.out.println(result);

    }
}
