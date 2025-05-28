package com.example.tumbaburros.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //flat map

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        System.out.println(listOfLists);

        List<Integer> flattenedList = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flattenedList);


        List<String> sentences = Arrays.asList("hello world", "java is awesome");

        System.out.println(sentences);

        List<String> words = sentences.stream()
                .flatMap(sentence -> Stream.of(sentence.split(" ")))
                .collect(Collectors.toList());

        System.out.println("Words: " + words);


        //generate
        List<Integer> integerList = Stream.generate(new Random()::nextInt)
                .limit(10).map(integer -> integer-10 ).collect(Collectors.toList());
        System.out.println(integerList);

        //iterate
        Stream<Integer> iterate = Stream.iterate(2, num -> num<50, num -> num*=num);
        System.out.println(iterate.collect(Collectors.toList()));

        //reduce
    }
}
