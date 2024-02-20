package com.example.tumbaburros.general;

import java.util.*;

public class Colections {
    public static void line(){
        System.out.println("----------------------------------------");
    }
    public static void list(){
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(3);list.add(5);list.add(7);list.add(1);

        //list add index
        list.add(2,222);
        System.out.println(list);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1); list2.add(3);list2.add(5);list2.add(7);list2.add(1);

        //retain all from list2 in list
        list.retainAll(list2);
        System.out.println(list);

        List<Integer> list3 = new ArrayList<>();
        list3.add(1);

        //remove from the list all what matches in list3
        list.removeAll(list3);
        System.out.println(list);

        list.add(0);
        //reverse insertion order
        Collections.reverse(list);
        System.out.println(list);

        //get index from object, -1 if doesnt exist
       int index = list.indexOf(7);
        System.out.println(index);

        //reversed iterator insertion order
        //forEachRemaining element in list
        LinkedList<Integer> linked = new LinkedList<>(); linked.add(1);linked.add(10);linked.add(3);
        linked.descendingIterator().forEachRemaining(System.out::println);

        //non stream for each for lists
        list.forEach(System.out::println);
    }

    public static void collections(){
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(3);list.add(5);list.add(7);list.add(1);

        //shuffle elements
        Collections.shuffle(list);
        System.out.println(list);

        //reverse elements
        Collections.reverse(list);
        System.out.println(list);

        //sort elements
        Collections.sort(list, Comparator.naturalOrder());
        System.out.println(list);

        //Returns a dynamically typesafe view of the specified list. Any attempt to insert an element of the wrong type will result in an immediate ClassCastException
        //before diamond
        List<Integer> typesafe = Collections.checkedList(list,Integer.class);
        System.out.println(typesafe);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1); list.add(7);


        //Returns true if the two specified collections have no elements in common.
        System.out.println(Collections.disjoint(list, list2));

        //synchronized set, list, collection for hreads
        List<Integer> synchronizedList = Collections.synchronizedList(list);
        System.out.println(synchronizedList);

        //replaces all elements in list wich sent value
        Collections.fill(list,100);
        System.out.println(list);

        list.clear();
        list.add(1); list.add(3);list.add(5);list.add(7);list.add(1);
        System.out.println(list);

        //swap 2 elements positions
        Collections.swap(list,1,3);
        System.out.println(list);

        //max value in collection
        int max =Collections.max(list);
        System.out.println(max);

        int min = Collections.min(list);
        System.out.println(min);

        //gets position of element using binary search
        int seek = Collections.binarySearch(list,5,Comparator.naturalOrder());
        System.out.println(seek);

        //unmodifiable list, collection, set
        List<Integer> unmodifiable = Collections.unmodifiableList(list);
        System.out.println(unmodifiable);

        //remove if predicate true
        System.out.println(list);
        list.removeIf(i->i>3);
        System.out.println(list);
    }

    public static void maps(){
        Map<Integer, Integer> map = new HashMap<>();
       map.put(1,1); map.put(3,3); map.put(5,5);

       //if doesnt exist add it
       map.putIfAbsent(4,4);

        System.out.println(map.getOrDefault(10,555));


    }

    public static void main(String[] args) {
        list();line();
        collections();line();
        maps();line();
    }
}
