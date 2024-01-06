package com.example.tumbaburros.digitalocean;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void line(){
        System.out.println("---------------------------");
    }
    public static void reverseString1(){
        String fer= "Fernando";
        char [] array = fer.toCharArray();
        for(int i=0; i<array.length/2; i++){
            char temp = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = temp;
        }
        System.out.println(new String(array));
    }

    public static void swapNumbers(){
        int a=10;
        int b=20;
        b= b+a;
        a= b-a;
        b= b-a;

    }

    public static void hasVowels(){
        String a= "bbbnnnmmmm";
        boolean result = a.toLowerCase().matches(".*[aeiou].*");
        System.out.println(result);
    }

    public static void hasVowels2(){
        String a= "bbbnnAnmmmm";
        String vowels = "aeiou";

        Set<String> set = Stream.of(vowels.split("")).collect(Collectors.toSet());
        System.out.println(set);

        boolean result = Stream.of(a.split("")).map(String::toLowerCase).anyMatch(s->set.contains(s));
        System.out.println(result);
    }

    public static boolean isPrime(int n){
        if(n == 0|| n == 1)
            return false;
        if(n==2)
            return true;

        for(int i=2; i<= n/2; i++){
            if(n%i==0)
                return false;
        }
        return true;
    }

    public static boolean isPrime2(int n){
        return IntStream.rangeClosed(2,n/2).noneMatch(i->n%i==0);
    }

    public static int fibonacci(int n){
        if(n<=1){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n -2);
    }

    public static void oddNumbersOnly(){
        List<Integer> list = List.of(1,3,5,7,9);
        boolean result = list.stream().noneMatch(i->i%2==0);
        System.out.println(result);
    }

    public static void palindrome(){
        String word = "aabbbaa";
        System.out.println(word.equals(new StringBuilder(word).reverse().toString()));
    }

    public static boolean palindrome2(){
        String word = "aabbbaa";

        for(int i=0; i<word.length()/2; i++){
            if(word.charAt(i) != word.charAt(word.length()-1-i)){
                return false;
            }
        }
        return true;
    }

    public static void removeSpaces(){
        String word = "Jose   Fernand   o ArellanoSaldaña";
        System.out.println(word.replace(" ", ""));
    }

    public static void removeNumbers(){
        String word = "Jos3 F3rn14nd0 4r3ll4no";
        char [] array = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<array.length;i++){
            if(Character.isLetter(array[i])){
                sb.append(array[i]);
            }
        }
        System.out.println(sb.toString());
    }

    public static void keepOnlyNumbers(){
        String word = "Jos3 F3rn14nd0 4r3ll4no";
        word.chars().mapToObj(i-> (char)i).filter(c->Character.isDigit(c)).forEach(System.out::print);
    }

    public static void sortArray1(){
        int [] array = new int[]{1,-2,4,-5,10,5};
        System.out.println("index 0: " + array[0]);
        Arrays.sort(array);
        System.out.println("index 0: " + array[0]);
    }

    public static void sortArray2(){
        Integer [] array = new Integer[]{1,-2,4,-5,10,5};
        System.out.println("index 0: " + array[0]);
        List.of(array).stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
    }

    public static int factorial(int n){
        if(n==1)
            return 1;
        return n * factorial(n-1);
    }

    public static void reverseLinkList(){
        LinkedList<Integer> list = new LinkedList<>(){{
            add(5);add(10);add(1);
        }};

        LinkedList<Integer> orderedList = new LinkedList<>();
        list.descendingIterator().forEachRemaining(i->orderedList.add(i));

        System.out.println(orderedList);
    }

    public static void duplicateElementsInArray(){
        Integer[] a = new Integer[]{1,2,3,4,5};
        Integer[] b = new Integer[]{3,5};

        List<Integer> listA = List.of(a);
        List<Integer> listB = List.of(b);

        List<Integer> finalList = listA.stream().filter(i->listB.contains(i)).collect(Collectors.toList());
        System.out.println(finalList);
    }

    public static void sumAllElements(){
        List<Integer> list = List.of(1,2,3,4,5);
        int x = list.stream().reduce(0,(i1,i2)->i1+i2).intValue();
        System.out.println(x);
    }

    public static void secondLargest(){
        List<Integer> list = List.of(1,20,30,4,5);
        list.stream().sorted(Comparator.reverseOrder()).skip(1).limit(1).forEach(System.out::println);
    }

    public static void shuffleArray(){
        Integer[] array = new Integer[]{1,2,3,4,5};
        List<Integer> list = new ArrayList<>(List.of(array));
        Collections.shuffle(list);
        array = list.stream().toArray(Integer[]::new);
        System.out.println(array[0]);
    }

    public static void mergeList1(){
        List<Integer> list = List.of(1,3,5,7);
        List<Integer> list2 = new ArrayList<>(List.of(2,4,6,8));
        list2.addAll(list);
        System.out.println(list2);
    }

    public static void mergeList2(){
        List<Integer> list = new ArrayList<>(List.of(1,3,5));
        List<Integer> list2 = new ArrayList<>(List.of(2,4,6,8));

        List<Integer> collect = Stream.concat(Stream.of(list), Stream.of(list2)).flatMap(List::stream).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void distinctLetters(){
        String s = "abcdABCDabcd";
        s.chars().mapToObj(i->(char)i).distinct().forEach(System.out::print);
    }

    public static void distinctLetters2(){
        String s = "abcdABCDabcd";
        char[] array = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<array.length;i++){
            char actual = array[i];
            if(map.containsKey(actual)){
                map.put(actual, (map.get(actual))+1);
            } else{
                map.put(actual, 1);
            }
        }
        System.out.println(map);
    }

    public static void distinctLetters3(){
        String s = "abcdABCDabcd";
        Map<Character, Long> map = s.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }



    public static void main(String[] args) {
        reverseString1();line();
        hasVowels();line();
        hasVowels2();line();
        System.out.println(isPrime(554));line();
        System.out.println(isPrime(17));line();
        System.out.println(isPrime2(554));line();
        System.out.println(isPrime2(17));line();

        for(int i=0; i<10; i++){
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        line();

        oddNumbersOnly();line();
        palindrome();line();
        System.out.println(palindrome2());line();
        removeSpaces();line();
        removeNumbers();line();
        keepOnlyNumbers();System.out.println();line();
        sortArray1();line();
        sortArray2();line();
        System.out.println(factorial(5));line();
        reverseLinkList();line();
        duplicateElementsInArray();line();
        sumAllElements();line();
        secondLargest();line();
        shuffleArray();line();
        mergeList1();line();
        mergeList2();line();
        distinctLetters();System.out.println();line();
        distinctLetters2();line();
        distinctLetters3();line();
    }
}
