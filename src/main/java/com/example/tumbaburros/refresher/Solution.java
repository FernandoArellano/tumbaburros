package com.example.tumbaburros.refresher;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.lang.ThreadLocal;

import org.junit.Test;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

    static class Counter{
        AtomicInteger i = new AtomicInteger(0);

        public void increment(){
            i.addAndGet(1);
        }

        public AtomicInteger get(){
            return i;
        }
    }

    public static int runValues(int start, int end ){
        Counter counter = new Counter();
        System.out.println("current thread:" + Thread.currentThread().getName());
        IntStream.range(start,end).parallel().boxed().forEach(
                i-> {
                    System.out.println("Hilo Generado dentro de parallel");
                    counter.increment();}
        );
        System.out.println(counter.get());
        return counter.get().intValue();
    }
}