package com.example.tumbaburros.refresher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CompleteImplementedCustomMap {

    public static void main(String[] args) {

        MyHashMap map = new MyHashMap();

        map.put("banana", 40);
        map.put("apple", 10);
        map.put("orange", 30);
        map.put("grape", 20);

        System.out.println("Original Map:");
        map.printMap();

        System.out.println("\nSorted By Values:");
        List<Entry> sorted = map.sortByValue();

        for (Entry e : sorted) {
            System.out.println(e.key + " -> " + e.value);
        }
    }
}

/*
 * Entry Node
 */
class Entry {
    String key;
    int value;

    Entry(String key, int value) {
        this.key = key;
        this.value = value;
    }
}

/*
 * Custom HashMap
 */
class MyHashMap {

    // Array size
    private final int SIZE = 10;

    // Buckets
    private LinkedList<Entry>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[SIZE];

        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /*
     * Hash Function
     */
    private int getBucketIndex(String key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    /*
     * Insert or Update
     */
    public void put(String key, int value) {

        int index = getBucketIndex(key);

        LinkedList<Entry> bucket = buckets[index];

        // Check if key already exists
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Add new entry
        bucket.add(new Entry(key, value));
    }

    /*
     * Get Value
     */
    public Integer get(String key) {

        int index = getBucketIndex(key);

        LinkedList<Entry> bucket = buckets[index];

        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    /*
     * Remove Key
     */
    public void remove(String key) {

        int index = getBucketIndex(key);

        LinkedList<Entry> bucket = buckets[index];

        bucket.removeIf(entry -> entry.key.equals(key));
    }

    /*
     * Print Map
     */
    public void printMap() {

        for (LinkedList<Entry> bucket : buckets) {

            for (Entry entry : bucket) {
                System.out.println(entry.key + " -> " + entry.value);
            }
        }
    }

    /*
     * Sort by Values
     */
    public List<Entry> sortByValue() {

        List<Entry> allEntries = new ArrayList<>();

        // Collect all entries
        for (LinkedList<Entry> bucket : buckets) {
            allEntries.addAll(bucket);
        }

        // Sort ascending by value
        Collections.sort(allEntries, (a, b) -> a.value - b.value);

        return allEntries;
    }
}
