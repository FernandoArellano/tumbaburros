package com.example.tumbaburros.hackerrank;

import java.util.*;

public class QuickTest {

    /*
        You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
        You must choose a subsequence of indices from nums1 of length k.
        For chosen indices i0, i1, ..., ik - 1, your score is defined as:
        The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
        It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
        Return the maximum possible score.

        Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
        Output: 12
        Explanation:
        The four possible subsequence scores are:
        - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
        - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
        - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
        - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
        Therefore, we return the max score, which is 12.

       al ordenar el q se multiplica de mayor a menor, aseguras q se multiplique por el menor de los q se estan usando
       al hacer pop al mas pequeño quedas con los mas grandes  para la suma
       cuidado con el tipo de dato, el int no llega a sumas altas
     */
    public static long maxScore(int[] nums1, int[] nums2, int k) {
        long ans =0;
        List<int[]> list = new LinkedList<>();

        if(nums1.length == nums2.length){

            if(nums1.length==1){
                return nums1[0]*nums2[0];
            }



            for(int i=0; i<nums1.length;i++){
                list.add(new int[]{nums1[i],nums2[i]});
            }

            Collections.sort(list, (a, b) -> b[1]-a[1]);

            long sum=0;
            PriorityQueue<Integer> queue = new PriorityQueue<>();

            for(int[] array : list){
                sum+=array[0];

                if(queue.size()==k){
                    sum-= queue.poll();
                }
                if(queue.size()== k-1){
                    ans = Math.max(ans, sum*array[1]);
                }
                queue.add(array[0]);
            }
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
        System.out.println(maxScore(new int[]{1,3,3,2}, new int[]{2,1,3,4},3));
    }
}
