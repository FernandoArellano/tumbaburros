package com.example.tumbaburros.refresher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomMapExample {

    //Build a hash map data structure for string keys and integer values,
    // without using util.HashMap, and the sort the Map based values.

    static class CustomMap<K, V extends Comparable<V>> implements Comparable<CustomMap<K,V>> {

        K key;
        V value;

        List<CustomMap<K, V>> list = new ArrayList<>();

        public CustomMap(){

        }

        public CustomMap(K key, V value){
            this.key = key;
            this.value = value;
        }

        public void save(K key, V val){
            this.key = key;
            this.value = val;
            list.add(new CustomMap<K, V>(key, value));
        }

        public List<CustomMap<K, V>> sort(){
            return this.list.stream().sorted(CustomMap::compareTo).collect(Collectors.toList());
        }


        @Override
        public int compareTo(CustomMap<K, V> o) {
            return this.value.compareTo(o.value);
        }

        @Override
        public String toString() {
            return this.key +":" + this.value;
        }
    }


    public static void main(String[] args) {

        List<CustomMap<String,Integer>> list = new ArrayList<>();

        CustomMap<String, Integer> m3 = new CustomMap<String, Integer>();

        m3.save("s3",3);
        m3.save("s1",1);
        m3.save("s2",2);
        System.out.println(m3.sort());
    }
}
