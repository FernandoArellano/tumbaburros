package com.example.tumbaburros.java8;

import java.util.*;

public class LambdaCollectionExample {

    public static void main(String[] args) {

        //comparator example
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(9);
        System.out.println(list);
        Collections.sort(list,(a,b) -> b - a);
        System.out.println(list);

        LambdaCollectionExample lambdaCollectionExample = new LambdaCollectionExample();
        lambdaCollectionExample.getDescencentPerson();

    }

    public void getDescencentPerson(){
        Person p1 = new Person("Fer",37);
        Person p2 = new Person("Fer2",17);
        Person p3 = new Person("Fer3",57);

        List<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        System.out.println(people);
        Collections.sort(people, (pe1, pe2) -> pe1.age - pe2.age);

        System.out.println(people);
    }

    class Person{

        String name;
        int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "[" + age + "]";
        }
    }
}
