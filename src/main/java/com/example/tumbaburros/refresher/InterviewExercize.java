package com.example.tumbaburros.refresher;

import java.util.*;

public class InterviewExercize {

    public static void main(String[] args) {
        Set<String> s1 = new TreeSet<>(){{add("Data1"); add("Data2");}};
        Set<String> s2 = new TreeSet<>(){{add("Data2"); add("Data1");}};
        System.out.println(s1.equals(s2));

        List<String> l1 = new LinkedList<>(){{add("Data2"); add("Data1");}};
        List<String> l2 = new LinkedList<>(){{add("Data1"); add("Data2");}};
        System.out.println(l1.equals(l2));

        Map<String, String> m1 = Map.of("Data1","Data1","Data2","Data2");
        Map<String, String> m2 = Map.of("Data2","Data2","Data1","Data1");
        System.out.println(m1.equals(m2));
    }
}
