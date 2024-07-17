package com.example.tumbaburros.hackerrank;

import java.util.*;

public class QuickTest {

    /*
        The Tribonacci sequence Tn is defined as follows:
        T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

        ex: Input: n = 4
            Output: 4
            Explanation:
            T_3 = 0 + 1 + 1 = 2
            T_4 = 1 + 1 + 2 = 4
     */

    public static int tribonacci(int n) {
        if(n==0){
            return 0;
        } else if(n==1){
            return 1;
        } else if(n==2){
            return 1;
        }

        Stack<Long> stack = new Stack<>();

        long sum =0;
        int count=0;
        while(count<n){
            if(stack.size()==0){
                stack.push(0L);
            } else if(stack.size()==1){
                stack.push(1L);
            } else if(stack.size()==2){
                stack.push(1L);
            } else {
                Stack<Long> tempStack = new Stack<>();
                tempStack.addAll(stack);
                long val = 0;
                for(int i=0; i<3;i++){
                    val+=tempStack.pop();
                }
                stack.push(val);
            }
            count++;
        }
        for(int i=0; i<3;i++){
            sum+=stack.pop();
        }
        return (int)sum;
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
        System.out.println(tribonacci(25));
    }
}
