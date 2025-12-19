package com.example.tumbaburros.cache.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

/*
    Problem: Build a fixed-capacity LRU (Least Recently Used) cache that evicts the oldest entry when capacity is exceeded.

    Explanation:

    LinkedHashMap with accessOrder=true keeps entries ordered by access.
    Override removeEldestEntry to auto-evict when exceeding capacity.
    This is not thread-safe; wrap with synchronization or use ConcurrentHashMap variants for concurrency.
 */

public class LruCache<K,V> extends LinkedHashMap<K,V> {

    int capacity;

    public LruCache(int capacity){
        super(capacity,.75f, true);
        this.capacity= capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return this.size()>capacity;
    }

    public static void main(String[] args) {
        LruCache<Integer, String> lruCache = new LruCache<>(3);
        lruCache.put(1,"A");
        lruCache.put(2,"B");
        lruCache.put(3,"C");

        lruCache.get(1);
        lruCache.put(4,"D");
        System.out.println(lruCache);

        //result {3=C, 1=A, 4=D}  -> added A,B,C -> getA -> B,C,A (A es ahora latest used) -> addD -> removes oldest (B) -> C,A,D
    }


}
