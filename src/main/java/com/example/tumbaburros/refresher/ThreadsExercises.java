package com.example.tumbaburros.refresher;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadsExercises {

    AtomicInteger count = new AtomicInteger(0);

    static class Printer{
        int x=0;
        int max;

        Printer(int max){
            this.max = max;
        }

        public synchronized void printEven(){
            while(x<=max){
                if(x%2==0){
                    System.out.println(x);
                    x++;
                    notify();
                }else{
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public synchronized void printOdd(){
            while(x<=max){
                if(x%2!=0){
                    System.out.println(x);
                    x++;
                    notify();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    static class Buffer{
        public Queue<Integer> queue = new LinkedList<>();
        private final int capacity =5;

        public synchronized void consume(){
            while(queue.size()==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("consumed" +queue.poll());
            notifyAll();
        }

        public synchronized void produce(){
            while(queue.size()==capacity){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int x = new Random().nextInt(9);
            System.out.println("produced "+x);
            queue.add(x);
            notifyAll();
        }
    }

    public int raceCondition(int start, int end){

        IntStream.range(start, end).parallel().forEach(i-> count.incrementAndGet());

        return count.get();
    }

    public List<Integer> get100InList(){

        List<Integer> collect = IntStream.range(0, 100).boxed().parallel().collect(Collectors.toList());
        return collect;
    }

    public int sumNumbers(int start, int end){
        return IntStream.range(start, end).parallel().sum();
    }

    public LongAdder countLong(){
        LongAdder adder = new LongAdder();
        IntStream.range(0,1_000_000).parallel().forEach(i->adder.increment());
        return adder;
    }

    public int executorsMultiply(){
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        int result=0;

        for(int i=0;i<5; i++){
            int j=i;
            futures.add(CompletableFuture.supplyAsync(()->j*2, service));
        }

        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        result = all.thenApply(i-> futures.stream().mapToInt(CompletableFuture::join).sum()).join();

        service.shutdown();
        return result;
    }

    public static void main(String[] args) {

//        Buffer buffer = new Buffer();
//
//        Thread consumer = new Thread(()->{
//            while (true) {
//                buffer.consume();
//                try {
//                    Thread.sleep(800);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        Thread producer = new Thread(()->{
//            while (true) {
//                buffer.produce();
//                try {
//                    Thread.sleep(700);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        producer.start();
//        consumer.start();
        ThreadsExercises solution = new ThreadsExercises();
        int result = solution.executorsMultiply();

        System.out.println("Result: " + result);

        // Expected:
        // i = 0..4 → (0*2 + 1*2 + 2*2 + 3*2 + 4*2)
        // = 0 + 2 + 4 + 6 + 8 = 20
        System.out.println("Expected: 20");

    }
}
