package com.example.tumbaburros.hackerrank;

import java.util.*;

public class QuickTest {

  /*
      Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the
      number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible,
      keep answer[i] == 0 instead.

       stack index for array position
       to calculate array result, position of the stack equals the i-stack pop only if temps in the stack position is less than actual temp
      results[stack.peek] in case there is a bigger one, will calculate i, less stack.pop and assign to the position using the stack.peek

       dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
   */
  public static int[] dailyTemperatures(int[] temps) {
    int[] results = new int[temps.length];
    Stack<Integer> stack = new Stack<>();
    /// UPVOTE !
    for (int i = 0; i < temps.length; i++) {
      while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
        results[stack.peek()] = i - stack.pop();
      }
      stack.push(i);
    }

    return results;
  }

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
      dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});

    }
}
