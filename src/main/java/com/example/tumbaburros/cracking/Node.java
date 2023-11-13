package com.example.tumbaburros.cracking;

import java.util.HashSet;
import java.util.Set;

public class Node {
    Node next = null;
    int data;

    public Node(int data) {
        this.data = data;
    }

    void appendToTail(int d){
        Node end = new Node(d);
        Node n = this;
        while(n.next!=null){
            n=n.next;
        }
        n.next=end;
    }

    void printData(){
        Node n=this;
        while(n!=null){
            System.out.print(n.data+"->");
            n = n.next;
        }
        System.out.println();
    }

    void removeDuplicates(){
        Node n = this;
        Set<Integer> set = new HashSet<>();
        Node previous = null;
        while(n!=null){
            if( set.contains(n.data) ){
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n=n.next;
        }
    }
}
