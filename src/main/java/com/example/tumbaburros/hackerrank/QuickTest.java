package com.example.tumbaburros.hackerrank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;


public class QuickTest {



  static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static void main(String[] args) {

        Map<String, List<String>> people = new HashMap<>();

        people.put("John", Arrays.asList("555-1123", "555-3389"));

        people.put("Mary", Arrays.asList("555-2243", "555-5264"));

        people.put("Steve", Arrays.asList("555-6654", "555-3242"));

        List<String> phones = people.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(phones);
       
    }
}
