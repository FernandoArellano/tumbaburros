package com.example.tumbaburros.hackerrank;

import java.util.*;

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
        System.out.println(singleNumber( new int[]{5,3,4,1,5,2,1,2,3,1,2,3,5,5,3,2,1}));
    }
}
