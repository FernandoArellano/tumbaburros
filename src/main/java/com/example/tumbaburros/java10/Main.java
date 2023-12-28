package com.example.tumbaburros.java10;

import java.util.List;

public class Main {

    public static void varTest() {
        var bandera = true;
        var nombre = "Fernando Arellano";
        var list = List.of("A","B","C");
        if(bandera){
            System.out.println(nombre);
            list.stream().forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
       varTest();
    }
}
