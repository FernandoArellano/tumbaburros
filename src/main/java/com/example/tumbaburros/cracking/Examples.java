package com.example.tumbaburros.cracking;



import java.util.*;

public class Examples {

    //isUnique string has all unique character
    public static boolean isUnique(String s){

        if(s==null || s.length() == 0) return false;

        Map<Integer, Boolean> map = new HashMap<>();
        for(int i=0; i<s.length();i++){
            int c = s.charAt(i);
            if(map.containsKey(c)){
                return false;
            } else {
                map.put(c,true);
            }
        }
        return true;
    }


    //given 2 strings method decide if 1 is permutation of the other
    // is whitespace significant? case-sensitive?
    //arrays.sor o(n log n)

    public static boolean isPermutationCaseSensitive(String s1, String s2){

        if(s1 == null || s2 == null || s1.length() != s2.length()) return false;

        return getAsciiValueOfString(s1) == getAsciiValueOfString(s2);
    }

    public static boolean isPermutationNoCaseSensitive(String s1, String s2){
        return isPermutationCaseSensitive(s1.toLowerCase(), s2.toLowerCase());
    }

    private static int getAsciiValueOfString(String s1) {
        char[] array = s1.toCharArray();

        int total = 0;
        for(int c : array){
                total+= c;
        }
        return total;
    }

    //palindrome permutation

    public static boolean isPalindromePermutation(String s){

        Map<Integer, Integer> map = new HashMap<>();

        char [] array = s.toCharArray();
        int counter=0;

        for(int c: array){
            if(map.containsKey(c)){
                map.put(c,(map.get(c))+1);
            } else {
                map.put(c,1);
            }
        }
        for(int val: map.values()){
            if(val%2!=0){
                counter++;
            }
        }

        return counter<2;
    }

    //is one insert, remove or replace away pale -> rale yes one edit away
    //"fer" "er"  yes one remove away
    //"er" "fer" yes one insert away

    public static boolean isOneAway(String s1, String s2){
        if(s1.length() == s2.length()){
            return isOneEditReplace(s1,s2);
        } else if(s1.length()+1 == s2.length()){
            return isOneEditInsert(s1,s2);
        } else if(s1.length()-1 == s2.length()){
            return isOneEditInsert(s2,s1);
        }

        return false;
    }

    private static boolean isOneEditInsert(String shorter, String longer) {

        int index1 = 0;
        int index2 = 0;

        while(index1 < shorter.length() && index2 < longer.length()){
            if(shorter.charAt(index1) != shorter.charAt(index2)){
                if(index1 != index2){
                    return false;
                }
            } else {
                index1++;
            }
            index2++;
        }
        return true;
    }

    private static boolean isOneEditReplace(String s1, String s2) {
        boolean difference = false;
        for(int i=0; i<s1.length();i++){
            if(s1.charAt(i) != s2.charAt(i)){
                if(difference){
                    return false;
                }
                difference = true;
            }
        }
        return true;
    }

    //count consecutive and return smaller string
    //from aaaaabbbbbccccccddddeeeeeee -> a5b5v6d4e7 smaller return compressed

    public static String returnSmallerString(String s){

        int counter=0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length();i++){
            counter++;
            if(i+1 == s.length() || s.charAt(i) != s.charAt(i+1)){
                sb.append(s.charAt(i)).append(counter);
                counter=0;
            }
        }
        if(sb.toString().length()<s.length()){
            return sb.toString();
        } else {
            return s;
        }
    }


    public static void main(String[] args) {
        System.out.println(isUnique("Fernando"));
        System.out.println(isUnique("Fer"));
        System.out.println(isUnique(null));

        System.out.println("***********");
        System.out.println(isPermutationCaseSensitive("fer","Fer"));
        System.out.println(isPermutationCaseSensitive("fer","ref"));
        System.out.println(isPermutationCaseSensitive("fer","er f"));
        System.out.println(isPermutationCaseSensitive("fer","erf"));
        System.out.println(isPermutationCaseSensitive("fer","erF"));
        System.out.println("---");
        System.out.println(isPermutationNoCaseSensitive("fer","Fer"));

        System.out.println("***********");
        System.out.println(isPalindromePermutation("Fernando"));
        System.out.println(isPalindromePermutation("abbcccbba"));
        System.out.println(isPalindromePermutation("abbbbaccc"));

        System.out.println("***********");
        System.out.println(isOneAway("Fer","Ver"));
        System.out.println(isOneAway("Fer","Dar"));
        System.out.println(isOneAway("Fern","Fer"));
        System.out.println(isOneAway("Fer","Fern"));
        System.out.println(isOneAway("Fer","Fernando"));

        System.out.println("***********");

        System.out.println(returnSmallerString("aaaaabbbbcccdde"));
        System.out.println(returnSmallerString("aaabc"));

        System.out.println("***********");

        Node n = new Node(10);
        n.appendToTail(8);
        n.appendToTail(6);
        n.appendToTail(4);
        n.appendToTail(4);
        n.appendToTail(4);
        n.appendToTail(18);
        n.appendToTail(18);
        n.printData();
        n.removeDuplicates();
        n.printData();

        System.out.println("***********");
        Queue<String> queue = new LinkedList<>();
        queue.add("Fer");
        queue.add("Are");
        queue.add("Sal");
        queue.add("Jose");
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.peek());

        System.out.println("***********");
        Stack<String> stack = new Stack<>();
        stack.add("Zaz");
        stack.add("Mar");
        stack.add("Gar");
        stack.add("Cam");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.firstElement());

        System.out.println("***********");
        StackOfPlates stackOfPlates = new StackOfPlates(3);
        stackOfPlates.addPlate("Dish1");
        stackOfPlates.addPlate("Dish2");
        System.out.println(stackOfPlates.addPlate("Dish3"));
        System.out.println(stackOfPlates.removePlate());
        stackOfPlates.addPlate("Dish4");
        stackOfPlates.addPlate("Dish5");
        stackOfPlates.addPlate("Dish6");
        System.out.println(stackOfPlates.addPlate("Dish7"));
        System.out.println(stackOfPlates.removePlate());
    }
}
