package com.example.tumbaburros.hackerrank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Function;

public class QuickTest {

  public static void rightView(TreeNode curr, List<Integer> result, int currDepth){
    if(curr == null){
      return;
    }
    if(currDepth == result.size()){
      result.add(curr.val);
    }

    rightView(curr.right, result, currDepth + 1);
    rightView(curr.left, result, currDepth + 1);

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

     // System.out.println(decodeString("2[abc]3[cd]ef"));

    }
}
