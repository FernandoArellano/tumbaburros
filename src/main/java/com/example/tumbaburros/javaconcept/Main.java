package com.example.tumbaburros.javaconcept;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void line(){
        System.out.println("----------------------------------------");
    }

    public static void pattern1(){
        int rows=9;
        int rowCount=1;
        for(int i=rows; i>0;i--){

            for(int j=i; j>0; j--){
                System.out.print(" ");
            }

            for(int j=1; j<=rowCount;j++){
                System.out.print(rowCount + " ");
            }
            System.out.println();
            rowCount++;
        }
    }

    public static void equality2Arrays(){
        int[] array = new int[]{1,2,3};
        int[] array2 = new int[]{3,2,1};
        System.out.println(Arrays.equals(array, array2));

        Arrays.sort(array);
        Arrays.sort(array2);
        System.out.println(Arrays.equals(array, array2));
    }

    //Arrays.deepEquals for multidimensional compare
    public static void equality2Arrays2Dimensions(){
        int[][] array = new int[][]{ {1,2,3}, {3,2,1} };
        int[][] array2 = new int[][]{ {1,2,3}, {3,2,1} };

        System.out.println(Arrays.equals(array, array2));
        System.out.println(Arrays.deepEquals(array, array2));

    }

    public static void anagram1(){
        String word1 = "keep";
        String word2 = "peek";

        char[] array1 = word1.toCharArray();
        char[] array2 = word2.toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);

        System.out.println(Arrays.equals(array1,array2));
    }

    //Character.getNumericValue
    public static void armstrong(){
        int num = 153;
        String s = String.valueOf(num);
        char[] array = s.toCharArray();

        int result =0;
        for(int i=0; i<array.length;i++){
            int temp = Character.getNumericValue(array[i]);   //Integer.parseInt(String.valueOf(array[i]));
            int pow = temp*temp*temp;
            result+=pow;
        }
        if(num==result){
            System.out.println("it is armstrong");
        } else{
            System.out.println("it is not armstrong");
        }
    }

    public static void countChars(){
        String word = "Fernando";

        Map<Character, Long> map = word.chars().mapToObj(i-> (char)i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }

    public static void intersection(){
        Integer[] i1 = {1, 2, 3, 4, 5, 4};

        Integer[] i2 = {3, 4, 5, 6, 7, 4};

        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(i1));

        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(i2));

        set1.retainAll(set2);

        System.out.println(set1);
    }

    public static void reverseEachString(){
        String word = "Ana Paulina Arellano Saldaña";
        String[] array = word.split(" ");
        List<String> list = Stream.of(array).map(s->new StringBuilder(s).reverse().toString()).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void decimalToBinary(){

        int number = 50;
        String binary = "";
        int rem =0;
        while(number>0){
            rem = number % 2;
            binary = rem + binary;
            number = number / 2;
        }
        System.out.println(binary);
    }

    public static void decimalToOctal(){

        int number = 1000;
        String octal = "";
        int rem =0;
        while(number>0){
            rem = number % 8;
            octal = rem + octal;
            number = number / 8;
        }
        System.out.println(octal);
    }

    public static void decimalToHexa() {
        char hexaDecimals[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int number = 2000;
        int rem =0;
        String hex="";

        while(number>0){
            rem = number%16;
            hex= hexaDecimals[rem]+hex;
            number = number/16;
        }
        System.out.println(hex);
    }

    

    public static void main(String[] args) {
        pattern1();System.out.println();line();
        equality2Arrays();line();
        equality2Arrays2Dimensions();line();
        anagram1();line();
        armstrong();line();
        countChars();line();
        intersection();line();
        reverseEachString();line();
        decimalToBinary();line();
        decimalToOctal();line();
        decimalToHexa();line();
    }
}
