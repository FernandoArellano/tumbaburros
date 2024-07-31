package com.example.tumbaburros.hackerrank;

import java.util.*;

public class QuickTest {

    /*
        Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
        Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.

        ex 2,6,5
            primera corrida

            (a & 1) a es 2 por lo q es 10 en binario al multiplicar por 1 queda 00
            (b & 1) b es 6 por lo q es 110 en binario al multiplicar por 1 queda 000
            (c & 1) c es 5 por lo q es 101 en binario al multiplicar por 1 queda 001

            boolean aHasOne = (a & 1) == 1;  //false
            boolean bHasOne = (b & 1) == 1;  //false
            boolean cHasOne = (c & 1) == 1;  //true

            a = a>>1;  a pasa de ser 10 a ser 01 = int 1
            b = b>>1; b pasa de ser 110 a ser 011 = int 3
            c = c>>1; c pasa de ser 101 a ser 010 = int 2
            z = z>>1; z pasa de ser 110 a ser 011 = int 3

            segunda vuelta:
            a = a>>1;  a pasa de ser 1 a ser 0 = int 0
            b = b>>1; b pasa de ser 11 a ser 1 = int 1
            c = c>>1; c pasa de ser 10 a ser 1 = int 1
            z = z>>1; z pasa de ser 11 a ser 1 = int 1

            tercera vuelta:

            a = a>>1;  a pasa de ser 0 a ser 0 = int 0
            b = b>>1; b pasa de ser 1 a ser 0 = int 0
            c = c>>1; c pasa de ser 1 a ser 0 = int 0
            z = z>>1; z pasa de ser 1 a ser 0 = int 0
     */
    public static int minFlips(int a, int b, int c) {
        int ans = 0;


        int z = Math.max(c,Math.max(a,b));

        while(z>0){
            boolean aHasOne = (a & 1) == 1;
            boolean bHasOne = (b & 1) == 1;
            boolean cHasOne = (c & 1) == 1;
            if(aHasOne && bHasOne){
                if(!cHasOne)
                    ans += 2;
            }
            else if((aHasOne && !bHasOne) || (!aHasOne && bHasOne)){
                if(!cHasOne){
                    ans+=1;
                }
            }
            else{
                if(cHasOne)
                    ans+=1;
            }
            a = a>>1;
            b = b>>1;
            c = c>>1;
            z = z>>1;
        }
        return ans;

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
        System.out.println(minFlips( 2,6,5));
    }
}
