package com.example.tumbaburros.java14;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Animal cat = new Cat("Don");

        Animal dog = new Dog("Gordita");

        List<Animal> animals = new ArrayList<>(){{
            add(cat); add(dog); add(null);
        }};

        for(Animal animal: animals){
            if(animal instanceof Cat cat1){
                cat1.meowt();
            }

            if(animal instanceof Dog dog1){
                dog1.bark();
            }
        }
    }
}
