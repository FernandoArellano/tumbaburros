package com.eazybytes.accounts;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterviewExercize {

    public static void main(String[] args) {

        String[] s = new InterviewExercize().words().split(" ");

        Map<String,Long> collect = Stream.of(s)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(collect);

        long r = collect.values().stream().sorted(Comparator.reverseOrder()).findFirst().orElse(-1l);
        long rr = collect.values().stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1l);

        for(Map.Entry<String, Long> c: collect.entrySet()){
            if(c.getValue() == r){
                System.out.println("Max:" + c.getKey());
            }

            if(c.getValue() == rr){
                System.out.println("2 Max:" + c.getKey());
            }
        }

    }

    public String words(){
        return "Hola Hola Hola Hola Hola Fer Fer Fer Fer";
    }
}
