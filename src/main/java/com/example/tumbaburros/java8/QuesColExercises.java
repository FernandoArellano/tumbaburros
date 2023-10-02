package com.example.tumbaburros.java8;

import java.util.*;
import java.util.stream.Stream;

public class QuesColExercises {

    public static void main(String[] args) {

        //Java 8 Program to add prefixe and suffix to the String?
        StringJoiner stringjoiner = new StringJoiner(",","$","#");
        String fer = "Fernando";
        String are = "Arellano";
        String sal = "Saldana";

        stringjoiner.add(fer);
        stringjoiner.add(are);
        stringjoiner.add(sal);

        System.out.println(stringjoiner);

        StringJoiner otherJoiner = new StringJoiner(",","$","#");
        otherJoiner.add("Jose");
        stringjoiner.merge(otherJoiner);

        System.out.println(stringjoiner);


        //Java 8 Program to Print ten random numbers using forEach?
        Random random = new Random();
        random.ints(0,3).limit(10).forEach(System.out::println);

        random.ints(0,100).limit(6).peek(System.out::println).max().ifPresent(System.out::println);


        //Java 8 program to find the Maximum number of a Stream?
        Integer[] array = new Integer[]{5,8,10,6,1,88};
        List<Integer> numeros = Arrays.asList(array);

        numeros.stream().min(Comparator.naturalOrder()).ifPresent(System.out::println);

        //Java 8 program to Count Strings whose length is greater than 3 in List?
        List<String> strings = new ArrayList<>(); strings.add("Fernando");strings.add("Chisco");strings.add("Are");strings.add("F");strings.add("X");
        System.out.println(strings.stream().filter(s->s.length()>3).count());

        //Java 8 program to multiply 3 to all elements in the list and print the list?
        numeros.stream().map(i->i*3).forEach(System.out::println);

        //Java 8 program to perform concatenation on two Streams?
        Integer[] array2 = new Integer[]{1,2,3};
        List<Integer> numeros2 = Arrays.asList(array2);

        Stream<Integer> mergedStream = Stream.concat(numeros.stream(),numeros2.stream());
        mergedStream.forEach(System.out::println);

        //Java 8 program to remove the duplicate elements from the list?
        Integer[] array3 = new Integer[]{1,2,4};
        List<Integer> numeros3 = Arrays.asList(array3);

        Stream<Integer> merged = Stream.concat(numeros2.stream(), numeros3.stream());

        merged.forEach(i-> System.out.print(i+":"));

        merged = Stream.concat(numeros2.stream(), numeros3.stream());

        System.out.println();

        merged.distinct().forEach(i-> System.out.print(i+":"));

        System.out.println();

        // Write a Java 8 program to get the sum of all numbers present in a list.
        System.out.println(numeros.stream().mapToInt(Integer::intValue).sum());

        //reduce
        int total = numeros.stream().reduce(0, (subtotal, next) -> subtotal + next, Integer::sum);
        System.out.println("suma:" + total);

        Employee e1 = new Employee("Fer",30,1000);
        Employee e2 = new Employee("Fer2",40,1000);
        Employee e3 = new Employee("Fer2",35,1000);

        List<Employee> employees = new ArrayList<>(); employees.add(e1);employees.add(e2);employees.add(e3);

        int promedio = employees.stream().reduce(0,(average,employee)-> average!=0 ?(average+employee.getAge())/2: employee.getAge(),Integer::sum);
        System.out.println(promedio);

    }
}
