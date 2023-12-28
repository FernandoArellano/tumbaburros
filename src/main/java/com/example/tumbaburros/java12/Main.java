package com.example.tumbaburros.java12;

import java.util.Optional;

public class Main {

    public static void line(){
        System.out.println("------------------------");
    }

    public static void indent(){
        System.out.println("Indent");
        String fer = "Fernando";
        fer = fer.indent(10);
        System.out.println(fer);
        fer = "Fernando";
        fer = fer.indent(-100);
        System.out.println(fer);
    }

    public static void transformTest (){
        System.out.println("Transform Test");
        String fer = "Fernando";
        int value = fer.transform(String::length);
        System.out.println(value);
        String s = fer.transform(String::toUpperCase);
        System.out.println(s);

    }

    public static void switchTest(String day){
        System.out.println("Switch 1");
        String result = switch(day){
            case "M","W", "F" -> "MWF";
            case "T","TH","S" -> "TTS";
            default -> "Sunday";
        };
        System.out.println(result);
    }

    public static void switchTest2(String day){
        System.out.println("Switch 2");
        int result = switch (day){
            case "M","W", "F": yield 1;
            case "T","TH","S": yield 2;
            default: yield 10;
        };
        System.out.println(result);
    }

    public static void describeConstable(){
        System.out.println("Describe Optional");
        Optional<String> optional = "Fernando".describeConstable();
        System.out.println(optional);
    }

    public static void main(String[] args) {
        indent();line();
        transformTest();line();
        switchTest("Sunday");line();
        switchTest2("W");line();
        describeConstable();line();
    }
}
