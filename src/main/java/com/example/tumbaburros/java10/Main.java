package com.example.tumbaburros.java10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void varTest() {
        var bandera = true;
        var nombre = "Fernando Arellano";
        var list = List.of("A","B","C");
        if(bandera){
            System.out.println(nombre);
            list.stream().forEach(System.out::println);
        }

        List<String> lista = new ArrayList<>();
        lista.add("fer");

    }

    public static void main(String[] args) {
       varTest();
    }
}
