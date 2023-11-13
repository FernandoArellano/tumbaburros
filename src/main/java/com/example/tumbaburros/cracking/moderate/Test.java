package com.example.tumbaburros.cracking.moderate;

import java.util.Arrays;
import java.util.HashMap;

public class Test {

    public static void swapNumbers(int a, int b){
        System.out.println("a=" +a + " b=" + b);

        a= a-b;
        b = b+a;
        a= b-a;

        System.out.println("a=" +a + " b=" + b);
    }

    public static int getWordFrequency(String[] book, String lookWord){

        int count = 0;
        for(String word : book){
            if(word.equalsIgnoreCase(lookWord)){
                count++;
            }
        }
        return count;
    }

    public static int getWordFrequencyStream(String [] book, String lookWord){
        return (int)Arrays.stream(book).filter(b-> b.equals(lookWord)).count();
    }

    public static int getWordFrequencyMultipleAccess(HashMap<String, Integer> map, String lookWord){
        return map.getOrDefault(lookWord, 0);
    }

    public static HashMap<String, Integer> preFillDictionary(String[] book){
        HashMap<String, Integer> map = new HashMap<>();
        Arrays.stream(book).forEach(s -> {
            Integer integer = map.containsKey(s) ? map.put(s, map.get(s) + 1) : map.put(s, 1);
        });
        return map;
    }

    public static int[] smallestDifference(int[] a, int[] b){
        int difference = Integer.MAX_VALUE;
        int aPosition = 0;
        int bPosition = 0;
        int[] result = new int[2];
        Arrays.sort(a);
        Arrays.sort(b);
        while(aPosition<a.length && bPosition < b.length){
            if(Math.abs(a[aPosition]-b[bPosition]) < difference){
                difference = Math.abs(a[aPosition]-b[bPosition]);
                result[0] = a[aPosition];
                result[1] = b[bPosition];
            }

            if(a[aPosition]<b[bPosition]){
                aPosition++;
            } else {
                bPosition++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
           swapNumbers(5,3);
        swapNumbers(10,10);
        swapNumbers(5,8);
        swapNumbers(-5,3);
        swapNumbers(-5,-3);
        swapNumbers(5,-3);
        String word = "Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña Jose Fernando Arellano Saldaña ";
        String [] book = word.split(" ");
        HashMap<String,Integer> map = preFillDictionary(book);
        System.out.println(getWordFrequency(book, "Fernando"));
        System.out.println(getWordFrequencyStream(book, "Fernando"));
        System.out.println(getWordFrequencyMultipleAccess(map, "Fernando"));

        int[] smallestResult = smallestDifference(new int[]{10,5,4,8,6,1}, new int[]{11,40,7,12,9});
        System.out.println("[" + smallestResult[0] + "," + smallestResult[1]+ "]");
    }
}
