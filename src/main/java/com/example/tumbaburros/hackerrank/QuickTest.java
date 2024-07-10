package com.example.tumbaburros.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class QuickTest {

    /*
        Binary Search

        You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell
        and potions[j] represents the strength of the jth potion.
        You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
        Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.

        Example 1:

        Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
        Output: [4,0,3]
        Explanation:
        - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
        - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
        - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
        Thus, [4,0,3] is returned.
     */
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {

        if(spells.length==0 || potions.length == 0){
            return new int[]{};
        }

        Arrays.sort(potions);
        int potionsSize = potions.length;
        int[] result = new int[spells.length];
        int spellCount = 0;
        for(int spell: spells){
            int min=0;
            int max = potionsSize-1;

             while(min<=max){
                 int mid = min+(max-min)/2;
              if(spell * ((long)potions[mid])>=success){
                  max = mid-1;
              } else {
                  min= mid+1;
              }
             }
                result[spellCount++] = potions.length-min;
            }
        return result;
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
        System.out.println(Arrays.stream(successfulPairs(new int[]{3,1,2}, new int[]{8,5,8}, 16)).boxed().collect(Collectors.toList()));
    }
}
