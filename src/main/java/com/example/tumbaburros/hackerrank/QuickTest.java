package com.example.tumbaburros.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class QuickTest {

    /*

     */
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {

        if(potions.length==0 || potions.length == 0){
            return new int[]{};
        }

        List<Integer> list = new LinkedList<>();

        Arrays.sort(potions);

        for(int spell: spells){
              int start = 0;
              int mid = start + (potions.length-1 - start)/2;

              while(mid>=0 && mid< potions.length){
                if(spell*potions[mid]>=success){
                    mid--;
                    if(mid>0){
                        mid = mid / 2;
                    }
                } else {
                    mid++;
                    mid = mid+mid/2;
                }

              }
        }
        return new int[]{};
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
        System.out.println(successfulPairs(new int[]{5,1,3}, new int[]{4,5,1,2,3}, 7));
    }
}
