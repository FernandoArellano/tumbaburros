package com.example.tumbaburros.hackerrank;

public class QuickTest {

    /*
    Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
    Return the number of good nodes in the binary tree.

    times node value is greater or equal to max value

        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);

        TreeNode three1 = new TreeNode(3,four, two);

        TreeNode three = new TreeNode(3, three1, null);

        System.out.println(goodNodes(three));

     */
    public static int counter =0;

    public static int goodNodes(TreeNode root) {

        Integer maxValue = Integer.MIN_VALUE;
        fillMaxVal(root, maxValue);


        return counter;
    }

    public static void fillMaxVal(TreeNode tree, Integer maxValue){
        if(tree.val>=maxValue){
            counter++;
            maxValue = tree.val;
        }

        if(tree.left != null){
            fillMaxVal(tree.left, maxValue);
        }
        if(tree.right != null){
            fillMaxVal(tree.right, maxValue);
        }
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
        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);

        TreeNode three1 = new TreeNode(3,four, two);

        TreeNode three = new TreeNode(3, three1, null);

        System.out.println(goodNodes(three));
    }
}
