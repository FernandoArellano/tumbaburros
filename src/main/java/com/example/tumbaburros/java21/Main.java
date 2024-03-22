package com.example.tumbaburros.java21;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
      //  Runnable runnable = () -> System.out.println("Inside Runnable");
        //Thread virtualThread = Thread.ofVirtual().start(runnable);

        // After Java 21
        //String template
        //‘Hello {your actual variable value} welcome to the GeeksForGeeks!’


      //  boolean isEmojiChar = Character.isEmoji('?');

        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("JDK 17");
        deque.addLast("JDK 21");
        String front = deque.getFirst();  // "JDK 17"
        String back = deque.getLast();
    }
}
