package com.example.tumbaburros.refresher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadsExercises {

    AtomicInteger count = new AtomicInteger(0);

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
}
