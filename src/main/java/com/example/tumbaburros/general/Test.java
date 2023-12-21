package com.example.tumbaburros.general;

import com.example.tumbaburros.cracking.blackjack.Deck;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {

        //break label
       test:
        for(int i=0; i<10;i++){
            System.out.println(i);
            if(i==5){
                break test;
            }
        }
        System.out.println("Fin");

        System.out.println("-------------------------");

        //Synchronized Set with compact initialization
        Set<String> set = new HashSet<>(){{add("Fer"); add("Mama");}};

        Set<String> synchronizedSet = Collections.synchronizedSet(set);
        System.out.println(synchronizedSet);

        System.out.println("-------------------------");

        //Reflection
        Class<Deck> deck = (Class<Deck>) Class.forName("com.example.tumbaburros.cracking.blackjack.Deck");
        Method[] methods = deck.getMethods();
        for(Method m : methods){
            System.out.println(m.toString());
        }

        System.out.println("-------------------------");


        
    }





}
