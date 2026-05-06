package com.example.tumbaburros.refresher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomMapExample {

    //Build a hash map data structure for string keys and integer values,
    // without using util.HashMap, and the sort the Map based values.

    static class CustomMap implements Comparable<CustomMap> {

        String key;
        Integer value;

        List<CustomMap> list = new ArrayList<>();

        public CustomMap(){

        }

        public CustomMap(String key, Integer value){
            this.key = key;
            this.value = value;
        }

        public void save(String key, Integer val){
            this.key = key;
            this.value = val;
            list.add(new CustomMap(key, value));
        }

        public List<CustomMap> sort(){
            return this.list.stream().sorted(CustomMap::compareTo).collect(Collectors.toList());
        }


        @Override
        public int compareTo(CustomMap o) {
            return this.value.compareTo(o.value);
        }

        @Override
        public String toString() {
            return this.key +":" + this.value;
        }
    }


    public static void main(String[] args) {

        List<CustomMap> list = new ArrayList<>();

        CustomMap m3 = new CustomMap();

        m3.save("s3",3);
        m3.save("s1",1);
        m3.save("s2",2);
        System.out.println(m3.sort());
    }
}
