package com.example.tumbaburros.refresher;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    @Test
    public void testEndNumber(){


        List<int[]> list = new ArrayList<>(){{
            add(new int[]{4,9});
            add(new int[]{2,7});
            add(new int[]{20,44});
        }};

        list.parallelStream().forEach(i-> assertEquals(Solution.runValues(i[0],i[1]), i[1]-i[0]));
    }
}