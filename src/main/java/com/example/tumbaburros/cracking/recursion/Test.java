package com.example.tumbaburros.cracking.recursion;

import java.util.Arrays;

public class Test {

    public static int getPaths(int n){
        int[] array = new int[n+1];
        Arrays.fill(array,-1);
        return getPaths(array, n);
    }

    private static int getPaths(int[] array, int n) {
        if(n<0){
            return 0;
        } else if(n==0) {
            return 1;
        } else if(array[n] != -1){
            return array[n];
        } else {
            array[n] = getPaths(array, n-1) + getPaths(array, n-2) + getPaths(array,n-3);
            return array[n];
        }
    }

    private static int getMagicIndex(int[] array){
        return getMagicIndex(array, 0, array.length-1);
    }

    private static int getMagicIndex(int[] array, int start, int end) {
        if(start > end){
            return -1;
        }

        int mid = (start + end) / 2;
        if(array[mid]==mid){
            return mid;

        } else if(array[mid] < mid){
            return getMagicIndex(array, mid+1, end);
        } else {
            return getMagicIndex(array, start, mid-1);
        }
    }

    public static void main(String[] args) {

        System.out.println(getPaths(3));
        System.out.println(getPaths(10));

        System.out.println(getMagicIndex(new int[]{-10,-5,-2,0,1,5,8,10}));
    }
}
