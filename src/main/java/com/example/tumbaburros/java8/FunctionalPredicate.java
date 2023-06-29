package com.example.tumbaburros.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class FunctionalPredicate {

    //perform validation condition and return boolean
    //1 argument of T value
    // boolean test(T t);

    public boolean isGreaterThan10(int value){
        Predicate<Integer> p1 = val -> val>10;
        return p1.test(value);
    }

    public boolean isStringLengthGreaterThan5(String val){
        Predicate<String> p = (s)-> s.length()>5;
        return p.test(val);
    }

    public boolean isCollectionEmpty(Collection collection){
        Predicate<Collection> p = c->c.isEmpty();
        return p.test(collection);
    }

    public boolean isGreterThan10AndEven(int num){
        Predicate<Integer> p1 = (i) -> i>10;
        Predicate<Integer> p2 = (i) -> i%2==0;

        return p1.and(p2).test(num);
    }

    public boolean isGreterThan10OREven(int num){
        Predicate<Integer> p1 = (i) -> i>10;
        Predicate<Integer> p2 = (i) -> i%2==0;

        return p1.or(p2).test(num);
    }

    public boolean isGreterThan10AndEvenNegate(int num){
        Predicate<Integer> p1 = (i) -> i>10;
        Predicate<Integer> p2 = (i) -> i%2==0;

        return p1.negate().and(p2).test(num);
    }

    public boolean startsWithF(String s){
        Predicate<String> p = (ss)->ss.startsWith("F");
        return p.test(s);
    }

    public boolean hasNullOrEmpty(String s){
        Predicate<String> p = (ss)-> ss!= null && !ss.isEmpty();
        return p.negate().test(s);
    }

    public boolean authenticate(User user){
        Predicate<User> p = (u)-> u.getName().equals("durga") && u.getPass().equals("java");
        return p.test(user);
    }

    public static void main(String[] args) {
        FunctionalPredicate fp = new FunctionalPredicate();
        Predicate<Integer> p2 = (val)->val>10;

        System.out.println(fp.isGreaterThan10(100));
        System.out.println(fp.isGreaterThan10(1));
        System.out.println(p2.test(20));

        System.out.println(fp.isStringLengthGreaterThan5("Fer"));
        System.out.println(fp.isStringLengthGreaterThan5("Fernando"));

        Collection<String> collection = new ArrayList<>();
        collection.add("Fer");
        System.out.println("Is Collection Empty:" + fp.isCollectionEmpty(collection));
        collection.clear();
        System.out.println("Is Collection Empty:" + fp.isCollectionEmpty(collection));

        System.out.println("Union Predicate 34:" + fp.isGreterThan10AndEven(34));
        System.out.println("Union Predicate 33:" + fp.isGreterThan10AndEven(33));
        System.out.println("Union Predicate 4:" + fp.isGreterThan10AndEven(4));

        System.out.println("Union Negate Predicate 34:" + fp.isGreterThan10AndEvenNegate(34));
        System.out.println("Union Negate Predicate 33:" + fp.isGreterThan10AndEvenNegate(33));
        System.out.println("Union Negate Predicate 4:" + fp.isGreterThan10AndEvenNegate(4));

        System.out.println("Union OR Predicate 34:" + fp.isGreterThan10OREven(34));
        System.out.println("Union OR Predicate 33:" + fp.isGreterThan10OREven(33));
        System.out.println("Union OR Predicate 4:" + fp.isGreterThan10OREven(4));

        System.out.println("Starts With F");
        String[] array = new String[]{"Ana","Sofia","Fernando", "Francisco", "Sandra"};
        for(String s: array){
            System.out.println(fp.startsWithF(s));
        }

        String[] array2 = new String[]{"Ana",null,"Fernando", "", "Sandra"};
        List<String> list = new ArrayList<>();
        for(String s: array2){
            if(!fp.hasNullOrEmpty(s)){
                list.add(s);
            }
        }
        System.out.println("List without empty or null");
        System.out.println(list);

        User user = new User();
        System.out.println("User Authentication");
        System.out.println("Enter Username");
        //Scanner scanner = new Scanner(System.in);
        user.setName("durga");
        System.out.println("Enter pass");
        user.setPass("java");

        if(fp.authenticate(user)){
            System.out.println("Authenticated Correctly");
        } else{
            System.out.println("Error Authenticating");
        }
    }

}
