package com.example.tumbaburros.java8;

import java.util.*;
import java.util.stream.Collectors;

public class Streams2 {

    public static void main(String[] args) {

        String[] names = new String[]{"Fernando","Arellano","Saldana","Benita","Chachil", "Chisco"};

        List<String> list = Arrays.asList(names);
        list.stream().forEach(System.out::println);

        list = list.stream()
                .filter(s->s.startsWith("C"))
                .map(s->s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(list);

        List<Person> personas = new ArrayList<>();
        for(int i= 0; i<10; i++){
            Person person = new Person();
            Address address = new Address("Street"+i,"State"+1,11111+(i*10));
            person.setAddress(address);
            personas.add(person);
        }

        List<Address> addressList = personas.stream().map(p->p.getAddress()).collect(Collectors.toList());
        System.out.println(addressList);

        Optional<String> primero =list.stream().sorted(Comparator.reverseOrder()).findFirst();
        primero.ifPresent(System.out::println);


        List<Integer> numeros = Arrays.asList(new Integer[]{2,5,8,9,3,10});

        boolean allMatch = numeros.stream().allMatch(i->i%2==0);
        boolean anyMatch = numeros.stream().anyMatch(i->i%2==0);
        boolean noneMatch = numeros.stream().noneMatch(i->i%2==0);

        System.out.println(allMatch + ":" + anyMatch + ":" + noneMatch);


    }
}
