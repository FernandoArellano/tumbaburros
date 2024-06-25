package com.example.tumbaburros.hackerrank;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuickTest {

    /*
    Given an encoded string, return its decoded string.
    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
    Note that k is guaranteed to be a positive integer.
    You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
    Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
    For example, there will not be input like 3a or 2[4].
    The test cases are generated so that the length of the output will never exceed 105.
     */
    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for(char c : s.toCharArray()){
            if(c == ']'){
                StringBuilder sb = new StringBuilder();

                while(!stack.isEmpty() && stack.peek() != '['){
                        sb.insert(0,stack.pop());
                }
                //remove [
                stack.pop();

                StringBuilder numberBuilder = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())){
                        numberBuilder.insert(0,stack.pop());
                }

                result.append(sb.toString().repeat(Integer.valueOf(numberBuilder.toString())));

                for(Character character: result.toString().toCharArray()){
                    stack.push(character);
                }
                result = new StringBuilder();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }
        result.append(sb);
        return  result.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }
}
