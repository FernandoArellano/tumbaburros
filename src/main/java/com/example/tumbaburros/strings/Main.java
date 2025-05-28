package com.example.tumbaburros.strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String s = "Jose fernando arellano saldaña";

        IntStream chars = s.chars();
        String v = chars.mapToObj(Character::toChars).map(c->new String(c)).collect(Collectors.joining(""));
        System.out.println(v);

        /*
            charCodeAt() is UTF-16, codePointAt() is Unicode. charCodeAt() returns a number between 0 and 65535. Both methods return an integer
            representing the UTF-16 code of a character, but only codePointAt() can return the full value of a Unicode value greather 0xFFFF (65535).
         */

        //optional of String
        System.out.println(s.describeConstable());

        System.out.println(s.indent(5));

        /* intern()
        if the pool already contains a string equal to this String object as determined by the equals(Object) method,
        then the string from the pool is returned. Otherwise, this String object is added to the pool and a reference to this String object is returned.
         */

        //transform
        String newS = s.transform(String::toUpperCase).transform(s1-> new StringBuilder(s1).reverse().toString());
        System.out.println(newS);

        System.out.println(s.subSequence(0,4));

        String s2= "Jose Ferbabdi\n\t dd";
        System.out.println(s2);
        System.out.println(s2.translateEscapes());

        String[] array = s.split(" ");
        Stream.of(array).forEach(System.out::println);

        List<String> lista = List.of("aaa","bbb");
        Stream.of(lista).forEach(System.out::println);
        lista.forEach(System.out::println);

        System.out.println(s.replace("fer", "XXX"));

        //matches for regular expression
        String Str = new String("Welcome to Tutorialspoint.com");

        System.out.print("Return Value :" );
        System.out.println(Str.matches("(.*)Tutorials(.*)"));

        int length = s.transform(String::length);
        System.out.println(length);

        //difference between string literal and new string because in case of new, interning doesn't happen automatically until you call the intern()
        //string literal automatically calls intern() ex: String s= "Fer";


        //compare to parameter less then -1, parameter bigger then 1
        String a = "a";
        String b = "b";
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));

        //String join
        String link="Un link a poner guiones";
        System.out.println(String.join("-",link.split(" ")));

        String fer = "jose fernando arellano saldaña";
        IntStream intStream = fer.chars();

        intStream.mapToObj(Character::toChars).forEach(System.out::println);
        int total = fer.chars().sum();
        System.out.println(total);


        System.out.println(String.join(";", new String[]{"fer","  aaa", "mdmd  f  f"}));

        //lines
        //returns a Stream of String for each \n
        String separado = "Jose\nFernando\nArellano\tSaldaña";

        separado.lines().forEach(System.out::println);

        //strip
        String toStrip = "      jose fernando       ";
        System.out.println(toStrip.strip());
        System.out.println(toStrip.stripIndent());
        System.out.println(toStrip.stripTrailing());

    }
}
