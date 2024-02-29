package com.example.tumbaburros.java9;

import javax.imageio.IIOException;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void line(){
        System.out.println("----------------------------------------");
    }

    public static void java7tryWithResources(){
        //java 7
        try( FileReader fr = new FileReader("C:\\Users\\FArellano\\Desktop\\fer.txt");
             BufferedReader br = new BufferedReader(fr)){
            System.out.println(br.readLine());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void java9TryWithResources() throws FileNotFoundException {
        //java9
        FileReader fr = new FileReader("C:\\Users\\FArellano\\Desktop\\fer.txt");
        BufferedReader br = new BufferedReader(fr);
        try(fr; br){
            System.out.println(br.readLine());
        } catch(IOException e){
            System.out.println(e);
        }
    }

    public static void java9ImmutableList(){
        List<Integer> emptyList = List.of();
        List<String> someStrings = List.of("Fer", "Juan");
      //  emptyList.add(5); //exception immutable list

        Set<Integer> emptySet = Set.of();
        Set<String> someSet = Set.of("SS");
      //  emptySet.add(6); //exception immutable set

        Map<Integer, String> emptyMap = Map.of();
        Map<Integer, String> someMap = Map.of(1, "one", 2, "two");
      //  someMap.put(3,"Three"); // exception immutable map

        System.out.println(emptyList);
        System.out.println(someStrings);

        System.out.println(emptySet);
        System.out.println(someSet);

        System.out.println(emptyMap);
        System.out.println(someMap);
    }

    //iterate n times
    //start, until when predicate, what to execute function
    public static void iterate(){
        Stream.iterate(1, n->n<20, n -> n*2).forEach(System.out::println);
    }

    public static void dropWhile(){
        List<Integer> result = List.of(1,5,7,9,20,30,50).stream().dropWhile(i->i<10).collect(Collectors.toList());
        System.out.println(result);
    }

    public static void takeWhile(){
        List<Integer> result = List.of(1,5,7,9,20,30,50).stream().takeWhile(i-> i<10).collect(Collectors.toList());
        System.out.println(result);
    }

    public static void ofNullable(){
        List<Integer> nullList = null;
        Stream.ofNullable(nullList).forEach(System.out::println);
        System.out.println("Didnt explode");

    }

    public static void main(String[] args) throws FileNotFoundException {
        java7tryWithResources();line();
        java9TryWithResources();line();
        iterate();line();
        java9ImmutableList();line();
        dropWhile();line();
        takeWhile();line();
        ofNullable();line();
    }
}
