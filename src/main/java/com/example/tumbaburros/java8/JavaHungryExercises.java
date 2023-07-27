package com.example.tumbaburros.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Function;
public class JavaHungryExercises {

    public static void main(String[] args) {

        //Given a list of integers, find out all the even numbers exist in the list using Stream functions
        //10,15,8,49,25,98,32)

        Integer [] numeros = new Integer[]{10,15,8,49,25,98,32};
        List<Integer> list = Arrays.asList(numeros);

        list = list.stream().filter(i->i%2==0).collect(Collectors.toList());
        System.out.println(list);

        //Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        //10,15,8,49,25,98,32

        numeros = new Integer[]{10,15,8,49,25,98,32};
        list = Arrays.asList(numeros);

        list.stream().map(i->String.valueOf(i)).filter(s->s.startsWith("1")).forEach(System.out::println);

        //How to find duplicate elements in a given integers list in java using Stream functions?
        //10,15,8,49,25,98,98,32,15

        numeros = new Integer[]{10,15,8,49,25,98,98,32,15};
        list = Arrays.asList(numeros);

        Set<Integer> set = new HashSet<>();
        list.stream().filter(i->!set.add(i)).forEach(i-> System.out.print(i+":"));


        //Given the list of integers, find the first element of the list using Stream functions?
        //10,15,8,49,25,98,98,32,15

        System.out.println();
        numeros = new Integer[]{10,15,8,49,25,98,98,32,15};
        list = Arrays.asList(numeros);

        Optional<Integer> optional = list.stream().findFirst();
        optional.ifPresent(System.out::println);

        //Q5 Given a list of integers, find the total number of elements present in the list using Stream functions?
        //10,15,8,49,25,98,98,32,15

        numeros = new Integer[]{10,15,8,49,25,98,98,32,15};
        list = Arrays.asList(numeros);

        System.out.println(list.stream().count());


        //Given a list of integers, find the maximum value element present in it using Stream functions?
        //10,15,8,49,25,98,98,32,15

        numeros = new Integer[]{10,15,8,49,25,98,98,32,15};
        list = Arrays.asList(numeros);

        System.out.println(list.stream().max(Comparator.naturalOrder()));

        //Given a String, find the first non-repeated character in it using Stream functions?

        String input = "Java Hungry Blog Alive is Awesome";
        Map<Character, Integer> map = new LinkedHashMap<>();

//        Character result = input.chars() // Stream of String
//                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue() == 1L)
//                .map(entry -> entry.getKey())
//                .findFirst()
//                .get();
//        System.out.println(result);

        //solution considering A and a different characters
        input.chars()
                .forEach(i->{
                    if(map.containsKey((char)i)){
                        map.put((char)i,map.get((char)i)+1);
                    } else {
                        map.put((char)i,1);
                    }
                });

        map.entrySet()
                .stream()
                .filter(entry-> entry.getValue()==1)
                .findFirst()
                .ifPresent(entry-> System.out.println(entry.getKey()));


        map.clear();

        //solution considering A and a same
        input.chars()
                .forEach(i->{
                    char c = Character.toLowerCase((char)i);
                    if(map.containsKey(c)){
                        map.put(c,map.get(c)+1);
                    } else {
                        map.put(c,1);
                    }
                });

        map.entrySet()
                .stream()
                .filter(entry-> entry.getValue()==1)
                .findFirst()
                .ifPresent(entry-> System.out.println(entry.getKey()));


        map.clear();

        //Otra manera

        input.chars()
                .mapToObj(c->Character.toLowerCase((char)c))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .forEach(System.out::println);


        input.chars()
                .mapToObj(c->Character.toLowerCase((char)c))
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new,Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry-> entry.getValue()==1)
                .findFirst()
                .ifPresent(entry-> System.out.println(entry.getKey()));

        input.chars()
                .mapToObj(c->String.valueOf((char)c))
                .peek(c->c.toUpperCase())
                .forEach(System.out::print);

        System.out.println();

        //Given a String, find the first repeated character in it using Stream functions?
        Set<Character> stringSet = new HashSet<>();

        input.chars()
                .mapToObj(c-> Character.toLowerCase((char)c))
                .filter(c->!stringSet.add(c))
                .findFirst()
                .ifPresent(System.out::println);

        stringSet.clear();


        //Given a list of integers, sort all the values present in it using Stream functions?
        //(10,15,8,49,25,98,98,32,15

        numeros = new Integer[]{10,15,8,49,25,98,98,32,15};
        list = Arrays.asList(numeros);

        list.stream().sorted(Comparator.naturalOrder()).forEach(i-> System.out.print(i+":"));

        System.out.println();
        //Given a list of integers, sort all the values present in it in descending order using Stream functions?
        numeros = new Integer[]{10,15,8,49,25,98,98,32,15};
        list = Arrays.asList(numeros);

        list.stream().sorted(Comparator.reverseOrder()).forEach(i-> System.out.print(i+":"));
    }
}
