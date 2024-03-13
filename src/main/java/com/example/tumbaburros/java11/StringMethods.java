package com.example.tumbaburros.java11;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StringMethods {

    public static void main(String[] args) {

        //String repeat
        //repear string n number of times

        String wish ="America Campeon!\n";
        System.out.println(wish.repeat(10));

        //IsBlank
        //check if is empty but considering white spaces as empty as well, isEmpty would consider white spaces as the string is not empty
        String isBlank = "    ";
        System.out.println("Is empty:" + isBlank.isEmpty());
        System.out.println("Is Blank:" + isBlank.isBlank());

        System.out.println("----------------------");

        //lines  divides each line in a string and returns a Stream of it
        String str = "Java11\nJava10\nJava9\nJava8\n";
        str.lines().forEach(s-> System.out.println(s));

        System.out.println("----------------------");

        //strip remove spaces from left and right
        //stripTrailing remove spaces from right
        //stripleading removes spaces from left

        //Arrays.asList
        List<String> list = Arrays.asList("Fer","Ana","Abc");
        list.stream().forEach(System.out::println);

        System.out.println("----------------------");
        //Predicate.not
        list.stream().filter(Predicate.not(s-> s.startsWith("A"))).forEach(System.out::println);


        System.out.println("----------------------");

        //isBlank
        String s1 = "         ";
        String s2 = "        d     ";
        System.out.println(s1.isBlank());
        System.out.println(s2.isBlank());

        //Lines
        String s3 = "ffff\n" +
                "fff\n" +
                "fff";
        Stream<String> stream= s3.lines();
        stream.forEach(System.out::println);

        //strip
        System.out.println("No strip:" + s2 +".");
        System.out.println("Strip:" + s2.strip()+".");

        //Repeat
        System.out.println("Fer".repeat(3));

        //Collection.toArray(IntFunction)
        List<Integer> integers = List.of(1,2,3,4,5);
        Integer[] integers1 = integers.toArray(Integer[]::new);
        for(Integer i: integers1){
            System.out.println(i);
        }

        Boolean[] booleans = new Boolean[]{true,false};
    }
}
