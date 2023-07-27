package com.example.tumbaburros.java8;

import java.util.Optional;


public class OptionalTest {

    public static void main(String[] args) {
        Person defaultPerson = new Person();
        defaultPerson.setAddress(new Address("Default Street", "Default State", 11111));

        Person p1 = new Person();
        Address address1 = new Address("Valle", "Jalisco", 45601);
        p1.setAddress(address1);

        Person p2 = null;

        Person p3 = new Person();

        System.out.println("Person With Address*******************");
        Optional<Person> optional = Optional.of(p1);
        optional.ifPresent( p-> System.out.println(p.getAddress()));

        System.out.println("null person************************");
        //handles optional with null posibility no null pointer
        optional = Optional.ofNullable(p2);
        System.out.println(optional.isPresent());
        optional.ifPresent( p-> System.out.println(p.getAddress()));

        //BUEN EJEMPLO
        Optional<Address> addresOptional = optional.map(Person::getAddress)
                .filter(address -> address!= null)
                .filter(address -> address.getState()!=null);
        addresOptional.ifPresent(a-> System.out.println(a));


        System.out.println("Reasigned************************");
        //reasigned default person
        optional = Optional.of(optional.orElseGet(()->defaultPerson));
        optional.ifPresent( p-> System.out.println(p.getAddress()));

        addresOptional = optional.map(Person::getAddress)
                .filter(address -> address!= null)
                .filter(address -> address.getState()!=null);

        addresOptional.ifPresent(a-> System.out.println(a));

        Optional<String> stateOptional = addresOptional.map(a-> a.getState());
        stateOptional.ifPresent(s-> System.out.println(s));

        System.out.println("Person Without Address*******************");
        optional = Optional.ofNullable(p3);
        System.out.println(optional.isPresent());

        optional.ifPresent( p-> {
            if(p.getAddress() != null){
                System.out.println(p.getAddress());
            } else {
                System.out.println("No Address");
            }
        });

        if(optional.isPresent()){
            System.out.println(optional.get());
        }


    }
}
