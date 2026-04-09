package com.example.tumbaburros.refresher;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadsExercisesTest {

    @Test
    public void raceConditionTest(){
        List<int[]> list = new ArrayList<>(){{
            add(new int[]{4,9});
            add(new int[]{2,7});
            add(new int[]{20,44});
        }};

        list.parallelStream().forEach(i -> assertEquals(new ThreadsExercises().raceCondition(i[0], i[1]), i[1]-i[0]));
    }

    @Test
    public void collectList(){
        assertEquals(100, new ThreadsExercises().get100InList().size());
    }

    @Test
    public void sumNumbers(){
        assertEquals(15, new ThreadsExercises().sumNumbers(1,6));
    }

    @Test
    public void longAdder(){
        assertEquals(1_000_000, new ThreadsExercises().countLong().longValue());
    }

    @Test
    public void executorsTest(){
        assertEquals(20, new ThreadsExercises().executorsMultiply());
    }
}