package com.example.tumbaburros.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TrieNode {
    public TrieNode[] children;
    public PriorityQueue<String> pq;

    public TrieNode() {
        children = new TrieNode[26];
        pq = new PriorityQueue<>((a,b) -> b.compareTo(a));
    }

    public void addToPQ(String word) {
        pq.add(word);
        if (pq.size() > 3) pq.poll();
    }

    public List<String> getTopThree() {
        List<String> topThree = new ArrayList<>();
        while (!pq.isEmpty()) topThree.add(pq.poll());
        Collections.reverse(topThree);
        return topThree;
    }
}
