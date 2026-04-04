package com.example.tumbaburros.refresher;

import java.util.*;

public class InterviewExercize {

    public static void main(String[] args) {
        testingEquals();

        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));

        rotate(new int[]{1,2,3,4,5,6,7},3);

        System.out.println(canJump(new int[]{2,5,0,0}));



    }

    private static void testingEquals() {
        Set<String> s1 = new TreeSet<>(){{add("Data1"); add("Data2");}};
        Set<String> s2 = new TreeSet<>(){{add("Data2"); add("Data1");}};
        System.out.println(s1.equals(s2));

        List<String> l1 = new LinkedList<>(){{add("Data2"); add("Data1");}};
        List<String> l2 = new LinkedList<>(){{add("Data1"); add("Data2");}};
        System.out.println(l1.equals(l2));

        Map<String, String> m1 = Map.of("Data1","Data1","Data2","Data2");
        Map<String, String> m2 = Map.of("Data2","Data2","Data1","Data1");
        System.out.println(m1.equals(m2));
    }

    public static int removeDuplicates(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int index=0;

        for(int i:nums){
            if(map.containsKey(i)){
                int value = map.get(i);
                if(value>=2)
                    continue;
                else{
                    map.put(i, map.get(i)+1);
                }
            }else {
                map.put(i,1);
            }
            nums[index++] = i;
        }

        return index;
    }

    //rotate k positions to the right
    public static void rotate(int[] nums, int k) {

        int[] result = new int[nums.length];
        k = k % nums.length;

        for(int i=0; i< nums.length; i++){

            result[(i+k)%nums.length]= nums[i];
        }

        for(int i=0; i< nums.length; i++){
            nums[i]= result[i];
        }
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;

            for(int i=1; i<prices.length; i++){
                if(prices[i-1]<prices[i]){
                    maxProfit += prices[i]-prices[i-1];
                }
            }
            return maxProfit;
    }

    //2,3,1,1,4
    public static boolean canJump(int[] nums) {
        int position=0;
        int maxJump = nums[position];
        int maxNextJump=-1;
        int bestPosition=-1;

        if(maxJump == 0 && nums.length<=1){
            return true;
        }

        if(maxJump == 0){
            return false;
        }

        if(maxJump+1>=nums.length){
            return true;
        }

        while(maxNextJump!=0 && position<nums.length-1){
            maxNextJump=0;
            maxJump= nums[position];
            if(maxJump + position>=nums.length-1){
                return true;
            }
            for(int i=1; i<=maxJump; i++){
                if(position+i<nums.length && nums[position+i]>maxNextJump){
                    maxNextJump=nums[position+i];
                    bestPosition = position+i;
                }
            }
            position=position+bestPosition;
            bestPosition=0;
        }

        return position>=nums.length-1;
    }
}
