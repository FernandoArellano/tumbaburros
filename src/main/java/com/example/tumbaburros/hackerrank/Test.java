package com.example.tumbaburros.hackerrank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    static Stack<int[]> st = new Stack<>();

    public static void line(){
        System.out.println("----------------------------------------");
    }

    // START ARRAY/STRINGS

    /*
         You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
         If a string is longer than the other, append the additional letters onto the end of the merged string.
         Return the merged string.
     */
    public static String mergeAlternately(String word1, String word2) {
        int size = word1.length() + word2.length();
        int counterA = 0;
        int counterB = 0;
        StringBuilder sb = new StringBuilder();
        while(sb.length()<size){
            if(counterA<word1.length()){
                sb.append(word1.charAt(counterA));
                counterA++;
            }
            if(counterB<word2.length()){
                sb.append(word2.charAt(counterB));
                counterB++;
            }
        }
        return sb.toString();
    }

    /*
      For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
      Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

      str1+str2 == str2+str1 in order to be a greatest common divisor, if not does not apply and return 0

      just need to confirm length of gcd and after that from 0 to length of gdc is the gdc

      ex: System.out.println(gcdOfStrings("ABABAB", "ABAB"));
      Input: str1 = "ABABAB", str2 = "ABAB"
      Output: "AB"
   */
    public static String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1)) return "";
        int gcd = getGcd(str1.length(), str2.length());
        return str1.substring(0, gcd);
    }

    public static int getGcd(int a, int b) {
        if (b==0) return a;
        return getGcd(b,a%b);
    }


    /*
        There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the
        ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
        Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.

        Note that multiple kids can have the greatest number of candies.

        find biggest and compare each kid + extra candies  to biggest

        ex: System.out.println(kidsWithCandies(new int[]{2,3,5,1,3}, 3));
     */
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new LinkedList<>();

        if(candies.length>=2 && candies.length<=100 && extraCandies>=1 && extraCandies<=50){
            int max = IntStream.of(candies).boxed().collect(Collectors.toList()).stream().max(Comparator.naturalOrder()).get();
            for(int i=0; i<candies.length; i++){
                if(candies[i]+extraCandies>=max){
                    result.add(true);
                } else {
                    result.add(false);
                }
            }
        }

        return result;
    }

    /*
      You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

      Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
      return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

      check if previous and next are 0 then available spot mas 1
      control was empty flag

      ex: System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 2));

   */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean wasPreviousEmpty = true;
        int availableSpots = 0;

        if(flowerbed.length ==1 && flowerbed[0]==0){
            return true;
        }

        for(int i=0; i<flowerbed.length; i++){
            if(flowerbed[i]==0&&wasPreviousEmpty){
                if((i+1< flowerbed.length && flowerbed[i+1]==0) || i+1 == flowerbed.length){
                    availableSpots++;
                    wasPreviousEmpty = false;
                }
            } else if(flowerbed[i]==0 && !wasPreviousEmpty){
                wasPreviousEmpty = true;
            } else if(i==0 && flowerbed[i]==1){
                wasPreviousEmpty = false;
            } else if(flowerbed[i]==1){
                wasPreviousEmpty = false;
            }
        }
        if(n<=availableSpots){
            return true;
        }
        return false;
    }

    /*
          Given a string s, reverse only all the vowels in the string and return it.
          The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

          stack vowels only and pop in reverse order when vowel found

          ex: System.out.println(reverseVowels("hello"));
       */
    public static String reverseVowels(String s) {
        HashSet<Character> vowelList = new HashSet<>();
        vowelList.addAll(Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            if(vowelList.contains(c)){
                stack.push(c);
            }
        }

        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            if(vowelList.contains(c)){
                sb.append(stack.pop());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /*
      Given an input string s, reverse the order of the words.
      A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
      Return a string of the words in reverse order concatenated by a single space.
      Note that s may contain leading or trailing spaces or multiple spaces between two words.
      The returned string should only have a single space separating the words. Do not include any extra spaces.

      trim removes spaces at start and end
      replaceAll(" +", " ")  will replace many spaces together for only 1 space

      ex: System.out.println(reverseWords("a good   example"));
   */
    public static String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        s = s.trim().replaceAll(" +", " ");
        String[] words = s.split(" ");
        for(int i= words.length-1; i>=0; i--){
            stringBuilder.append(words[i]);
            if(i>0){
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    /*
          Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
          The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
          You must write an algorithm that runs in O(n) time and without using the division operation.

          2 different for = o(n)
          left to right, index 0 = 1, multiply per 1 is like we ignore it to multiply by it
                          last index will have result of multiply all except last
          right to left multiply last index answer per 1 keeps result, multiply to the left and will get result

          ex: List<Integer> result = Arrays.stream(productExceptSelf(new int[]{1,2,3,4})).boxed().collect(Collectors.toList());
              System.out.println(result);
       */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Calculate the product of all elements to the left of each element
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            answer[i] = leftProduct;
            leftProduct *= nums[i];
        }

        // Calculate the product of all elements to the right of each element
        // and multiply it with the current value in the ans array
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return answer;
    }

    /*
  Given an integer array nums, return true if there exists a triple of indices (i, j, k)
  such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

  ex:
  Input: nums = [2,1,5,0,4,6]
  Output: true
  Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.

  System.out.println(increasingTriplet(new int[]{2,1,5,0,4,6}));
*/
    public static boolean increasingTriplet(int[] nums) {

        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;


        for(int big : nums) {
            if(big <= small) {
                small = big;
            }
            else if(big <= mid) {
                mid = big;
            }
            else return true;
        }
        return false;
    }


    /*
            Given an array of characters chars, compress it using the following algorithm:
            Begin with an empty string s. For each group of consecutive repeating characters in chars:
            If the group's length is 1, append the character to s.
            Otherwise, append the character followed by the group's length.
            The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
            Note that group lengths that are 10 or longer will be split into multiple characters in chars.
            After you are done modifying the input array, return the new length of the array.
            You must write an algorithm that uses only constant extra space.

            Input: chars = ["a","a","b","b","c","c","c"]
            Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
            Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
     */
    public static int compress(char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();
        char previousChar = Character.MAX_VALUE;
        int count=0;

        if(chars.length == 1){
            return 1;
        }

        for(int i=0; i<chars.length;i++){
            char c = chars[i];

            if(previousChar == c && i<chars.length){
                count++;
            }
            if(previousChar != c || i==chars.length-1) {
                if(count>0){
                    count++;
                    String countString = String.valueOf(count);
                    for(char cc: countString.toCharArray()){
                        stringBuilder.append(cc);
                    }

                    count =0;
                }

                if(i!=chars.length-1 || (i==chars.length-1 && count==0 && previousChar != c)){
                    stringBuilder.append(c);
                }


            }
            previousChar = c;

        }

        for(int i=0; i<stringBuilder.length();i++){
            chars[i] = stringBuilder.charAt(i);
        }


        return stringBuilder.length();
    }

    // ENDS ARRAY/STRINGS


    //START 2 POINTERS
         /*
      Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
      Note that you must do this in-place without making a copy of the array.

      ex: moveZeroes(new int[]{0,1,0,3,12});
   */
    public static void moveZeroes(int[] nums) {
        int index=0;
        int counter = 0;
        for(int i=0; i<nums.length;i++){
            if(nums[i]!=0){
                nums[index]= nums[i];
                index++;
            } else {
                counter++;
            }
        }
        for(int i=0; i<counter;i++){
            nums[nums.length-1-i] = 0;
        }
    }

    /*
    Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
    A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
    of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

    Input: s = "abc", t = "ahbgdc"
    Output: true

     System.out.println(isSubsequence("abc", "ahbgdc"));
 */
    public static boolean isSubsequence(String s, String t) {
        Queue<Character> queue = new LinkedList<>();

        for(char c: s.toCharArray()){
            queue.add(c);
        }

        for(char c: t.toCharArray()){
            if(s.contains(String.valueOf(c))){
                if(!queue.isEmpty() && c== queue.peek()){
                    queue.poll();
                }
            }
        }

        if(!queue.isEmpty()){
            return false;
        }
        return true;
    }


    /*
      You are given an integer array height of length n. There are n vertical lines drawn such that
      the two endpoints of the ith line are (i, 0) and (i, height[i]).
      Find two lines that together with the x-axis form a container, such that the container contains the most water.

      Input: height = [1,8,6,2,5,4,8,3,7]
      Output: 49
      Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
      In this case, the max area of water (blue section) the container can contain is 49.

Return the maximum amount of water a container can store.
   */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left=0;
        int right = height.length-1;

        while(left<right){
            int total = getTotal(height, left, right);
            if(total>maxArea){
                maxArea = total;
            }

            if(height[left]<height[right]){
                left++;
            } else {
                right--;
            }

        }
        return maxArea;
    }
    private static int getTotal(int[] height, int left, int right) {
        int multiplier = right - left;
        int minValue = Math.min(height[left], height[right]);
        int total = minValue*multiplier;
        return total;
    }

    /*
      You are given an integer array nums and an integer k.
      In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
      Return the maximum number of operations you can perform on the array.

      Example 1:
      Input: nums = [1,2,3,4], k = 5
      Output: 2
      Explanation: Starting with nums = [1,2,3,4]:
      - Remove numbers 1 and 4, then nums = [2,3]
      - Remove numbers 2 and 3, then nums = []
      There are no more pairs that sum up to 5, hence a total of 2 operations.

      System.out.println(maxOperations(new int[]{1,2,3,4},5));
   */
    public static int maxOperations(int[] nums, int k) {
        int operations =0;

        Map<Integer, Long> map = IntStream.of(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Set<Integer> used = new HashSet<>();

        for(Map.Entry<Integer, Long> entry : map.entrySet()){
            int key = entry.getKey();
            int toLook = k-key;
            if(key == toLook){
                operations += entry.getValue()/2;
            } else if(map.containsKey(toLook) && !used.contains(toLook)) {
                operations+= Math.min(entry.getValue(),map.get(toLook));
            }
            used.add(key);
            used.add(toLook);
        }

        return operations;
    }

    //END 2 POINTERS

    //START SLIDING WINDOW

    /*
      You are given an integer array nums consisting of n elements, and an integer k.
      Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
      Any answer with a calculation error less than 10-5 will be accepted.

      System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3},4));
   */

    public static double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int index=0;
        java.math.BigDecimal result= new java.math.BigDecimal(0);

        if(nums.length>=k){
            while(index+k<=nums.length){
                int tempMax =0;
                for(int i=index, counter=0; counter<k; i++,counter++){
                    tempMax+=nums[i];
                }
                if(tempMax>max){
                    max=tempMax;
                }
                index++;
            }
            result = new java.math.BigDecimal(max).divide(new java.math.BigDecimal(k),5,java.math.BigDecimal.ROUND_HALF_UP);
        } else if(nums.length>0){
            for(int i=0; i<nums.length;i++){
                max+= nums[i];
            }
            result = new java.math.BigDecimal(max).divide(new java.math.BigDecimal(nums.length),5,java.math.BigDecimal.ROUND_HALF_UP);
        }


        return result.doubleValue();
    }

    /*
    Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
    Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

    get vowels of size k from the beggining
    after that remove initial character and rest vowel if it was and add vowel+1 if current character is vowel

    ex:System.out.println(maxVowels("abciiidef",3));

 */
    public static int maxVowels(String s, int k) {

        String vowels = "aeiou";

        int maxVowelCount = 0;
        int currentVowelCount = 0;

        //vowels before k
        for (int i = 0; i < k; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                currentVowelCount++;
            }
        }

        maxVowelCount = Math.max(maxVowelCount, currentVowelCount);


        for (int i = k; i < s.length(); i++) {

            //after counting before k vowels, rest 1 if before starting character was a vowel
            if (vowels.indexOf(s.charAt(i - k)) != -1) {
                currentVowelCount--;
            }

            //add vowel count if current character is vowel
            if (vowels.indexOf(s.charAt(i)) != -1) {
                currentVowelCount++;
            }

            maxVowelCount = Math.max(maxVowelCount, currentVowelCount);
        }
        return maxVowelCount;
    }

    /*
        Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's

        keep track of biggest, start only changes when there is a need to remove a 0 from the consecutive used
        to know number of elements current iteration i-start +1 due to index 0

     */
    public static int longestOnes(int[] nums, int k) {
        int start=0;
        int biggest=0;
        int zeros=0;

        for(int i=0; i<nums.length;i++){
            zeros+= nums[i]==0 ? 1 : 0;

            while(zeros>k){
                zeros -= nums[start]==1 ? 0 :1;
                start++;
            }

            biggest = Math.max(biggest, ((i-start)+1));
        }
        return biggest;
    }

    /*
    Given a binary array nums, you should delete one element from it.
    Return the size of the longest non-empty subarray containing only 1's in the resulting array.
    Return 0 if there is no such subarray.

    iterate one by one, if there is already more than 1 zero, need to move starting evaluating point
    until you remove 1 of the zeros, keep the biggest in the process

    System.out.println(longestSubarray(new int[]{1,1,0,1}));
 */
    public static int longestSubarray(int[] nums) {
        int start=0;
        int biggest=0;
        int zeros=0;

        for(int i=0; i<nums.length;i++){
            zeros+= nums[i]==0 ? 1 : 0;

            while(zeros>1){
                zeros -= nums[start]==1 ? 0 :1;
                start++;
            }

            biggest = Math.max(biggest, i-start);
        }
        return biggest;
    }

    //END SLIDING WINDOW

    //START PREFIX SUM

    /*
        There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
        The biker starts his trip on point 0 with altitude equal 0.
        You are given an integer array gain of length n where gain[i] is the net gain in altitude
        between points i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.

        starting in zero, when was it highest  0,-5,4,2 = 1
         0-5=-5
        -5+4=-1
        -1+2=1
     */
    public static int largestAltitude(int[] gain) {
        int max = 0;
        int temp=0;
        for(int x: gain){
            temp+= x;
            if(temp>max){
                max=temp;
            }
        }
        return max;
    }

    /*
    Given an array of integers nums, calculate the pivot index of this array.
    The pivot index is the index where the sum of all the numbers strictly to the left of the index
    is equal to the sum of all the numbers strictly to the index's right.
    If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
    Return the leftmost pivot index. If no such index exists, return -1.

    sum to left side of the evaluating only
    rest left and actual to the sum of total will give right side

    System.out.println(pivotIndex(new int[]{1,2,3}));
 */
    public static int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).boxed().mapToInt(i->new Integer(i)).sum();

        int leftSide =0;
        int rightSide =0;

        for(int i=0; i<nums.length; i++){
            int actual= nums[i];

            if(i-1>=0){
                leftSide+=nums[i-1];
            }
            rightSide= sum-leftSide-actual;

            if(leftSide==rightSide){
                return i;
            }
        }
        return -1;
    }

    //END PREFIX SUM


    //START HASHMAP/SET

    /*
       Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
       answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
       answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
       Note that the integers in the lists may be returned in any order.

       distinct lists then remove from one list all in the other so will keep the ones in that list and not in the other

       ex: System.out.println(findDifference(new int[]{1,2,3},new int[]{2,4,6}));
     */
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<Integer> n1 = Arrays.stream(nums1).boxed().distinct().toList();
        List<Integer> n2 = Arrays.stream(nums2).boxed().distinct().toList();

        List<Integer> result1= new ArrayList<>(n1);
        result1.removeAll(n2);

        List<Integer> result2 = new ArrayList<>(n2);
        result2.removeAll(n1);

        List<List<Integer>> list = new ArrayList<>();
        list.add(result1);
        list.add(result2);

        return list;
    }

    /*
        Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.

        keep track of number of occurrences, if that is not equal after remove distinct then false

        ex: System.out.println(uniqueOccurrences(new int[]{1,2,2,1,1,3}));
     */
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i:arr){
            if(map.containsKey(i)){
                map.put(i, (map.get(i))+1);
            } else {
                map.put(i, 1);
            }
        }

        List<Integer> all = map.values().stream().toList();
        if(all.size() == all.stream().distinct().collect(Collectors.toList()).size()){
            return true;
        } else{
            return false;
        }
    }

    /*
     Two strings are considered close if you can attain one from the other using the following operations:

     Operation 1: Swap any two existing characters.
     For example, abcde -> aecdb
     Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
     For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
     You can use the operations on either string as many times as necessary.

     Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

        if one letter is equal 0 in array 1 and different than 0 in the other array then it can be swapt
        int array of all letters, add number of letters per letter
        sort numbers in array, if all numbers in array 1 and array 2 match then it can be swap and work


     ex: System.out.println(closeStrings("abc","bca"));
  */
    public static boolean closeStrings(String word1, String word2) {

        int[] array1 = new int[26];
        int[] array2 = new int[26];

        if(word1.length() != word2.length()){
            return false;
        }

        for(int i=0; i<word2.length(); i++){
            array1[word1.charAt(i)-'a'] +=1;
            array2[word2.charAt(i)-'a'] +=1;
        }

        for(int i=0; i<array1.length;i++){
            if(array1[i]==0 && array2[i]!=0 || array2[i]==0 && array1[i] !=0){
                return false;
            }
        }

        Arrays.sort(array1);
        Arrays.sort(array2);

        for(int i=0; i<array1.length;i++){
            if(array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }

    /*
   Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
   A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

    crea una lista con los rows: 3,1,2,2, etc
    crea una lista con los columns: 3,1,2,2, etc

    si esta en ambas listas counter+1

   System.out.println(equalPairs(new int[][]{{3,1,2,2},{1,4,4,4},{2,4,2,2},{2,5,2,2}}));
    */
    public static int equalPairs(int[][] grid) {

        List<String> rowsList = new ArrayList<>();
        List<String> columnsList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int counter =0;

        for(int i=0; i<grid.length;i++){
            for(int j=0; j< grid.length;j++) {
                sb.append(grid[i][j]);
                if(j< grid.length-1){
                    sb.append(",");
                }
            }
            rowsList.add(sb.toString());
            sb = new StringBuilder();
        }

        for(int i=0; i<grid.length;i++){
            for(int j=0; j< grid.length;j++) {
                sb.append(grid[j][i]);
                if(j< grid.length-1){
                    sb.append(",");
                }
            }
            columnsList.add(sb.toString());
            sb = new StringBuilder();
        }

        for(String s: rowsList){
            if(columnsList.contains(s)){
                counter+=columnsList.stream().filter(c->s.equals(c)).count();
            }
        }


        return counter;

    }
    //END HASHMAPSET


    //BINARY SEARCH

    /*
    We are playing the Guess Game. The game is as follows:
    I pick a number from 1 to n. You have to guess which number I picked.
    Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
    You call a pre-defined API int guess(int num), which returns three possible results:

    -1: Your guess is higher than the number I picked (i.e. num > pick).
    1: Your guess is lower than the number I picked (i.e. num < pick).
    0: your guess is equal to the number I picked (i.e. num == pick).

    int guess(int num); returns 0,-1,1

    binary search -> start + (start+end)/2
 */
    public static int guessNumber(int n) {

        int start = 1;
        int end = n;

        while(start<=end){
            int mid = (start + end)/2;
            int num=0;

            //num = guess(mid);

            if(num == 0){
                return mid;
            } else if(num==-1){
                end = mid-1;
            } else{
                start = mid+1;
            }
        }
        return start;
    }

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

    /*
        BINARY SEARCH
        A peak element is an element that is strictly greater than its neighbors.
        Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
        You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

        EX: [1,2]  index 1 is bigger than previous and after (empty)
        System.out.println(findPeakElement(new int[]{6,5,4,3,2,3,2}));
     */
    public static int findPeakElement(int[] nums) {

        if(nums.length==1){
            return 0;
        }

        if(nums[0] > nums[1]){
            return 0;
        }

        if(nums[nums.length-1]>nums[nums.length-2]){
            return nums.length-1;
        }

        if(nums.length==2){
            if(nums[0]<nums[1]){
                return 1;
            } else {
                return 0;
            }
        }

        int min=1;
        int max= nums.length-2;

        while(min<=max){
            int mid = min + (max-min)/2;
            if(nums[mid-1]< nums[mid] && nums[mid] > nums[mid+1]){
                return mid;
            } else if(nums[mid] < nums[mid-1]){
                max = mid-1;
            } else if(nums[mid] < nums[mid+1]){
                min = mid+1;
            }
        }
        return -1;
    }

    /*
        BINARY SEARCH
        Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
        Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
        If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
        Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
        Return the minimum integer k such that she can eat all the bananas within h hours.

        piles = [3,6,7,11], h = 8
        EX:
        k = 3,
        3/3 + 6/3 + 7/3 + 11/3 = 1+ 2+ 3 + 4 = 10hrs > 8 hrs = Not enough time

        k = 4,
        3/4 + 6/4 + 7/4 + 11/4 = 1 + 2 + 2 + 3 = 8hrs = GOOD

        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20},6));
     */

    public static int minEatingSpeed(int[] piles, int h) {

        if(piles.length==0 || h == 0){
            return 0;
        }

        long end = -1;

        for(int i: piles){
            if(i>end){
                end = i;
            }
        }

        long start =1;
        long k = Long.MAX_VALUE;

        while(start<=end){
            long result =0;
            long mid = start + (end-start)/2;

            for(int pile : piles){
                long divider;
                if(pile>=mid){
                    divider = pile/mid;
                    long remaining = pile%mid;
                    if(remaining>0){
                        divider+=1;
                    }
                } else {
                    divider=1;
                }


                result +=divider;
            }

            if(result<=h){
                end = mid-1;
                if(mid<k){
                    k= mid;
                }
            } else{
                start = mid+1;
            }
        }

        return (int)k;
    }

    // END BINARY SEARCH


    // BACKTRACKING BINARY SEARCH

/*
        Given a String containing digits from 2-9 incusive, return all possible letter combinations that the number could represent.
        Return the answer in ANY order.

        Example:
        input: digits="23"
        output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        System.out.println(letterCombinations("23"));
     */
public static List<String> letterCombinations(String digits) {
    List<String> resultList = new ArrayList<>();
    if(digits.length()==0){
        return resultList;
    }

    Map<Integer, String> map = new HashMap<>();
    map.put(2,"abc");
    map.put(3,"def");
    map.put(4,"ghi");
    map.put(5,"jkl");
    map.put(6,"mno");
    map.put(7,"pqrs");
    map.put(8,"tuv");
    map.put(9,"wxyz");

    StringBuilder sb = new StringBuilder();
    int index =0;

    solve(digits, sb,index,resultList,map);

    return resultList;
}

    private static void solve(String digit, StringBuilder output, int index, List<String> resultList, Map<Integer, String> map) {

        if(digit.length()==index){
            resultList.add(output.toString());
            return;
        }

        String options = map.get(Character.getNumericValue(digit.charAt(index)));

        //append first letter, then recursive for next index to all combinations, then remuve last digit to move
        //to next option letter
        for(int i=0; i<options.length(); i++){
            output.append(options.charAt(i));
            solve(digit,output,index+1, resultList,map);
            output.deleteCharAt(index);
        }

    }

     /*
        Find all valid combinations of k numbers that sum up to n such that the following conditions are true
        -only numbers 1 through 9 are used
        -each number is used at most once on each combination
        Return a list of all possible valid combinations, the list must not contain the same combination twice
        and the combinations may be return in any order

        Ex:
        input: k=3, n=9
        output: [[1,2,6],[1,3,5],[2,3,4]]
        1+2+6=9
        1+3+5=9
        2+3+4=9
     */
     public static List<List<Integer>> combinations(int k, int n){
         List<List<Integer>> ans = new ArrayList<>();
         combination(ans, new ArrayList<Integer>(), k, 1, n);
         return ans;
     }

    private static void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
        if (comb.size() == k && n == 0) {
            List<Integer> li = new ArrayList<Integer>(comb);
            ans.add(li);
            return;
        }
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            combination(ans, comb, k, i+1, n-i);
            comb.remove(comb.size() - 1);
        }
    }

    // END BACKTRACKING


    //DP 1D

    /*
        The Tribonacci sequence Tn is defined as follows:
        T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

        ex: Input: n = 4
            Output: 4
            Explanation:
            T_3 = 0 + 1 + 1 = 2
            T_4 = 1 + 1 + 2 = 4
     */
    public static int tribonacci(int n) {
        if(n==0){
            return 0;
        } else if(n==1){
            return 1;
        } else if(n==2){
            return 1;
        }

        Stack<Long> stack = new Stack<>();

        long sum =0;
        int count=0;
        while(count<n){
            if(stack.size()==0){
                stack.push(0L);
            } else if(stack.size()==1){
                stack.push(1L);
            } else if(stack.size()==2){
                stack.push(1L);
            } else {
                Stack<Long> tempStack = new Stack<>();
                tempStack.addAll(stack);
                long val = 0;
                for(int i=0; i<3;i++){
                    val+=tempStack.pop();
                }
                stack.push(val);
            }
            count++;
        }
        for(int i=0; i<3;i++){
            sum+=stack.pop();
        }
        return (int)sum;
    }

    /*
        You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
        You can either start from the step with index 0, or the step with index 1.
        Return the minimum cost to reach the top of the floor.

        Ex: Input: cost = [1,100,1,1,1,100,1,1,100,1]
        Output: 6
        Explanation: You will start at index 0.
        - Pay 1 and climb two steps to reach index 2.
        - Pay 1 and climb two steps to reach index 4.
        - Pay 1 and climb two steps to reach index 6.
        - Pay 1 and climb one step to reach index 7.
        - Pay 1 and climb two steps to reach index 9.
        - Pay 1 and climb one step to reach the top.
        The total cost is 6.
     */
    public static int minCostClimbingStairs(int[] cost) {

        int n= cost.length;
        int[] temp = new int[n+1];
        Arrays.fill(temp,-1);
        return minCost(cost,n,temp);
    }

    public static int minCost(int[] cost, int n, int[] temp){

        if(n==0 || n==1){
            return temp[n] = 0;
        }

        if(temp[n] != -1){
            return temp[n];
        }

        if(n==2){
            return temp[n]=Math.min(cost[0],cost[1]);
        }

        return temp[n] = Math.min(minCost(cost, n-1, temp)+cost[n-1], minCost(cost, n-2, temp)+cost[n-2]);

    }

    /*
        You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
        the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically
        contact the police if two adjacent houses were broken into on the same night.
        You can skip as many houses as youd like

        Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

        Input: nums = [2,7,9,3,1]
        Output: 12
        Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        Total amount you can rob = 2 + 9 + 1 = 12.
     */

    public static int rob(int[] nums) {
        int prev = nums[0];
        int prev2 = 0;

        for(int i=1; i<nums.length;i++){
            int take = nums[i];
            take += prev2;
            int nonTake = prev;
            int max = Math.max(take, nonTake);

            prev2 = prev;
            prev = max;
        }
        return prev;
    }

    // END DP 1D

    //STARTS DP MULTIDIMENSIONAL
/*
        There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
        Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

        If the current position (i, j) is out of bounds (i >= m or j >= n), return 0 because it's not a valid path.
        If the current position (i, j) is the bottom-right corner of the grid (i == m-1 and j == n-1), return 1 because we've found a valid path.
        If the value at a[i][j] is greater than 0, return the stored value. This avoids redundant calculations (memoization).
        Otherwise, calculate the number of paths from the current cell by summing the number of paths from the cell to the right (i, j+1) and the cell below (i+1, j).
        Store the result in a[i][j] and return it.

        Input: m = 3, n = 2
        System.out.println(uniquePaths(3,2));
     */
    public static int uniquePaths(int m, int n) {

        int[][] array = new int[m][n];

        return dest(array, m,n,0,0);
    }

    public static int dest(int[][] array,int m, int n, int i, int j){
        if(i>=m || j>=n){
            return 0;
        }

        if(i==m-1 && j==n-1){
            return 1;
        }

        if(array[i][j] > 0){
            return array[i][j];
        }

        return array[i][j] = dest(array,m,n, i+1,j) + dest(array,m,n,i,j+1);
    }


    /*
        You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
        Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

        Note:
        You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
        The transaction fee is only charged once for each stock purchase and sale.

        ex:
            Input: prices = [1,3,2,8,4,9], fee = 2
            Output: 8
            Explanation: The maximum profit can be achieved by:
            - Buying at prices[0] = 1
            - Selling at prices[3] = 8
            - Buying at prices[4] = 4
            - Selling at prices[5] = 9
            The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
     */
    public static int maxProfit(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;

        for (int price : prices) {
            buy = Math.max(buy, sell - price);
            sell = Math.max(sell, buy + price - fee);
        }

        return sell;
    }



    //END DP MULTIDIMENSIONAL

    //BIT MANIPULATION

    /*
        Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

        result[i/2] gives the previous ones
        i%2 gives if new number has a new 1

        Ex: n=8
        number: [0][1][2][3][4][5][6][7][8]
        ones:    0  1  1  2  1  2  2  3  1
        result[i/2] = index 4 = 1
        i%2 = 0 for index res = 1

     */
    public static int[] countBits(int n) {
        int[] result = new int [n+1];
        for(int i=1;i<=n;i++){
            result[i] = result[i/2]+i%2;

        }
        return result;
    }

    /*
        Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
        You must implement a solution with a linear runtime complexity and use only constant extra space.

        ex: System.out.println(singleNumber( new int[]{5,3,4,1,5,2,1,2,3}));
        ^ negates its equal
          1,2,3,5 have 2 then are negated with each other leaving only 4
          if you have 4 numbers of any for example, 1 eliminates 1, 1 eliminates 1 so all are gone
     */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for(int i=0; i<n; i++){
            ans = ans^nums[i];
        }
        return ans;
    }


    /*
        Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
        Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.


        ex 2,6,5
        System.out.println(minFlips( 2,6,5));
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

    // END BIT MANIPULATION

    //TRIE

    /*
        A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
         There are various applications of this data structure, such as autocomplete and spellchecker.
        Implement the Trie class:
        Trie() Initializes the trie object.
        void insert(String word) Inserts the string word into the trie.
        boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
        boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

        Firstly, we create a nodes array that symbolizes our alphabet.
        Then, for each char in the word, we will traverse through using index. For each new letter, we will create a new Node.
        At the end of our word, we will signify that by changing that node's isEnd to be true.

        Ex:
            Input
            ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
            [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
            Output
            [null, null, true, false, true, null, true]

            Explanation
            Trie trie = new Trie();
            trie.insert("apple");
            trie.search("apple");   // return True
            trie.search("app");     // return False
            trie.startsWith("app"); // return True
            trie.insert("app");
            trie.search("app");     // return True
     */
    public static void testTrie2(String[] args) {
        Trie2 trie = new Trie2();
        trie.insert("apple");
        trie.search("apple");   // return True
        trie.search("app");     // return False
        trie.startsWith("app"); // return True
        trie.insert("app");
        trie.search("app");     // return True

    }


    /*
      You are given an array of strings products and a string searchWord.
      Design a system that suggests at most three product names from products after each character of searchWord is typed.
      Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix
      return the three lexicographically minimums products.
      Return a list of lists of the suggested products after each character of searchWord is typed.

      insert
      each letter is a node, for each letter of a product, the product itself is stored as an option
      [m][o][b][i][l][e]   each of this will have a suggested word mobile next
      [m][o][n][i][t][o][r] each of this will have a suggested word mobile next  ( index m and next o would have suggested as mobile and monitor

      search
      for each letter get top three of the index according to the letter

      suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
   */
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for (String product: products) insert(root, product);

        List<List<String>> results = new ArrayList<>();
        for (char c: searchWord.toCharArray()) {
            if ((root = root.children[c - 'a']) == null) break;
            results.add(root.getTopThree());
        }

        while (results.size() < searchWord.length())
            results.add(new ArrayList<>());
        return results;
    }

    private static void insert(TrieNode root, String word) {
        for (char c : word.toCharArray()) {
            if (root.children[c - 'a'] == null)
                root.children[c - 'a'] = new TrieNode();
            root = root.children[c - 'a'];
            root.addToPQ(word);
        }
    }

    //END TRIE

    //START INTERVALS

    /*

    Given an array of intervals intervals where intervals[i] = [starti, endi],
    return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping

    Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
    Output: 1
    Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

    order according to the end hour of the meeting
    if ending time of second is greater or equal to endint time of starting
    then count++ and new prev is the starting available hour

     System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
 */
    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int prev = 0;
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }
        return n - count;
    }

    /*
    There are some spherical balloons taped onto a flat wall that represents the XY-plane.
    The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal
    diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
    Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
    A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
    There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
    Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

    ex:
    Input: points = [[1,2],[2,3],[3,4],[4,5]]
    Output: 2
    Explanation: The balloons can be burst by 2 arrows:
    - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
    - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

    System.out.println(findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
 */
    public static int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 0;
        int end = points[0][1];
        for(int i = 1; i < points.length; i++) {
            if(points[i][0] <= end) {
                continue;
            } else {
                count++;
                end = points[i][1];
            }
        }
        count++;
        return count;
    }



    //END INTERVALS

    //START MONOTONIC STACK

        /*
      Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the
      number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible,
      keep answer[i] == 0 instead.

       stack index for array position
       to calculate array result, position of the stack equals the i-stack pop only if temps in the stack position is less than actual temp
       results[stack.peek] in case there is a bigger one, will calculate i, less stack.pop and assign to the position using the stack.peek

       dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});

       Input: temperatures = [73,74,75,71,69,72,76,73]
       Output: [1,1,4,2,1,1,0,0]
    */
    public static int[] dailyTemperatures(int[] temps) {
        int[] results = new int[temps.length];
        Stack<Integer> stack = new Stack<>();
        /// UPVOTE !
        for (int i = 0; i < temps.length; i++) {
            while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
                results[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }

        return results;
    }

    /*
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for
which the stock price was less than or equal to the price of that day.
For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8,
then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.

  ex: [100],[80],[60],[70],[60],[75],[85]]
  System.out.println(next(100));
  System.out.println(next(80));
  System.out.println(next(60));
  System.out.println(next(70));
  System.out.println(next(60));
  System.out.println(next(75));
  System.out.println(next(85));

    it 1: [100][1]
    it 2: [100][1],[80][1]
    it 3: [100][1],[80][1], [60][1]
    it 4: [100][1],[80][1],[70][2]; 70 es mas grande q 60 pero no q 80, hace pop al 60 y agrega su suma quedando en 2,
                                    si alguno es mas grande q 70 tmb lo sera q el 60, se suma el 2 q se guardo a 70 por ambos
    it 5: [100][1],[80][1],[70][2],[60][1]
    it 6: [100][1],[80][1],[75][4]
    it 7: [100][1],[85][6]


*/
    public static int next(int price) {
        int span = 1;

        while(st.size() > 0 && price >= st.peek()[0]){
            span += st.pop()[1];
        }

        st.push(new int[]{price, span});

        return span;
    }

    //END MONOTONIC STACK

    //START STACK

    /*
        You are given a string s, which contains stars *.
        In one operation, you can:
        Choose a star in s.
        Remove the closest non-star character to its left, as well as remove the star itself.
        Return the string after all stars have been removed.
        Note:
        The input will be generated such that the operation is always possible.
        It can be shown that the resulting string will always be unique.

        add to stack if non start add if start pop to remove the one on the left

         System.out.println(removeStars("erase*****"));
 */
    public static String removeStars(String s) {

        if(!s.contains("*")){
            return s;
        }

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='*'){
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        for(int i=0; i<stack.size();i++){
            sb.append(stack.get(i));
        }
        return sb.toString();
    }


    /*
        We are given an array asteroids of integers representing asteroids in a row.
        For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
        Each asteroid moves at the same speed.
        Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
        If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

        if empty add asteroid to stack
        not empty check if goes to same direction, if does add to stack
        if it goes to a different direction but first is going negative then add add to stack, they wont touch
         if it goes to a different direction but first goes positive then they will collide, keep bigger
        if stack has more continue comparing for bigger till stack empty or both go the same direction

         */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        boolean positiveInt = false;
        boolean positiveStack = false;

        for(int asteroid: asteroids){
            do{
                if(stack.isEmpty()){
                    stack.push(asteroid);
                    break;
                } else{
                    positiveInt = Integer.signum(asteroid)>=0?true:false;
                    positiveStack = Integer.signum(stack.peek())>=0?true:false;

                    if(positiveInt == positiveStack){
                        stack.push(asteroid);
                        break;
                    } else {
                        if(stack.peek()<0&&asteroid>0){
                            stack.push(asteroid);
                            break;
                        } else if(Math.abs(stack.peek())==Math.abs(asteroid)){
                            stack.pop();
                            break;
                        } else if(Math.abs(stack.peek())>Math.abs(asteroid)) {
                            break;
                        } else{
                            stack.pop();
                        }
                    }

                }
            } while(true);

        }

        int[] result = new int[stack.size()];

        for(int i= result.length-1; i>=0;i--){
            result[i]= stack.pop();
        }
        return result;
    }

    /*
  Given an encoded string, return its decoded string.
  The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
  Note that k is guaranteed to be a positive integer.
  You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
  Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
  For example, there will not be input like 3a or 2[4].
  The test cases are generated so that the length of the output will never exceed 105.

  add to stack all characters until ] is found
  keep characters until [ is found
  keep numbers until nan is found
  result equal characters*number
  return characters to stack in case more [] exist
  result is all characters in stack

   System.out.println(decodeString("2[abc]3[cd]ef"));
   abcabccdcdcdef
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

    //END STACK


    //START QUEUE


    /*
    You have a RecentCounter class which counts the number of recent requests within a certain time frame.
    Implement the RecentCounter class:
    RecentCounter() Initializes the counter with zero recent requests.
    int ping(int t) Adds a new request at time t, where t represents some time in milliseconds,
    and returns the number of requests that has happened in the past 3000 milliseconds (including the new request).
    Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
    It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.

    QuickTest q = new QuickTest();
       int[] array = new int[]{1178,1483,1563,4054,4152};

       for(int a: array){
           System.out.println(q.ping(a));
       }

       add new element
       while peek of the queue is less than t-3000 poll it
       then return the size of the queue

     */
    Queue<Integer> q = new LinkedList<>();

    public int ping(int t) {
        q.add(t);

        while(q.peek() < t-3000){
            q.poll();
        }
        return q.size();
    }


    /*
    Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
    Announce the victory: If this senator found the senators who still have rights to vote are all from the same party,
    he can announce the victory and decide on the change in the game.
    Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party.
    Then if there are n senators, the size of the given string will be n.
    The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting.
    All the senators who have lost their rights will be skipped during the procedure.
    Suppose every senator is smart enough and will play the best strategy for his own party.
    Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".

    One strategy that would pass the judge (although not the most efficient) to think about the problem is for the first character in the string to move to the back and eliminate the first "opposing" character in the string. So in the case of "DDRRR":

    DDRRR - the first D moves to the back and takes out the first R
    DRRD - the first D moves to the back and takes out the first R
    RDD - the first R moves to the back and takes out the first D
    DR - the first (and only) D moves to the back and takes out the first (and only) R
    D - D wins the vote.

    System.out.println(predictPartyVictory("DDRRR"));
     */
    public static String predictPartyVictory(String senate) {
        Queue<Character> queue = new LinkedList<>();

        Character before = null;
        for(char c: senate.toCharArray()){
            queue.add(c);
        }

        Character contrary = null;
        do {
            if(before==null){
                before=queue.peek();
            } else {
                queue.remove(contrary);
                queue.add(queue.poll());
                before=queue.peek();
            }
            if(before == 'R'){
                contrary = 'D';
            } else {
                contrary = 'R';
            }

        }while(queue.contains(contrary));

        if(before != null && before=='R'){
            return "Radiant";
        }
        return "Dire";

    }


    //END QUEUE

    //START LINKED LIST

    /*
 You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes
 the largest integer less than or equal to x.
 For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively

 keep track of the before and next elements
 when index to remove is found, before.next = before.next.next if this is not null


 ListNode a = new ListNode(1);
 ListNode b = new ListNode(2);
 ListNode c = new ListNode(3);
 ListNode d = new ListNode(4);
 a.next = b;
 b.next = c;
 c.next = d;

     ListNode result = deleteMiddle(a);
  */
    public static QuickTest.ListNode deleteMiddle(QuickTest.ListNode head) {
        int count=0;
        QuickTest.ListNode test = head;
        while(test != null){
            count++;
            test = test.next;
        }

        if(count<2){
            return null;
        }

        int toRemoveIndex= count/2;

        test = head;

        int counter = 0;
        QuickTest.ListNode before = null;
        while(test != null){
            if(counter == toRemoveIndex){
                if(before.next.next != null){
                    before.next = before.next.next;
                } else {
                    before.next = null;
                }
                before = test;
                test = test.next;
            } else{
                before = test;
                test = test.next;
            }
            counter++;
        }

        return head;
    }

    /*
  Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
  The first node is considered odd, and the second node is even, and so on.
  Note that the relative order inside both the even and odd groups should remain as it was in the input.

  fill a queue of even elements, fill a queue of odd elements
  fill a list with all even then all odd

  ListNode a = new ListNode(1);
  ListNode b = new ListNode(2);
  ListNode c = new ListNode(3);
  ListNode d = new ListNode(4);
  ListNode e = new ListNode(5);
  a.next = b;
  b.next = c;
  c.next = d;
  d.next = e;

  ListNode result = oddEvenList(a);
   */
    public static QuickTest.ListNode oddEvenList(QuickTest.ListNode head) {

        if(head == null || head.next ==null){
            return head;
        }

        Queue<QuickTest.ListNode> odd = new LinkedList<>();
        Queue<QuickTest.ListNode> even = new LinkedList<>();
        int count =1;
        QuickTest.ListNode temp = head;
        while(temp != null){
            QuickTest.ListNode toAdd = new QuickTest.ListNode(temp.val);
            if(count%2!=0){
                odd.add(toAdd);
            } else{
                even.add(toAdd);
            }
            temp = temp.next;
            count++;
        }

        if(!odd.isEmpty()){
            temp = odd.poll();
        } else {
            temp = even.poll();
        }

        QuickTest.ListNode result = temp;
        while(!odd.isEmpty()){
            temp.next = odd.poll();
            temp= temp.next;
        }

        while(!even.isEmpty()){
            temp.next = even.poll();
            temp= temp.next;
        }

        return result;
    }

    /*
Given the head of a singly linked list, reverse the list, and return the reversed list.

ListNode a = new ListNode(1);
ListNode b = new ListNode(2);
ListNode c = new ListNode(3);
ListNode d = new ListNode(4);
ListNode e = new ListNode(5);
a.next = b;
b.next = c;
c.next = d;
d.next = e;

ListNode result = reverseList(a);

curr.next for a moment points to prev so when prev=curr, prev points as next what prev was before
 */
    public static QuickTest.ListNode reverseList(QuickTest.ListNode head) {
        QuickTest.ListNode curr=head;
        QuickTest.ListNode prev=null;
        QuickTest.ListNode next=null;
        while(curr!=null){
            next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    /*
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.
Given the head of a linked list with even length, return the maximum twin sum of the linked list.

static class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

ListNode a = new ListNode(1);
    ListNode b = new ListNode(100000);
    a.next = b;
    System.out.println(pairSum(a));

!!!get from array faster than list.get

    first and last sum i+ length-1-i, it would increment i to right and end -i to go left
 */
    public static int pairSum(QuickTest.ListNode head) {
        Integer maxVal =0;
        List<Integer> list = new LinkedList<>();

        while(head != null){
            list.add(head.val);
            head = head.next;
        }

        Integer[] array = list.stream().toArray(Integer[]::new);
        for(int i=0; i<list.size()/2; i++){
            Integer sum = array[i]+array[array.length-1-i];
            if(maxVal<sum){
                maxVal = sum;
            }
        }

        return maxVal;
    }

    //END LINKED LIST

    //START BINARY TREE DFS

    /*
        Given the root of a binary tree, return its maximum depth.
        A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

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

        TreeNode nine = new TreeNode(9);
            TreeNode twenty = new TreeNode(20, new TreeNode(15), new TreeNode(7));
           TreeNode root = new TreeNode(3, nine, twenty);
            System.out.println(maxDepth(root));
     */
    public static int maxDepth(QuickTest.TreeNode root) {

        if(root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /*
Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
Two binary trees are considered leaf-similar if their leaf value sequence is the same.

get all leafs from both trees and then compare

    TreeNode six = new TreeNode(6);
    TreeNode seven = new TreeNode(7);
    TreeNode four = new TreeNode(4);
    TreeNode nine = new TreeNode(9);
    TreeNode eight = new TreeNode(8);

    TreeNode two = new TreeNode(2, seven, four);
    TreeNode five = new TreeNode(5,six, two);
    TreeNode one = new TreeNode(1,nine,eight);

    TreeNode three = new TreeNode(3, five, one);

    System.out.println(leafSimilar(three, three));

 */
    public static boolean leafSimilar(QuickTest.TreeNode root1, QuickTest.TreeNode root2) {

        List<Integer> list = new LinkedList<>();

        fill(root1, list);

        List<Integer> list2 = new LinkedList<>();
        fill(root2, list2);

        if(list.size() != list2.size()){
            return false;
        }

        for(int i=0; i< list.size(); i++){
            int a = list.get(i);
            int b = list2.get(i);
            if( a!= b){
                return false;
            }
        }

        return true;
    }

    public static void fill(QuickTest.TreeNode tree, List<Integer> list){
        if(tree.left == null && tree.right == null){
            list.add(tree.val);
            return;
        }

        if(tree.left != null){
            fill(tree.left, list);
        }
        if(tree.right != null){
            fill(tree.right, list);
        }
    }

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

    public static int goodNodes(QuickTest.TreeNode root) {

        Integer maxValue = Integer.MIN_VALUE;
        fillMaxVal(root, maxValue);


        return counter;
    }

    public static void fillMaxVal(QuickTest.TreeNode tree, Integer maxValue){
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

    /*
        Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
        The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
     */
    public static int pathSum(QuickTest.TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return get(root, targetSum, map, 0L);
    }

    private static int get(QuickTest.TreeNode root, int targetSum, Map<Long, Integer> map, long prefixSum) {
        if (root == null) {
            return 0;
        }

        prefixSum += root.val;
        int count = map.getOrDefault(prefixSum - targetSum, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        count += get(root.left, targetSum, map, prefixSum);
        count += get(root.right, targetSum, map, prefixSum);
        map.put(prefixSum, map.get(prefixSum) - 1);

        return count;
    }


    /*
        You are given the root of a binary tree.
        A ZigZag path for a binary tree is defined as follow:
        Choose any node in the binary tree and a direction (right or left).
        If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
        Change the direction from right to left or from left to right.
        Repeat the second and third steps until you can't move in the tree.
        Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

        Return the longest ZigZag path contained in that tree.
     */
    static int maxZigZag = 0;
    public static int longestZigZag(QuickTest.TreeNode root) {
        if (root == null) return 0;
        traverse(root.left, true, 1);
        traverse(root.right, false, 1);
        return maxZigZag;
    }

    private static void traverse(QuickTest.TreeNode node, boolean isLeft, int length) {
        if (node == null) return;
        maxZigZag = Math.max(maxZigZag, length);
        if (isLeft) {
            traverse(node.right, false, length+1);
            traverse(node.left, true, 1);
        } else {
            traverse(node.left, true, length+1);
            traverse(node.right, false, 1);
        }
    }

    /*
        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
        two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     */
    public QuickTest.TreeNode lowestCommonAncestor(QuickTest.TreeNode root, QuickTest.TreeNode p, QuickTest.TreeNode q) {
        if (root == null) return root;
        QuickTest.TreeNode left = lowestCommonAncestor(root.left, p, q);
        QuickTest.TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (root == p || root == q) return root;
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    //END BINARY TREE DFS


    //START BINARY TREE BFS

    /*
        Given the root of a binary tree, imagine yourself standing on the right side of it,
        return the values of the nodes you can see ordered from top to bottom.

        only add when current depth equals list, this will only be true for right side since that is called first
     */
    public static List<Integer> rightSideView(QuickTest.TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public static void rightView(QuickTest.TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);

    }

    /*
        Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
        Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

        Input: root = [1,7,0,7,-8,null,null]
        Output: 2
        Explanation:
        Level 1 sum = 1.
        Level 2 sum = 7 + 0 = 7.
        Level 3 sum = 7 + -8 = -1.
        So we return the level with the maximum sum which is level 2.


     */
    public static int maxLevelSum(QuickTest.TreeNode root) {
        if(root == null)
            return 0;

        Queue<QuickTest.TreeNode> queue = new LinkedList<>();
        int minLevel = 0;
        int maxSum = Integer.MIN_VALUE;
        queue.add(root);
        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            while(size > 0) {
                QuickTest.TreeNode node = queue.poll();
                sum += node.val;

                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);

                size--;
            }

            if(maxSum < sum) {
                maxSum = sum;
                minLevel = level;
            }

            level++;
        }

        return minLevel;
    }
    //END BINARY TREE BFS

    //START BINARY SEARCH TREE

    /*
        You are given the root of a binary search tree (BST) and an integer val.
        Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
        If such a node does not exist, return null.

        traverse through the node while it is not null
        if value smaller go node left else go node right

        ex:
        Input: root = [4,2,7,1,3], val = 2
        Output: [2,1,3]
     */
    public static QuickTest.TreeNode searchBST(QuickTest.TreeNode root, int val) {
        if(root==null)return null;
        if(root.val==val)return root;
        if(val<root.val) return searchBST(root.left,val);
        else
            return searchBST(root.right,val);
    }

    /*
        Given a root node reference of a BST and a key, delete the node with the given key in the BST.
        Return the root node reference (possibly updated) of the BST.
        Basically, the deletion can be divided into two stages:
        Search for a node to remove.
        If the node is found, delete the node.

        if delete with children, new reference would be first right child if not left child

        ex:
        Input: root = [5,3,6,2,4,null,7], key = 3
        Output: [5,4,6,2,null,null,7]
        Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
        One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
        Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted
     */
    public static QuickTest.TreeNode deleteNode(QuickTest.TreeNode root, int key) {
        if(root==null){
            return root;
        }
        if(root.val<key){
            root.right= deleteNode(root.right,key);
        }else if(root.val>key){
            root.left= deleteNode(root.left,key);
        }else{
            if(root.right==null && root.left==null){
                return null;
            }
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }

            QuickTest.TreeNode IS= fis(root.right);
            root.val= IS.val;
            root.right= deleteNode(root.right, IS.val);
        }
        return root;
    }

    public static QuickTest.TreeNode fis(QuickTest.TreeNode root){
        while(root.left!=null){
            root= root.left;
        }
        return root;
    }

    //END BINARY SEARCH TREE

    //START GRAPHS DFS

    /*
  There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
  Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
  When you visit a room, you may find a set of distinct keys in it.
  Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
  Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
  return true if you can visit all the rooms, or false otherwise.

  set keeps track of accessed rooms, if it is equal to number of rooms all have been visited
  keys available keys, room 0 is always accesible

  ex:
  Input: rooms = [[1,3],[3,0,1],[2],[0]]
  Output: false
  Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.

  List<Integer> keys = new LinkedList<>();
    List<List<Integer>> list = new LinkedList<>();
    keys.add(1);
    keys.add(3);
    list.add(keys);
    keys = new LinkedList<>();
    keys.add(3);
    keys.add(0);
    keys.add(1);
    list.add(keys);
    keys = new LinkedList<>();
    keys.add(2);
    list.add(keys);
    keys = new LinkedList<>();
    keys.add(0);
    list.add(keys);
    System.out.println(canVisitAllRooms(list));
 */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {

        Stack<Integer> keys = new Stack<>();
        Set<Integer> set = new HashSet<>();

        set.add(0);
        for(int ele : rooms.get(0)){
            keys.push(ele);
        }

        while(!keys.isEmpty()){
            int key = keys.pop();
            if(!set.contains(key)){
                set.add(key);

                for(int ele : rooms.get(key)){
                    if(!set.contains(ele)){
                        keys.push(ele);
                    }
                }
            }

        }
        if(set.size() == rooms.size()) return true;

        return false;
    }

    // END GRAPHS DFS


    //START HEAP PRIORITY QUEUE

        /*
        Given an integer array nums and an integer k, return the kth largest element in the array.
        Note that it is the kth largest element in the sorted order, not the kth distinct element.
        Can you solve it without sorting?

        EX:
            Input: nums = [3,2,1,5,6,4], k = 2
            Output: 5

        add elements to Priority queue reverse order to have largest at peek, poll number of k times

        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4},2));
     */

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        if(nums.length==0){
            return 0;
        }

        for(int i=0; i< nums.length; i++){
            queue.add(nums[i]);
        }

        for(int i=1; i<k; i++){
            queue.poll();
        }


        return queue.poll();
    }


    /*
        You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
        Implement the SmallestInfiniteSet class:
        SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
        int popSmallest() Removes and returns the smallest integer contained in the infinite set.
        void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.

        ex:
            Input
            ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
     */
    static HashSet<Integer>set = new HashSet<>();
    static Queue<Integer>queue = new PriorityQueue<>();

    {
        for(Integer i=1; i<=1000;i++){
            set.add(i);
            queue.add(i);
        }
    }

    public static int popSmallest() {
        Integer val = queue.poll();
        set.remove(val);
        return val;
    }

    public static void addBack(int num) {
        if(!set.contains(num)){
            set.add(num);
            queue.add(num);
        }
    }


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


    /*
    We hire 3 workers in total. The total cost is initially 0.
    - In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
    - In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
    - In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
    The total hiring cost is 11.
    candidates is the size of the set starting or ending
    starting set of 4 candidates for from example = first 4 elements [17,12,10,2]
    ending set of 4 candidates for our example = last 4 elements [2,11,20,8]
    middle element 7 is ignored in first k run

    System.out.println(totalCost(new int[]{17,12,10,2,7,2,11,20,8}, 3, 4));

    faster than being getting elements from list fill queue with number of elements needed
    and be removing the smallest from them
 */
    public static long totalCost(int[] costs, int k, int candidates) {
        if(costs.length == 0){
            return 0;
        }
        int count =0;
        long sum=0;
        PriorityQueue<Integer> startQueue = new PriorityQueue<>();
        PriorityQueue<Integer> endQueue = new PriorityQueue<>();

        int i=0;
        int j=costs.length-1;

        while(count<k ){

            while(startQueue.size()<candidates && i <= j){
                startQueue.add(costs[i]);
                i++;
            }

            while(endQueue.size()<candidates && i <= j){
                endQueue.add(costs[j]);
                j--;
            }

            int toSum;

            int q1 = startQueue.peek() != null ? startQueue.peek():Integer.MAX_VALUE;
            int q2 = endQueue.peek() != null ? endQueue.peek():Integer.MAX_VALUE;

            sum += q1<=q2?startQueue.poll():endQueue.poll();
            count++;
        }

        return sum;
    }


    //END HEAP PRIORITY QUEUE

    //START HACKER RANK

    //rank leader
    public static void ranks(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < n; i++){
            int score = in.nextInt();
            if (scores.size() == 0 || scores.get(scores.size() - 1) != score)
                scores.add(score);
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++){
            int score = in.nextInt();
            int min = 0;
            int max = scores.size();
            while (max > min){
                int mid = (min + max) / 2;
                if (scores.get(mid) <= score)
                    max = mid;
                else
                    min = mid + 1;
            }
            System.out.println(min + 1);
        }
    }

    /*
        An arcade game player wants to climb to the top of the leaderboard and track their ranking. The game uses Dense Ranking, so its leaderboard works like this:
        The player with the highest score is ranked number 1 on the leaderboard.
        Players who have equal scores receive the same ranking number, and the next player(s) receive the immediately following ranking number.
        Example
        ranked = 100,90,90,80
        player = 70,80,105

        The ranked players will have ranks 1, 2, 2, and 3, respectively. If the player's scores are 70,80 and 105, their rankings after each game are 4, 3 and 1.
        Return 4,3,1.
     */
    //Collections.binarySearch needs the collection to be sorted in natural order
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        List<Integer> positionResult = new LinkedList<>();
        ranked = ranked.stream().distinct().collect(Collectors.toList());
        for (int score : player) {
            int min = 0;
            int max = ranked.size();
            while (min < max) {
                int mid = (min + max) / 2;
                if (ranked.get(mid) <= score) {
                    max = mid;
                } else {
                    min = mid+1;
                }
            }
            positionResult.add(min + 1);
        }
        return positionResult;
    }

    //Given the pointer to the head node of a linked list, change the next pointers of the nodes so that their order is reversed.
    // The head pointer given may be null meaning that the initial list is empty.
    // Example 1->2->3->NULL
    // references the list
    // 3->2->1->NULL
    //Manipulate the  pointers of each node in place and return , now referencing the head of the list .
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        // Write your code here
        Stack<SinglyLinkedListNode> stack = new Stack<>();
        while(llist != null){
            stack.push(llist);
            llist = llist.next;
        }

        SinglyLinkedListNode result = null;
        SinglyLinkedListNode temp = null;
        while(!stack.isEmpty()){
            if(result == null && temp ==null){
                result = stack.peek();
                temp = stack.pop();
            } else {
                temp.next = stack.pop();
                temp = temp.next;
            }
        }
        if(temp != null && temp.next != null){
            temp.next = null;
        }
        return result;
    }

    //Big decimal, Scale, Rounding
    public static void plusMinus(List<Integer> arr){
        BigDecimal positive= new BigDecimal(0);
        BigDecimal negative=new BigDecimal(0);
        BigDecimal zero=new BigDecimal(0);
        BigDecimal plusOne = new BigDecimal(1);
        BigDecimal divider = new BigDecimal(arr.size());

        for(Integer i: arr){
            if(i>0){
                positive = positive.add(plusOne);
            } else if(i==0){
                zero = zero.add(plusOne);
            } else{
                negative = negative.add(plusOne);
            }
        }

        System.out.println(positive.divide(divider,6,RoundingMode.HALF_UP));
        System.out.println(negative.divide(divider,6,RoundingMode.HALF_UP));
        System.out.println(zero.divide(divider,6,RoundingMode.HALF_UP));
    }

    //There is a collection of input strings and a collection of query strings. For each query string, determine how many times it occurs in the
    // list of input strings. Return an array of the results.
    //Example
    //There are  instances of ',  of '' and  of ''. For each query, add an element to the return array, .
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        // Write your code here
        Map<String, Long> map = strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> result = new LinkedList<>();
        for(String q:queries){
            long value = map.getOrDefault(q,0L);
            result.add((int)value);
        }
        return result;
    }

    //Given five positive integers, find the minimum and maximum values that can be calculated by
    // summing exactly four of the five integers. Then print the respective minimum and maximum
    // values as a single line of two space-separated long integers.
    public static void miniMaxSum(List<Integer> arr) {
        // Write your code here
        arr = arr.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        BigInteger min= new BigInteger("0");
        BigInteger max= new BigInteger("0");

        int toSkip = 1;

        for(int i=0; i<arr.size();i++){
            if(i>=toSkip){
                max = max.add(new BigInteger(String.valueOf(arr.get(i))));
                //max= max+Double.valueOf(arr.get(i));
            }
            if(i+toSkip<arr.size()){
                //min = min +Double.valueOf(arr.get(i));
                min = min.add(new BigInteger(String.valueOf(arr.get(i))));
            }
        }
        System.out.print(min + " " + max);

    }

    //Given a time in -hour AM/PM format, convert it to military (24-hour) time.
    //Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
    //- 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
    public static void timeConversion(String s) throws ParseException {
        //new
        String time = LocalTime.parse(s, DateTimeFormatter.ofPattern("hh:mm:ssa", Locale.US))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time);

        //legacy
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ssa", Locale.US);
        java.util.Date date = parseFormat.parse(s);
        System.out.println(date);

        SimpleDateFormat diplayParse = new SimpleDateFormat("HH:mm:ss");
        String format = diplayParse.format(date);
        System.out.println(format);

    }

    //find medium element
    public static int findMedian(List<Integer> arr) {
        // Write your code here
        if((arr.size()%2)==0){
            System.out.println("List should have odd elements");
            return -1;
        }

        arr= arr.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        return arr.get(arr.size()/2);
    }

    //Given an array of integers, where all elements but one occur twice, find the unique element.
    public static int lonelyinteger(List<Integer> a) {
        System.out.println("Lonely");
        // Write your code here
        Map<Integer, Long> map = a.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        for(Map.Entry<Integer, Long> entry : map.entrySet()){
            if(entry.getValue().intValue() == 1){
                return entry.getKey();
            }
        }
        return -1;
    }

    public static int lonelyinteger2(List<Integer> a) {
        System.out.println("Lonely Visited2");
        // Write your code here
        Integer visited=-1;
        a= a.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        for(Integer i : a){
            if(visited == -1){
                visited = i;
            } else{
                if(visited!=i){
                    return visited;
                }
                visited = -1;
            }
        }
        return -1;
    }

    //Given a square matrix, calculate the absolute difference between the sums of its diagonals.

    public static int diagonalDifference(List<List<Integer>> list){
        System.out.println("Diagonal Difference");
        int leftDiagonal=0;
        int rightDiagonal=0;

        int size = list.size();
        for(int x=0; x<size;x++){
            for(int y=0; y<size;y++){
                if(x==y){
                    List<Integer> xList = list.get(x);
                    leftDiagonal += xList.get(y);
                }
                if(x+y+1 == size){
                    List<Integer> xList = list.get(x);
                    rightDiagonal += xList.get(y);
                }
            }
        }

        return Math.abs(leftDiagonal-rightDiagonal);
    }

    //Comparison Sorting
    //Quicksort usually has a running time of n x log(n), but is there an algorithm that can sort even faster? In general,
    // this is not possible. Most sorting algorithms are comparison sorts, i.e. they sort a list just by comparing t
    // he elements to one another. A comparison sort algorithm cannot beat n x log(n) (worst-case) running time, since
    // represents the minimum number of comparisons needed to know where to place each element. For more details,
    // you can see these notes (PDF).
    //Alternative Sorting
    //Another sorting method, the counting sort, does not require comparison. Instead, you create an integer
    // array whose index range covers the entire range of values in your array to sort. Each time a value occurs
    // in the original array, you increment the counter at that index. At the end, run through your counting array,
    // printing the value of each non-zero valued index that number of times.
    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        int[] frequencyArray = new int[100];

        for(Integer value:arr){
            if(frequencyArray[value]==0){
                frequencyArray[value]=1;
            } else {
                frequencyArray[value]= frequencyArray[value] +1;
            }
        }

        return IntStream.of(frequencyArray).boxed().collect(Collectors.toList());
    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        System.out.println("Flipping matrix");

        int size= matrix.size();
        int maxSum=0;
        for(int x=0; x<size/2;x++){
            for(int y=0; y<size/2;y++){
                maxSum+= Math.max(matrix.get(x).get(y),
                        Math.max(matrix.get(x).get(size-1-y),
                                Math.max(matrix.get(size-1-x).get(y), matrix.get(size-1-x).get(size-1-y)))
                );
            }
        }
        return maxSum;
    }

    //here is a large pile of socks that must be paired by color. Given an array of integers representing the color of each sock,
// determine how many pairs of socks with matching colors there are.
//Example
//N=7
//ARR= [1,2,1,2,1,3,2]
//There is one pair of color  and one of color . There are three odd socks left, one of each color. The number of pairs is .
    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        int count=0;
        Map<Integer, Long> map = ar.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        for(Map.Entry<Integer, Long> entry : map.entrySet()){
            int val = (entry.getValue()).intValue()/2;
            count+=val;
        }
        return count;
    }


    //player 1 starts
    //player 2 can match if even towera player 2 will win
    //player 1 will win if odd
    //height =1 player 1 will not have available move
    public static int towerBreakers(int n, int m) {
        // Write your code here
        if(m==1 || n%2==0){
            return 2;
        }
        return 1;
    }

    private static List<Character> vocabularyUpperList;
    private static List<Character> vocabularyList;

    static{
        String vocabulary = "abcdefghijklmnopqrstuvwxyz";
        vocabularyList = vocabulary.chars().mapToObj(c->new Character((char)c)).collect(Collectors.toList());
        vocabularyUpperList = vocabulary.toUpperCase().chars().mapToObj(c->new Character((char)c)).collect(Collectors.toList());
    }

    public static List<Character> getVocabularyList(boolean isUppercase){

        if(isUppercase){
            return vocabularyUpperList;
        }
        return vocabularyList;
    }

    //ulius Caesar protected his confidential information by encrypting it using a cipher.
    // Caesar's cipher shifts each letter by a number of letters. If the shift takes you past
    // the end of the alphabet, just rotate back to the front of the alphabet. In the case of a
    // rotation by 3, w, x, y and z would map to z, a, b and c.

    //tener cuidado con el index0 y el size del abecedario
    public static String caesarCipher(String s, int k) {
        // Write your code here

        StringBuilder sb = new StringBuilder();

        int size = getVocabularyList(true).size();
        List<Character> vocabularyList=null;

        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            int index=-1;
            if(Character.isLetter(c)){
                if(Character.isUpperCase(c)){
                    vocabularyList = getVocabularyList(true);
                } else {
                    vocabularyList = getVocabularyList(false);
                }

                index= vocabularyList.indexOf(c);

                index = index+k;

                if(index>vocabularyList.size()-1){
                    index = index%vocabularyList.size();
                }
            }

            if(index>=0){
                sb.append(vocabularyList.get(index));
            } else{
                sb.append(c);
            }

        }
        return sb.toString();
    }


    //Declare a 2-dimensional array, , of  empty arrays. All arrays are zero indexed.
    //Declare an integer, , and initialize it to .
    //There are  types of queries, given as an array of strings for you to parse:
    //Query: 1 x y
    //Let .
    //Append the integer  to .
    //Query: 2 x y
    //Let .
    //Assign the value  to .
    //Store the new value of last answer to an answers array.
    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        // Write your code here
        ArrayList<ArrayList<Integer>> seqs=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp= new ArrayList<Integer>();
        int lastAns=0;

        for(int i=0;i<n;i++)
        {
            seqs.add(new ArrayList<Integer>());
        }

        for(List<Integer> arr: queries)
        {
            int seq=(arr.get(1)^lastAns)%n;

            int size=seqs.get(seq).size();

            switch(arr.get(0))
            {
                case 1: seqs.get(seq).add(arr.get(2));
                    break;

                case 2: lastAns= seqs.get(seq).get(arr.get(2)%size);
                    temp.add(lastAns);
            }
        }
        return temp;
    }

    //remove letter to make a palindrome
    //return index number of the removed character
    //return -1 if already a palindrome or no palindrome available
    //eficiencia, comparar todos uno por 1 en lugar mejora comparando inicio y fin en su lugar

    public static int palindromeIndex(String s) {
        // Write your code here
        if(s.length()>100005 || s==null || s.equals(new StringBuilder(s).reverse().toString()))
            return -1;

        int size = s.length();

        for(int i=0;i<size/2;i++){
            if(s.charAt(i) != s.charAt(size-1-i)){
                StringBuilder sb = new StringBuilder(s);
                sb.replace(i,i+1,"");
                if(sb.toString().equals(sb.reverse().toString())){
                    return i;
                } else{
                    sb = new StringBuilder(s);
                    sb.replace(size-1-i,size-1-i+1,"");
                    if(sb.toString().equals(sb.reverse().toString())){
                        return size-1-i;
                    }
                }
            }
        }

        return -1;
    }

    public static String pangrams(String s) {
        // Write your code here
        String abc = "abcdefghijklmnopqrstuvwxyz";

        s= Arrays.asList(s.toLowerCase().split(""))
                .stream().distinct().collect(Collectors.joining()).replace(" ", "");

        if(s.length()<abc.length()){
            return "not pangram";
        }

        Map<Character, Long> map = abc.chars().mapToObj(c-> Character.valueOf((char)c)).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        for(char c: s.toCharArray()){
            map.remove(c);
        }

        if(map.size()>0){
            return "not pangram";
        } else {
            return "pangram";
        }
    }

    //Given a square grid of characters in the range ascii[a-z], rearrange elements of each row alphabetically,
    // ascending. Determine if the columns are also in ascending alphabetical order, top to bottom. Return YES
    // if they are or NO if they are not
    //bidemencional declarar primer[] controla tamaño segundo horizontal
    //bidimencional insertar [0][2] llena hacia derecha [2][0] llena hacia abajo
    //[][] primero controla y segundo controla x
    public static String gridChallenge(List<String> grid) {
        System.out.println("Grid Challenge");
        // Write your code here
        if(grid.size()>=1 && grid.size()<=100){
            int max = grid.stream().max(Comparator.comparing(String::length)).get().length();
            char[][] matrix = new char[grid.size()][max];
            int x=0;
            int y=0;
            for(String s: grid){
                char[] c = s.toCharArray();
                Arrays.sort(c);
                for(char cc:c){
                    matrix[x][y] = cc;
                    y++;
                }
                x++;
                y=0;
            }

            for(int i=0; i<max;i++){
                for(int j=1; j<grid.size();j++){
                    char temp='a';
                    if(matrix[j-1][i]>matrix[j][i]){
                        return "NO";
                    }
                }
            }
            return "YES";
        }

        return "NO";
    }

    //You will be given a list of integers, , and a single integer . You must create an array of length  from elements of  such
    // that its unfairness is minimized. Call that array . Unfairness of an array is calculated as
    //Where:
    //- max denotes the largest integer in
    //- min denotes the smallest integer in
    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        int n = arr.size();
        int startingIndex=0;
        int res=-1;
        while(startingIndex+k-1 < n){
            int min = arr.get(startingIndex);
            int max = arr.get(startingIndex+k -1);
            if(res == -1){
                res = max-min;
            } else{
                if(max-min < res){
                    res = max - min;
                }
            }
            startingIndex++;
        }
        return res;
    }


    //We define super digit of an integer  using the following rules:
    //Given an integer, we need to find the super digit of the integer.
    //If  has only  digit, then its super digit is .
    //Otherwise, the super digit of  is equal to the super digit of the sum of the digits of .
    // si se trata de append numeros y sumas 1 por uno lo mismo, equivale a mejor el numero resultante de
    //la primer copia por el numero de copias, no agregar los caracteres!
    public static int superDigit(String n, int k) {
        // Write your code here
        long l=n.length()*k;
        int c=0;
        while(l>1l)
        {
            long so=0l;
            for(int x=0;x<n.length();x++)
            {
                so=so+Long.parseLong(""+n.charAt(x));
            }
            if(c==0)
            {
                so=so*k;
                c++;
            }
            n=Long.toString(so);
            l=n.length();
        }
        return Integer.parseInt(n);
    }

    //It is New Year's Day and people are in line for the Wonderland rollercoaster ride. Each person wears a sticker indicating their initial position
    // in the queue from  to . Any person can bribe the person directly in front of them to swap positions, but they still wear their original sticker.
    // One person can bribe at most two others.
    //Determine the minimum number of bribes that took place to get to a given queue order. Print the number of bribes, or, if anyone has bribed more
    // than two people, print Too chaotic.
    //Example
    //
    //If person  bribes person , the queue will look like this: . Only  bribe is required. Print 1.
    //
    //Person  had to bribe  people to get to the current position. Print Too chaotic.
//2,5,1,3,4
    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        if(q.size()>=1 && q.size()<=100000){
            BigInteger bigInteger = new BigInteger(String.valueOf("0"));
            for(int i=0;i<q.size();i++){
                int index = q.indexOf(q.get(i));
                List<Integer> set = q.stream().filter(val->q.indexOf(val)>index).collect(Collectors.toList());
                long count = set.stream().filter(val -> q.get(index)>val).count();
                if(count>2l){
                    System.out.println("Too chaotic");
                    return;
                } else {
                    bigInteger =bigInteger.add(new BigInteger(String.valueOf(count)));
                }
            }
            System.out.println(bigInteger.longValue());
        }
    }

    //2,1,5,3,4
    public static void minimumBribes2(List<Integer> q) {
        // Write your code here
        if(q.size()>=1 && q.size()<=100000){
            BigInteger bigInteger = new BigInteger(String.valueOf("0"));

            for(int i=0;i<q.size();i++){
                int index = q.indexOf(q.get(i));
                int value = q.get(index);
                List<Integer> set = q.stream().filter(val->q.indexOf(val)>=index).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                long count = Collections.binarySearch(set,value);


                if(count>2l){
                    System.out.println("Too chaotic");
                    return;
                } else {
                    bigInteger =bigInteger.add(new BigInteger(String.valueOf(count)));
                }
            }
            System.out.println(bigInteger.longValue());
        }
    }

    //2,5,1,3,4
    //2,1,5,3,4
    //si esta a un lugar intercambia puestos
    //recorre 1 posicion los otros 2
    //y pon el q esta a 2 a su lugar
    public static void minimumBribes3(List<Integer> q) {
        // Write your code here


        if(q.size()>=1 && q.size()<=100000){
            int[] array = new int[q.size()];
            for(int i=0;i<q.size();i++){
                array[i] = q.get(i);
            }
            int bride =0;
            for(int i=array.length-1; i>=0;i--){
                if(i+1 !=array[i]){
                    if(i-1>=0 && i+1==array[i-1]){
                        bride++;
                        int temp= array[i-1];
                        array[i-1] = array[i];
                        array[i] = temp;
                    } else if(i-2>=0 && i+1==array[i-2]){
                        bride=bride+2;
                        array[i-2] = array[i-1];
                        array[i-1] = array[i];
                        array[i] = array[i-2];
                    } else{
                        System.out.println("Too chaotic");
                        return;
                    }
                }
            }
            System.out.println(bride);
        }
    }

    //truck tour
    //
    //position % size will always return position from 0 to size
    // 1%3=1;  2%3=2; 3%3=0 positions from 0 to size-1!
    public static int truckTour(List<List<Integer>> petrolpumps) {
        // Write your code here
        final int size = petrolpumps.size();

        for (int start = 0; start < size; start++) {
            if (petrolpumps.get(start).get(0) < petrolpumps.get(start).get(1)) continue;

            long tank = 0;
            int position = start;
            int pumpCount = 0;

            while (true) {
                if (pumpCount == size) return start; // all pumps has been visited
                if (tank < 0) break;                 // not enough petrol to move further

                tank += petrolpumps.get(position % size).get(0) - petrolpumps.get(position % size).get(1);

                position++;
                pumpCount++;
            }
        }
        return -1;


    }

    //Merge two sorted linked lists
    //merge 2 nodes, move pointer to be the next depending on value jump from node 1 to node 2
    //merge two sorted linked list
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        SinglyLinkedListNode result= null;

        if(head1==null){
            return head2;
        } else if(head2==null){
            return head1;
        }

        if(head1.data<=head2.data){
            result = head1;
            result.next = mergeLists(head1.next,head2);
        } else{
            result = head2;
            result.next = mergeLists(head1, head2.next);
        }

        return result;
    }


//    //Two friends like to pool their money and go to the ice cream parlor. They always choose two distinct flavors and they spend all of their money.
//    //Given a list of prices for the flavors of ice cream, select the two that will cost all of the money they have.
//    //Example.
//    //m=6  cost[1,3,4,5,6]
//    //The two flavors that cost 1 and 5  meet the criteria. Using 1-based indexing, they are at indices 1 and 4.
//    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
//        // Write your code here
//        List<Integer>result = new LinkedList<>();
//        List<Integer> ordered = new ArrayList<>(arr);
//        Collections.sort(ordered);
//        for(int i=0; i<ordered.size();i++){
//            int value = ordered.get(i);
//            int index = Collections.binarySearch(ordered, m-value);
//            if(index>=0){
//                int index1 = (arr.indexOf(ordered.get(i)))+1;
//                int index2 = (arr.indexOf(ordered.get(index)))+1;
//                if(index1 == index2){
//                    index2++;
//                }
//                result.add(Math.min(index1,index2));
//                result.add(Math.max(index1,index2));
//                break;
//            }
//        }
//        return result;
//    }


    //There are two -element arrays of integers,  and . Permute them into some  and  such that the relation  holds for all  where .
    //There will be  queries consisting of , , and . For each query, return YES if some permutation , satisfying the relation exists. Otherwise, return NO.
    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        // Write your code here
        Collections.sort(A);
        Collections.sort(B);
        int i=0;
        int j=A.size()-1;
        while(i<A.size() && j>0){
            if(A.get(i)+B.get(j)<k){
                return "NO";
            }
            i++;
            j--;
        }
        return "YES";
    }


    //Two children, Lily and Ron, want to share a chocolate bar. Each of the squares has an integer on it.
    //Lily decides to share a contiguous segment of the bar selected such that:
    //The length of the segment matches Ron's birth month, and,
    //The sum of the integers on the squares is equal to his birth day.
    //Determine how many ways she can divide the chocolate.
    public static int birthday(List<Integer> s, int d, int m) {
        // Write your code here
        int index=0;
        int res=0;

        while(index+m<=s.size()){
            int acum=0;
            for(int i=0; i<m;i++){
                acum+=s.get(index+i);
                if(acum>d){
                    break;
                }
            }
            if(acum==d){
                res++;
            }
            index++;
        }
        return res;
    }

    //el scanner hay q tener cuidado y usar el nextint
    //STDIN es usar el scanner para lo q se usa
    public static void queueUsingTwoStacks(){
        Scanner sc = new Scanner(System.in);

        Deque<Integer> input = new ArrayDeque<>();
        Deque<Integer> output = new ArrayDeque<>();

        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                int x = sc.nextInt();
                input.add(x);
            } else if (type == 2) {
                if(!input.isEmpty()){
                    output.add(input.pop());
                }
            } else if (type == 3) {
                if(!input.isEmpty()){
                    System.out.println(input.peekFirst());
                }
            }
        }

        sc.close();
    }

    //A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
    //Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or {) occurs to the left of a closing bracket (i.e., ), ], or }) of the exact same type. There are three types of matched pairs of brackets: [], {}, and ().
    //A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For example, {[(])} is not balanced because the contents in between { and } are not balanced. The pair of square brackets encloses a single, unbalanced opening bracket, (, and the pair of parentheses encloses a single, unbalanced closing square bracket, ].
    //By this logic, we say a sequence of brackets is balanced if the following conditions are met:
    //It contains no unmatched brackets.
    //The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.
    //Given  strings of brackets, determine whether each sequence of brackets is balanced. If a string is balanced, return YES. Otherwise, return NO.
    //para balance el stack asi va a mantener obteniendo {[(  agrega y remueve en orden ([{ asi confirmamos el ordenamiento

    //System.out.println(isBalanced("{{[[(())]]}}"));
    public static String isBalanced(String s) {

        char[] array = s.toCharArray();
        Stack<Character> stack = new Stack<>();


        for(int i=0; i<array.length; i++){
            char c = array[i];

            switch(c){
                case '(':
                    stack.push(c);
                    break;
                case '[':
                    stack.push(c);
                    break;
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if(stack.isEmpty() || stack.pop() != '('){
                        return "NO";
                    }
                    break;
                case ']':
                    if(stack.isEmpty() || stack.pop() != '['){
                        return "NO";
                    }
                    break;
                case '}':
                    if(stack.isEmpty() || stack.pop() != '{'){
                        return "NO";
                    }
                    break;
            }
        }

        if(stack.size()==0){
            return "YES";
        }

        return "NO";
    }


    //si se busca una diferencia, se puede buscar si el numero - la diferencia buscada = numero q se necesita existe en la lista
    //con una busqueda si se encuentra se consigue
    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        arr= arr.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        int count=0;

        for(Integer i:arr){
            if(Collections.binarySearch(arr,i-k)>=0){
                count++;
            }
        }
        return count;
    }

    //Watson gives Sherlock an array of integers. His challenge is to find an element of the array such that the sum of all elements to the
    // left is equal to the sum of all elements to the right.
    //Example
    //[5,6,8,11]
    // 8 is between two subarrays that sum to 11.
    public static String balancedSums(List<Integer> arr) {
        // Write your code here
        int sum = 0;
        sum = arr.stream().reduce(0,(i1,i2)->i1+i2).intValue();

        //curr is cimulated sum in the left
        int curr = 0;
        for(int j=0; j<arr.size(); j++){
            //left part equals right part
            if(curr == sum - arr.get(j)-curr){
                return "YES";
            }
            curr += arr.get(j);
        }
        return "NO";
    }

    //stack helps to keep track of last operations
    //cuidado con los index al hacer substring y charat
    public static void simpleTextEditor(){
        Scanner scan = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        int operations = scan.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<operations;i++){
            int type = scan.nextInt();

            switch (type){
                case 1:
                    stack.push(sb.toString());
                    sb.append(scan.next());
                    break;
                case 2:
                    stack.push(sb.toString());
                    int toRemove = scan.nextInt();
                    toRemove = sb.length()-toRemove;
                    sb = new StringBuilder(sb.substring(0,toRemove));
                    break;
                case 3:
                    int toPrint= scan.nextInt()-1;
                    if(sb.length()>toPrint){
                        System.out.println(sb.toString().charAt(toPrint));
                    }
                    break;
                case 4:
                    if(!stack.isEmpty()){
                        sb= new StringBuilder(stack.pop());
                    }
                    break;
            }
        }
    }

    /*
         You are a waiter at a party. There is a pile of numbered plates.
         Create an empty answers array. At each iteration, i, remove each plate from the top of the stack in order.
         Determine if the number on the plate is evenly divisible by the ith prime number.
         If it is, stack it in pile Bi. Otherwise, stack it in stack Ai.
         Store the values in Bi from top to bottom in answers.
         In the next iteration, do the same with the values in stack Ai.
         Once the required number of iterations is complete, store the remaining values in Ai in answers,
         again from top to bottom. Return the answers array.
      */
    public static List<Integer> waiter(List<Integer> number, int q) {
        // Write your code here

        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        List<Integer> answers = new LinkedList<>();

        int index=0;
        for(Integer i : number){
            stackA.push(i);
        }

        Integer primeNumber = 2;
        while(index < q){

            Stack<Integer> nextA= new Stack<>();
            while(!stackA.isEmpty()){
                if(stackA.peek() % primeNumber == 0){
                    stackB.push(stackA.pop());
                } else {
                    nextA.push(stackA.pop());
                }

            }

            while(!stackB.isEmpty()){
                answers.add(stackB.pop());
            }
            stackA = nextA;
            primeNumber = nextPrime(primeNumber);
            index++;
        }



        while(!stackA.isEmpty()){
            answers.add(stackA.pop());
        }

        return answers;
    }

    static int nextPrime(int begin) {
        for (int i = begin + 1;; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }

    static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static int legoBlocks(int n, int m) {
        // Write your code here
        if (n < 2 || m < 1) return 0;
        if (m == 1) return 1;

        // - Step 1: consider only one row
        long [] total = new long [m + 1];

        // set a flag (-1) to calculate only once
        for (int i = 0; i < total.length; i++)
            total[i] = -1;

        fillTot(total, m);

        // - Step 2: extend to all rows
        for (int i = 0; i < total.length; i++) {
            long tmp = 1;
            for (int j = 0; j < n; j++) {
                tmp = (tmp * total[i]) % MOD;
            }
            total[i] = tmp;
        }

        // - Step 3: subtract the vertically unstable
        // don't calculate the vertically unstable at first
        long [] result = new long [m + 1];
        // set a flag (-1) to calculate only once
        for (int i = 0; i < result.length; i++)
            result[i] = -1;

        getResult(total, result, m);

        // solution 1:
        // - subtract the vertically unstable
        // return (int) ((total[m] - result[m]) % MOD);

        // solution 2:
        // - return the result
        return (int) (result[m] % MOD);
    }

    static long MOD = 1000000000 + 7;

    // calculate unstable by splitting it into two parts and
    // multiplying unstable part with total part
    static long getResult(long [] total, long [] result, int i) {
        if (result[i] == -1) {
            if (i == 1) {
                // solution 1
                // result[i] = 0;

                // solution 2
                result[i] = 1;
            }
            else {
                // solution 1
                // result[i] = 0;
                // for (int j = 1; j < i; j++) {
                //     result[i] += ((total[j] - getResult(total, result, j)) * total[i - j]) % MOD;
                // }

                // solution 2
                result[i] = total[i];
                for (int j = 1; j < i; j++) {
                    result[i] -= (getResult(total, result, j) * total[i - j]) % MOD;
                }
            }
        }

        return result[i];
    }

    // fill totals partially
    static long fillTot(long [] total, int i) {
        if (i < 0) return 0;

        if (total[i] == -1) {
            if (i == 0 || i == 1)
                total[i] = 1;
            else
                total[i] = (fillTot(total, i - 1) + fillTot(total, i - 2) + fillTot(total, i - 3) + fillTot(total, i - 4)) % MOD;
        }

        return total[i];
    }

    public static List<Integer> searchFirstBreath(int n, int m, List<List<Integer>> edges, int s){
        if (n < 1 || edges == null || edges.size() < 1 || s < 1) return null;

        // Step 1
        Set<Integer> [] tree = new Set[n];
        for (List<Integer> edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            if (tree[a-1] == null) {
                tree[a-1] = new HashSet<Integer>();
            }
            tree[a-1].add(b);
            if (tree[b-1] == null) {
                tree[b-1] = new HashSet<Integer>();
            }
            tree[b-1].add(a);
        }

        // Step 2
        List<Integer> res = new ArrayList<Integer>();

        // O(n) start from S
        for (int i = 0; i < n - 1; i++) {
            res.add(-1);
        }

        Set<Integer> root = tree[s-1];
        if (root == null) return res;

        Deque<Integer> queue = new LinkedList<Integer>();
        for (Integer r : root) {
            queue.add(r);
        }
        int height = 1;
        boolean [] visited = new boolean [n];
        visited[s-1] = true;
        while (!queue.isEmpty()) {
            Deque<Integer> queuen = new LinkedList<Integer>();
            while (!queue.isEmpty()) {
                int v = queue.poll().intValue();
                if (!visited[v-1]) {
                    visited[v-1] = true;
                    // update distances
                    if (v > s)
                        res.set(v-2, height*6);
                    else
                        res.set(v-1, height*6);
                    if (tree[v-1] != null) {
                        for (Integer r : tree[v-1]) {
                            queuen.add(r);
                        }
                    }
                }
            }
            queue = queuen;
            height++;
        }

        // return res
        return res;
    }

    public static void preOrder(Node root) {

        Stack<Node> stack = new Stack<>();
        while(root != null){
            System.out.print(root.data);
            if(root.right != null){
                stack.push(root.right);
            }
            if(root.left!=null){
                root= root.left;
            } else if(stack.size()>0){
                root = stack.pop();
            } else {
                root=null;
            }
            if(root != null){
                System.out.print(" ");
            }
        }
    }


    public static void decode(String s, Node2 root){
        Node2 current = root;
        char[] array = s.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0; i<array.length;i++){
            int type = Character.getNumericValue(array[i]);
            if (type == 0) {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                result.append(current.data);
                current = root;
            }
        }
        System.out.println(result.toString());
    }


    public static void noPrefix(List<String> words) {
        // Write your code here

        boolean good = true;
        Trie trie = new Trie();
        for (String word : words) {
            good = trie.insert(word);
            if (!good) {
                System.out.println("BAD SET");
                System.out.println(word);
                break;
            }
        }

        if (good) {
            System.out.println("GOOD SET");
        }
    }

    public static long flippingBits(long n) {
        // Write your code here
        int[] array = new int[32];
        int index =0;
        long result =0;
        while(n>0){
            int lastDigit= (int)n%2;
            n=n/2;
            array[index] =lastDigit;
            index++;
        }

        for(int i=0;i<32;i++){
            System.out.print(array[i] +" ");
        }

        for(int i=0;i<32;i++){
            array[i] = 1-array[i];
            if(array[i]==1){
                result = result +(1L<<i);
            }
        }

        return result;
    }

    public static String decimalToBinary(long number){
        String binary = "";
        long rem =0;
        while(number>0){
            rem = number % 2;
            binary = rem + binary;
            number = number / 2;
        }
        return binary;
    }

    //A teacher asks the class to open their books to a page number. A student can either start turning pages from the front of the book
    // or from the back of the book. They always turn pages one at a time. When they open the book, page  is always on the right side:
    //When they flip page , they see pages  and . Each page except the last page will always be printed on both sides.
    // The last page may only be printed on the front, given the length of the book. If the book is pages long, and a student wants to turn to page ,
    // what is the minimum number of pages to turn? They can start at the beginning or the end of the book.
    public static int pageCount(int n, int p) {
        // Write your code here

        return Math.min(p/2, (n/2)-(p/2));
    }
    public static List<String> processLogs(List<String> logs, int threshold) {
        // Write your code here
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        Set<String> set = new TreeSet<>();
        for(int i=0; i<logs.size(); i++){
            String log = logs.get(i);
            String[] array = log.split(" ");
            if(array.length==3){
                String index1 = array[0];
                String index2 = array[1];
                if(index1.equals(index2) && !set.contains(index1)){
                    if(map.containsKey(index1)){
                        int newValue = map.get(index1)+1;
                        if(newValue>=threshold){
                            set.add(index1);
                        }
                        map.put(index1, newValue);
                    } else {
                        map.put(index1,1);
                    }
                } else {
                    if(map.containsKey(index1)){
                        if(!set.contains(index1)){
                            int newValue = map.get(index1)+1;
                            if(newValue>=threshold){
                                set.add(index1);
                            }
                            map.put(index1, newValue);
                        }
                    } else {
                        map.put(index1,1);
                    }
                    if(map.containsKey(index2)){
                        if(!set.contains(index2)){
                            int newValue = map.get(index2)+1;
                            if(newValue>=threshold){
                                set.add(index2);
                            }
                            map.put(index2, newValue);
                        }
                    } else {
                        map.put(index2,1);
                    }
                }
            }
        }

        return new LinkedList<>(set);
    }

//            for(Map.Entry<String, Integer> entry: map.entrySet()){
//                if(entry.getValue()>=threshold){
//                    set.add(entry.getKey());
//                }
//            }


    //item in containers

    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {

        List<Integer> result = new LinkedList<>();

        // Write your code here
        for(int i=0; i< startIndices.size();i++){
            int articles = 0;
            Integer start = startIndices.get(i)-1;
            Integer end = endIndices.get(i);
            if(start<=end && start>=0){
                String word = s.substring(start, end);
                Stack<Character> stack = new Stack();
                char[] chars = word.toCharArray();
                for(char c: chars){
                    stack.push(c);
                }

                boolean openCompartment = false;

                while(!stack.isEmpty() && stack.peek() != null){
                    int tempArticles =0;
                    Character eval = stack.pop();
                    if(eval == '|'){
                        if(openCompartment){
                            articles+=tempArticles;
                            openCompartment = false;
                        } else{
                            openCompartment = true;
                        }
                    }
                    if(eval == '*'){
                        if(openCompartment){
                            tempArticles++;
                        }
                    }
                }
            }

            result.add(articles);
        }
        return result;
    }

    public static String gamingArray(List<Integer> arr) {
        // Write your code here
        int count = 0;
        int bigger = 0;

        for(Integer i : arr){
            if(i > bigger){
                bigger = i;
                count++;
            }
        }

        if (count % 2 == 0) {
            return "ANDY";
        }

        return "BOB";
    }

    public static int[][] spiral(int rows, int columns) {

        int counter = 0;
        boolean columnsRightDirection = true;
        boolean rowsDownDirection = true;
        int rowIndex =0;
        int columnIndex =0;
        int invertRowCounter =0;
        int invertColumnCounter=0;
        boolean fillingColumns = true;

        int[][] matrix = new int[rows][columns];

        while(counter< rows*columns){
            counter++;
            matrix[rowIndex][columnIndex] = counter;

            if(fillingColumns){
                if(columnsRightDirection && (columnIndex+invertColumnCounter+1==columns)){
                    columnsRightDirection = false;
                    fillingColumns = false;

                    if(rowsDownDirection){
                        rowIndex++;
                    } else {
                        rowIndex--;
                    }
                } else if(columnsRightDirection){
                    columnIndex++;
                } else if(!columnsRightDirection && columnIndex-invertColumnCounter==0){
                    columnsRightDirection=true;
                    fillingColumns = false;
                    invertColumnCounter++;
                    if(rowsDownDirection){
                        rowIndex++;
                    } else {
                        rowIndex--;
                    }
                } else if(!columnsRightDirection){
                    columnIndex--;
                }
            } else {
                if(rowsDownDirection && (rowIndex+invertRowCounter+1==rows)){
                    rowsDownDirection = false;
                    fillingColumns = true;
                    invertRowCounter++;
                    if(columnsRightDirection){
                        columnIndex++;
                    } else {
                        columnIndex--;
                    }
                } else if(rowsDownDirection){
                    rowIndex++;
                } else if(!rowsDownDirection && rowIndex-invertRowCounter==0){
                    rowsDownDirection=true;
                    fillingColumns = true;

                    if(columnsRightDirection){
                        columnIndex++;
                    } else {
                        columnIndex--;
                    }
                } else if(!rowsDownDirection){
                    rowIndex--;
                }
            }

        }
        return matrix;
    }

    public static int superDigit2(String n, int k) {
        // Write your code here
        int result=Integer.parseInt(n);

        while(n.length()>1){
            result=0;
            for(int i=0; i<n.length();i++){
                result+= Integer.valueOf(n.charAt(i));
            }
            n=String.valueOf(result);
        }
        result = Integer.valueOf(n) * k;
        n= String.valueOf(result);

        while(n.length()>1){
            result=0;
            for(int i=0; i<n.length();i++){
                result+= Integer.valueOf(n.charAt(i));
            }
            n=String.valueOf(result);
        }
        return result;
    }

    public static int pylons(int k, List<Integer> arr) {
        int next = -1;
        int count = 0;
        int prev = -1;
        while(next<arr.size()-1){
            int on = Math.min(next+k,arr.size()-1);
            while(on>prev){
                if(arr.get(on)==1){
                    break;
                }
                on--;
            }
            if(on==prev){
                return -1;
            }
            count++;
            prev = on;
            next = on + k-1;

        }
        return count;
    }

    //Sherlock and the Valid String

    /*
      Sherlock considers a string to be valid if all characters of the string appear the same number of times.
      It is also valid if he can remove just 1 character at 1 index in the string,
      and the remaining characters will occur the same number of times. Given a string , determine if it is valid.
      If so, return YES, otherwise return NO.

      Example: abc
      This is a valid string because frequencies are 1,1,1.

      abcc
      This is a valid string because we can remove one c and have 1 of each character in the remaining string.

      abccc
      This string is not valid as we can only remove 1 occurrence of c. That leaves character frequencies of 1,1,2

      only valid when all frequencies are same, when all same but 1 with frequency 1,
      when all same but 1 which 1 higher in frequency ex aabbccc = 2,2,3. 3 is 1 higher than the frequency 2 of the rest

      System.out.println(isValid("aabbccc"));
   */
    public static String isValid(String s) {
        // Write your code here
        if(s==null || s.length()<=2){
            return "YES";
        }

        Character[] chars = new Character[s.length()];

        for(int i=0; i< chars.length;i++){
            chars[i] = s.charAt(i);
        }

        Map<Character, Long> map = Arrays.stream(chars).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(map);
        Map<Long, Long> times = map.values().stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(times);

        if(times.size()==1){
            return "YES";
        } else if(times.size()==2){
            if(times.get(1L) != null && times.get(1L)==1L){
                times.remove(1L);
                if(times.size()==1){
                    return "YES";
                }
            } else {
                Long max= times.keySet().stream().max(Comparator.naturalOrder()).get();
                Long min= times.keySet().stream().min(Comparator.naturalOrder()).get();
                if(max-1L == min && times.get(max)==1L){
                    return "YES";
                }
            }

        }

        return "NO";
    }

    //END Sherlock and the Valid String

    /*
        A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
        Given a pointer to the head of a linked list, determine if it contains a cycle. If it does, return 1. Otherwise, return 0.

        Example

        head refers to the list of nodes 1-2-3-NULL
        The numbers shown are the node numbers, not their data values. There is no cycle in this list so return 0.

        head refers to the list of nodes 1-2-3-1-null
        There is a cycle where node 3 points back to node 1, so return 1.
     */
    static boolean hasCycle(SinglyLinkedListNode head) {

        if(head == null) return false;

        //fast starts ahead, if eventually they are equal then there is a cycle
        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head.next;

        while(fast != null && fast.next != null){
            if(slow == fast){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    /*
    Two friends like to pool their money and go to the ice cream parlor.
    They always choose two distinct flavors and they spend all of their money.
    Given a list of prices for the flavors of ice cream, select the two that will cost all of the money they have.
    Example. m=4, cost = 1,4,5,3,2
    The two flavors that cost 1 and 3 meet the criteria. Using 1-based indexing, they are at indices 1 and 4.

    System.out.println(icecreamParlor(4, List.of(1,4,5,3,2)));
 */
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap();
        int[] array = arr.stream().mapToInt(i->i).toArray();
        List<Integer> result = new LinkedList<>();

        for(int i=0; i<array.length;i++){
            if(map.containsKey(m-array[i])){
                result.add(Math.min(i+1, map.get(m-array[i])));
                result.add(Math.max(i+1, map.get(m-array[i])));
                return result;
            } else {
                map.put(array[i],i+1);
            }
        }

        return Collections.EMPTY_LIST;
    }

    /*
    Queue using 2 stacks
    A queue is an abstract data type that maintains the order in which elements were added to it,
    allowing the oldest elements to be removed from the front and new elements to be added to the rear.
    This is called a First-In-First-Out (FIFO) data structure because the first element added to the queue
    (i.e., the one that has been waiting the longest) is always the first one to be removed.

    when moving from one stack to other, the order will be switched so moving to a second stack will have
    order to be as in a queue would be

    Stacks stacks = new Stacks();
    Scanner in = new Scanner(System.in);
    int q = in.nextInt();
    for(int i=0; i<q;i++){
      int op = in.nextInt();
      if(op==1){
        int val = in.nextInt();
        stacks.enqueue(val);
      } else if(op==2){
        stacks.deque();
      } else if(op==3){
        System.out.println(stacks.peek());
      }
    }
 */
    public static class Stacks{

        Stack<Integer> newestOnTop = new Stack<>();
        Stack<Integer> oldestOnTop = new Stack<>();

        public void enqueue(Integer val){
            newestOnTop.push(val);
        }

        public Integer peek(){
            if(oldestOnTop.isEmpty()){
                shiftStacks();
            }
            if(!oldestOnTop.isEmpty()){
                return oldestOnTop.peek();
            }
            return null;
        }

        public Integer deque(){
            if(oldestOnTop.isEmpty()){
                shiftStacks();
            }
            if(!oldestOnTop.isEmpty()){
                return oldestOnTop.pop();
            }
            return null;
        }

        private void shiftStacks() {
            while(!newestOnTop.isEmpty()){
                oldestOnTop.push(newestOnTop.pop());
            }
        }
    }

    /*
    Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
    Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
    Example s=mom
    The list of all anagrammatic pairs is [m,m], [mo,om] at positions [0,2], [[0,1],[1,2]] respectively.

    add all available substrings to a map
    order them to confirm if substring is conformed by same chars
    only substring, so not same length than original string

    System.out.println(sherlockAndAnagrams("mom"));
 */
    public static int sherlockAndAnagrams(String s) {
        // Write your code here

        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<s.length();i++){
            for(int j=i; j<s.length();j++){
                //j+1 because if not substring to 0,0 ,etc will have nothing in it
                char[] array = s.substring(i,j+1).toCharArray();
                Arrays.sort(array);
                String ss= new String(array);
                if(map.containsKey(ss)){
                    map.put(ss, map.get(ss)+1);
                } else {
                    map.put(ss,1);
                }
            }
        }

        int count = 0;
        for(Map.Entry<String, Integer> entry:map.entrySet()){
            int val = entry.getValue();
            count+= (val * (val-1)) /2;
        }

        return count;
    }

    /*
    Reduce a string of lowercase characters in range ascii[‘a’..’z’]by doing a series of operations.
    In each operation, select a pair of adjacent letters that match, and delete them.
    Delete as many characters as possible using this method and return the resulting string.
    If the final string is empty, return Empty String

    Example. aab
      aab shortens to b in one operation: remove the adjacent a characters.
    Example abba
      Remove the two 'b' characters leaving 'aa'. Remove the two 'a' characters to leave ''. Return 'Empty String'.

      System.out.println(superReducedString("abba"));
 */
    public static String superReducedString(String s) {
        // Write your code here

        StringBuilder sb = new StringBuilder(s);

        for(int i=1; i<sb.length();i++){
            if(sb.charAt(i)==sb.charAt(i-1)){
                //delete start inclusive, end exclusive
                sb.delete(i-1,i+1);
                //when this instruction ends, will go to start of for making i to go 1
                i=0;
            }
        }

        String result = sb.toString();

        if(result.length()==0) return "Empty String";

        return result;
    }

    /*
  a+b >c   -> valid non degenerate triangle

  //important to validate against the valid triangles only sorting received list first

  Given an array of stick lengths, use 3 of them to construct a non-degenerate triangle with the maximum possible perimeter.
  Return an array of the lengths of its sides as 3 integers in non-decreasing order.
  If there are several valid triangles having the maximum perimeter:

  Choose the one with the longest maximum side.
  If more than one has that maximum, choose from them the one with the longest minimum side.
  If more than one has that maximum as well, print any one them.
  If no non-degenerate triangle exists, return -1.

  Example
  sticks: 1,2,3,4,5,10
  The triplet 1,2,3 will not form a triangle. Neither will 4,5,10 or 2,3,5, so the problem is reduced to 2,3,4 and 3,4,5 . The longer perimeter is 3+4+5=12.

  System.out.println(maximumPerimeterTriangle(List.of(1,2,3,4,5,10)));
*/
    public static List<Integer> maximumPerimeterTriangle(List<Integer> sticks) {
        // Write your code here
        //important to validate against the valid triangles only
        Collections.sort(sticks);
        int sum=0;
        Queue<Integer> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        List<Integer> resultList = new ArrayList<>();

        for(int i=0; i< sticks.size(); i++){
            int val = sticks.get(i);
            if(queue.size()<3){
                queue.add(val);
                sum+=val;
            }

            if(queue.size()==3) {
                int nextInQueue = val;
                if(sum-nextInQueue>nextInQueue && sum>=max){
                    if(sum>max){
                        max = sum;
                        resultList.clear();
                        getListFromQueue(queue, resultList);
                        Collections.sort(resultList, Comparator.naturalOrder());
                    } else {
                        List<Integer> tempList = new ArrayList<>();
                        getListFromQueue(queue, tempList);
                        Collections.sort(tempList, Comparator.naturalOrder());
                        if(resultList.get(2)<tempList.get(2) || resultList.get(0)<tempList.get(0)){
                            resultList.clear();
                            resultList.addAll(tempList);
                        }
                    }
                }
                sum -= queue.poll();

            }
        }

        if(resultList.isEmpty()){
            resultList.add(-1);
        }

        return resultList;
    }

    private static void getListFromQueue(Queue<Integer> queue, List<Integer> resultList) {
        Queue<Integer> tempQueue = new LinkedList<>(queue);
        while (!tempQueue.isEmpty()){
            resultList.add(tempQueue.poll());
        }
    }

    /*
        In this challenge, the task is to debug the existing code to successfully execute all provided test files.
        Given an array of n distinct integers, transform the array into a zig zag sequence by permuting the array elements.
        A sequence will be called a zig zag sequence if the first k elements in the sequence are in increasing order
        and the last k elements are in decreasing order, where k=n+1/2.
        You need to find the lexicographically smallest zig zag sequence of the given array.

        Example.

        Now if we permute the array as , the result is a zig zag sequence.

        firt half except mid with sort works
        mid one switch with last
        then switch places between mid +1 and end -1 till they encounter

     */
    public static void findZigZagSequence(int [] a, int n){
        //sort gives us first part
        Arrays.sort(a);
        // -1 since is array based
        int mid = ((n + 1)/2)-1;
        //switch mid with last element
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

        //switch elements after mid and before last element
        int st = mid + 1;
        int ed = n - 2;
        while(st <= ed){
            temp = a[st];
            a[st] = a[ed];
            a[ed] = temp;
            st = st + 1;
            ed = ed - 1;
        }
        for(int i = 0; i < n; i++){
            if(i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }

    /*
        There will be two arrays of integers. Determine all integer satisfy the following 2 conditions:
        1. The elements of the first array are all factor of the integer being considered (considered int% each element must result 0)
            ex: 6%2 and 6%6    first number evaluated number second is a number in set a
        2. The integer being considered is a factor of all elements of second array (each element in set 2 % considered int must result 0)
            ex: 24%6 and 36%6  first number element in set b, second evaluated number

            a: 2,6
            b: 24,36

            considered number must be multiply of biggest number of a, if not for example the 2 -> 2%6 -> different than 0
            System.out.println(getTotalX(List.of(3,4),List.of(24,48)));
     */
    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // Write your code here
        //get multiply
        Integer maxA = a.stream().max(Comparator.naturalOrder()).get();
        //get max value to consider
        Integer maxB = b.stream().max(Comparator.naturalOrder()).get();
        int multiply = maxA;
        int counter =0;
        while(multiply<=maxB){
            boolean valid = false;

            //factor of set a
            for(int i=0; i<a.size();i++){
                if(multiply%a.get(i) != 0){
                    break;
                }
                if(i==a.size()-1){
                    valid=true;
                }
            }

            if(valid) {
                for (int i = 0; i < b.size(); i++) {
                    Long longVal = b.get(i).longValue();
                    if (Long.valueOf(longVal % multiply) != 0L) {
                        break;
                    }
                    //factor of all in b
                    if (i == b.size() - 1) {
                        counter++;
                    }
                }
            }
            //multiples of multiply only
            multiply = multiply+maxA;
        }
        return counter;

    }


    /*
        The absolute difference is the positive difference between two values a and b, is written a-b or b-a and they are equal.
        If a=3 and b=2, |3-2|=|2-3| =1 . Given an array of integers, find the minimum absolute difference between any two elements in the array.

        Example. [-2,2,4]

        There are 3 pairs of numbers: [-2,2],[-2,4] and [2,4] .
        The absolute differences for these pairs are |-2-2| = 4, |-2-4| = 6 and |2-4|=2. The minimum absolute difference is 2.

        important to sort, if you sort you will have them in the closest range so if compared to the one next
        will always be smaller than with the one next to it

        System.out.println(minimumAbsoluteDifference(List.of(-2,2,4)));
     */
    public static int minimumAbsoluteDifference(List<Integer> arr) {
        // Write your code here
        int min = Integer.MAX_VALUE;
        int[] array = arr.stream().mapToInt(i->i).toArray();
        //sort so will have closest values next to it
        Arrays.sort(array);

        //no need to extra for to compare to others since they will be bigger
        //than the one next to it
        for(int i=0; i<arr.size()-1; i++){
            int difference = Math.abs(array[i]-array[i+1]);
            if(difference<min){
                min=difference;
            }
        }

        return min;
    }


    /*
        smart number if square of val * square of val = original val

        A number is called a smart number if it has an odd number of factors.
        Given some numbers, find whether they are smart numbers or not.
        Debug the given function is_smart_number to correctly check if a given number is a smart number.

        4 sqr = 2
        2*2= original val 4
     */
    public static boolean isSmartNumber(int num) {
        int val = (int) Math.sqrt(num);
        if(val * val == num)
            return true;
        return false;
    }

    /*
    NW SOME not sure why

    first half replace with - instead of letters
    sort them base on first element or index

    ex:
    List<String> l1 = List.of("0","ab");
    List<String> l2 = List.of("6","cd");
    List<String> l3 = List.of("0","ef");
    List<String> l4 = List.of("6","gh");
    List<String> l5 = List.of("4","ij");
    List<String> l6 = List.of("0","ab");
    List<String> l7 = List.of("6","cd");
    List<String> l8 = List.of("0","ef");
    List<String> l9 = List.of("6","gh");
    List<String> l10 = List.of("0","ij");
    List<String> l11 = List.of("4","that");
    List<String> l12 = List.of("3","be");
    List<String> l13 = List.of("0","to");
    List<String> l14 = List.of("1","be");
    List<String> l15 = List.of("5","question");
    List<String> l16 = List.of("1","or");
    List<String> l17 = List.of("2","not");
    List<String> l18 = List.of("4","is");
    List<String> l19 = List.of("2","to");
    List<String> l20 = List.of("4","the");




    List<String> ll1 = new ArrayList<>(l1);
    List<String> ll2 = new ArrayList<>(l2);
    List<String> ll3 = new ArrayList<>(l3);
    List<String> ll4 = new ArrayList<>(l4);
    List<String> ll5 = new ArrayList<>(l5);
    List<String> ll6 = new ArrayList<>(l6);
    List<String> ll7 = new ArrayList<>(l7);
    List<String> ll8 = new ArrayList<>(l8);
    List<String> ll9 = new ArrayList<>(l9);
    List<String> ll10 = new ArrayList<>(l10);
    List<String> ll11 = new ArrayList<>(l11);
    List<String> ll12 = new ArrayList<>(l12);
    List<String> ll13 = new ArrayList<>(l13);
    List<String> ll14 = new ArrayList<>(l14);
    List<String> ll15 = new ArrayList<>(l15);
    List<String> ll16 = new ArrayList<>(l16);
    List<String> ll17 = new ArrayList<>(l17);
    List<String> ll18 = new ArrayList<>(l18);
    List<String> ll19 = new ArrayList<>(l19);
    List<String> ll20 = new ArrayList<>(l20);

    List<List<String>> ll = new ArrayList<>();
    ll.add(ll1);
    ll.add(ll2);
    ll.add(ll3);
    ll.add(ll4);
    ll.add(ll5);
    ll.add(ll6);
    ll.add(ll7);
    ll.add(ll8);
    ll.add(ll9);
    ll.add(ll10);
    ll.add(ll11);
    ll.add(ll12);
    ll.add(ll13);
    ll.add(ll14);
    ll.add(ll15);
    ll.add(ll16);
    ll.add(ll17);
    ll.add(ll18);
    ll.add(ll19);
    ll.add(ll20);

    countSort(ll);

 */
    public static void countSort(List<List<String>> arr) {

        List<List<String>> result = new ArrayList<>();

        for(int i=0; i<arr.size(); i++){
            List<String> l = new ArrayList<>(arr.get(i));
            result.add(l);
        }

        // Write your code here
        for(int i=0; i<result.size()/2;i++){
            List<String> l = result.get(i);
            l.remove(1);
            l.add("-");
        }

        class ListComparator<T extends Comparable<T>> implements Comparator<List<T>>{

            @Override
            public int compare(List<T> o1, List<T> o2) {

                return o1.get(0).compareTo(o2.get(0));
            }
        }

        Collections.sort(result, new ListComparator());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<result.size();i++){
            result.get(i).remove(0);
            sb.append(result.get(i).get(0));
            if(i< result.size()-1){
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    /*
        Two people are playing game of Misère Nim. The basic rules for this game are as follows:

        The game starts with n piles of stones indexed from 0 to n-1.
        Each pile i (where 0<=i<n) has si stones.
        The players move in alternating turns. During each move, the current player must remove one or more stones from a single pile.
        The player who removes the last stone loses the game.
        Given the value of n and the number of stones in each pile,
        determine whether the person who wins the game is the first or second person to move.
        If the first player to move wins, print First on a new line; otherwise, print Second.
        Assume both players move optimally.

        System.out.println(misereNim(List.of(1,2,2)));
     */
    public static String misereNim(List<Integer> s) {
        // Write your code here
        int n = s.size();
        // if there is only 1 pile AND it has more than 1 element => First player wins
        if (n == 1) {
            return s.get(0) > 1 ? "First" : "Second";
        }
        int total = s.get(0);
        int xor = s.get(0);

        for (int i = 1; i < n; i++) {
            total += s.get(i);
            xor ^= s.get(i);
        }
        /*
         * If sum of all stones equals the total piles, all piles have a single (1)
         * stone. For even number of piles, first player will always win.
         */
        if (total == n) {
            return total % 2 == 0 ? "First" : "Second";
        }
        /*
         * For all other cases, the xor value determines winner. If xor value = 0, then
         * second player will always win as all piles (stones) can be paired.
         */
        return xor > 0 ? "First" : "Second";

    }

    /*
    We define a magic square to be an nxn matrix of distinct positive integers from 1 to n square where the sum of any row, column,
    or diagonal of length n is always equal to the same number: the magic constant.
    You will be given a 3x3 matrix s of integers in the inclusive range 1,9.
    We can convert any digit a to any other digit b in the range 1,9 at cost of a-b.
    Given s, convert it into a magic square at minimal cost. Print this cost on a new line.
    Note: The resulting magic square must contain distinct integers in the inclusive range 1,9.

    there are 8 matrix which can be magic square, we create a list with them squares
    s.get(j/3).get(j%3) to get positions:
        00 01 01
        10 11 12
        20 21 22
        from original matrix and rest it to the correct value from 1 of the magic matrix

        List<List<Integer>> s = new ArrayList<>();
        s.add(Arrays.asList(5,3,4));
        s.add(Arrays.asList(1,5,8));
        s.add(Arrays.asList(6,4,2));

        System.out.println(formingMagicSquare(s));
     */
    public static int formingMagicSquare(List<List<Integer>> s) {
        // Write your code here
        List<List<Integer>> squares = new ArrayList<>();
        squares.add(Arrays.asList(8,3,4,1,5,9,6,7,2));
        squares.add(Arrays.asList(4,3,8,9,5,1,2,7,6));
        squares.add(Arrays.asList(8,1,6,3,5,7,4,9,2));
        squares.add(Arrays.asList(6,1,8,7,5,3,2,9,4));
        squares.add(Arrays.asList(2,9,4,7,5,3,6,1,8));
        squares.add(Arrays.asList(4,9,2,3,5,7,8,1,6));
        squares.add(Arrays.asList(2,7,6,9,5,1,4,3,8));
        squares.add(Arrays.asList(6,7,2,1,5,9,8,3,4));

        int min = Integer.MAX_VALUE;

        for(int i=0; i<squares.size();i++){
            int total = 0;
            for(int j=0; j<squares.get(i).size(); j++){
                //si fueran iguales las matrices, el total seria 0, no podria haber menos
                //cada posicion es tomada y restada con su par y solo cuando se necesito restar va a sumar a total
                total += Math.abs(s.get(j/3).get(j%3) - squares.get(i).get(j));
            }
            if(total < min) min=total;
        }
        return min;
    }

    /*
        Louise and Richard have developed a numbers game.
        They pick a number and check to see if it is a power of 2. If it is, they divide it by 2.
        If not, they reduce it by the next lower number which is a power of 2.
        Whoever reduces the number to 1 wins the game. Louise always starts.
        Given an initial value, determine who wins the game.

        Example n=132

        It's Louise's turn first. She determines that 132 is not a power of 2.
        The next lower power of 2 is 128, so she subtracts that from 132 and passes 4 to Richard.
        4 is a power of 2, so Richard divides it by 2 and passes 2 to Louise.
        Likewise, 2 is a power so she divides it by 2 and reaches 1. She wins the game.

Update If they initially set counter to , Richard wins. Louise cannot make a move so she loses.
     */
    public static String counterGame(long n) {
        // Write your code here
        if(n == 1){
            return "Richard";
        }

        boolean chanceLouise = true;
        while(n>1){
            if(Long.bitCount(n) >1){
                double i=1;
                int count =0;
                while(i<n){
                    i=Math.pow(2,count);
                    if(i>n){
                        break;
                    }
                    count++;
                }
                count --;
                double nextNum = (long) Math.pow(2,count);
                n = (long) (n-nextNum);
            } else{
                n=n/2;
            }

            if(n==1)
                break;

            chanceLouise = !chanceLouise;
        }

        if(chanceLouise){
            return "Louise";
        } else {
            return "Richard";
        }
    }


    /*
    Time Complexity: O(n log(n)) //It takes n log(n) time to convert to binary using two's division
    Space Complexity: O(1) //There is no ddynamically allocated variables

    Given an integer n, find each x such that:
    0<=x<=n
    n+x = n xor x
    where xor denotes the bitwise XOR operator. Return the number of x's satisfying the criteria.

    Example n=4
    4+0 = 4 xor 0 = 4
    4+1 = 4 xor 1 = 5
    4+2 = 4 xor 2 = 6
    4+3 = 4 xor 3 = 7

    There are four values that meet the criteria:

    Return 4.

    System.out.println(sumXor(4));
 */
    public static long sumXor(long n) {
        // Write your code here
        long count = 0;
        //This performs a basic conversion from int to binary using divide by two and checking even or odd
        while(n != 0){
            count += (n%2 == 0)?1:0;
            n/=2;
        }
        count = (long) Math.pow(2,count);
        return count;
    }

    /*
        Given a string of lowercase letters in the range ascii a-z, determine
        the index of a character that can be remove to make the string a palindrome.
        There may be more than one solution, but any will do.
        if the word is already a palindrome or there is no solution return -1.
        otherwise return the index of a character to remove.

        ex: s=bcbc
        either remove index 0 or 3
        System.out.println(palindromeIndex2("aaab"));
     */
    public static int palindromeIndex2(String s) {
        // Write your code here
        System.out.println(s);
        if(s.equals(new StringBuilder(s).reverse().toString())){
            return -1;
        }

        char[] array = s.toCharArray();
        for(int i=0; i<=s.length()/2;i++){
            if(array[i] != array[s.length()-1-i]){
                StringBuilder start = new StringBuilder(s);
                start.deleteCharAt(i);
                StringBuilder end = new StringBuilder(s);
                end.deleteCharAt(s.length()-1-i);

                if(start.toString().equals(start.reverse().toString())){
                    return i;
                } else if(end.toString().equals(end.reverse().toString())){
                    return s.length()-1-i;
                }

            }
        }



        return -1;
    }


    /*
    Given the pointer to the head node of a doubly linked list, reverse the order of the nodes in place.
    That is, change the next and prev pointers of the nodes so that the direction of the list is reversed.
    Return a reference to the head node of the reversed list.

    Note: The head node might be NULL to indicate that the list is empty.
     */
    public static DoublyLinkedListNode reverseDoubly(DoublyLinkedListNode llist) {
        // Write your code here
        if(llist == null) return llist;

        DoublyLinkedListNode current_node = llist;
        DoublyLinkedListNode new_head = llist;

        while(current_node != null){
            DoublyLinkedListNode prev = current_node.prev;
            current_node.prev = current_node.next;
            current_node.next = prev;
            new_head = current_node;
            current_node = current_node.prev;
        }

        return new_head;
    }

    /*
    instead of looking for the position, look for position-1 for simplicity

    Given the pointer to the head node of a linked list and an integer to insert at a certain position,
    create a new node with the given integer as its data attribute, insert this node at the desired position and return the head node.

    A position of 0 indicates head, a position of 1 indicates one node away from the head and so on.
    The head pointer given may be null meaning that the initial list is empty.

    Example
    head refers to the first node in the list 1-2-3
    data = 4
    position = 2

    Insert a node at position 2 with data=4 . The new list is 1-2-4-3
 */
    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        // Write your code here
        SinglyLinkedListNode new_node = new SinglyLinkedListNode(data);
        SinglyLinkedListNode current_node = llist;

        int index=0;
        while(index < position-1){
            current_node = current_node.next;
            index++;
        }

        new_node.next = current_node.next;
        current_node.next = new_node;


        return llist;
    }

    /*
    big sorting
    Adding to a Priority Queue and then fill another list would exceed time

    faster to use compare way

    System.out.println(bigSorting(Arrays.asList("1","5","3", "54654654")));
 */
    public static List<String> bigSorting(List<String> unsorted) {
        // Write your code here
        Collections.sort(unsorted, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()>o2.length()){
                    return 1;
                } else if(o2.length()>o1.length()){
                    return -1;
                } else{
                    BigInteger big1 = new BigInteger(o1);
                    BigInteger big2 = new BigInteger(o2);
                    return big1.compareTo(big2);
                }
            }
        });
        return unsorted;
    }

    /*
    Delete duplicate value nodes from sorted
    You are given the pointer to the head node of a sorted linked list
    where data in the nodes is in ascending order. delete nodes and return a sorted list
    with each distinct value in the original list

    ex: 1-2-2-3-3-3-3-null
        -> 1-2-3-null

    SinglyLinkedListNode headOne = new SinglyLinkedListNode();
    headOne.data = 1;
    SinglyLinkedListNode headOne2 = new SinglyLinkedListNode();
    headOne2.data=3;
    SinglyLinkedListNode headOne22 = new SinglyLinkedListNode();
    headOne22.data=3;
    SinglyLinkedListNode headOne3 = new SinglyLinkedListNode();
    headOne3.data =7;

    headOne.next = headOne2;
    headOne2.next = headOne22;
    headOne22.next = headOne3;
    removeDuplicates(headOne);
 */
    public static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {
        if(llist == null) return llist;

        // Write your code here
        SinglyLinkedListNode current = llist;
        SinglyLinkedListNode next = llist.next;

        while(current != null){
            if(next != null){
                while(current != null && next != null && current.data == next.data){
                    next = next.next;
                }
            }

            current.next = next;
            current = current.next;
            if(next != null){
                next = next.next;
            }

        }
        return llist;
    }

    /*
        Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc.
        (WOT) will be for the next number of days.

        Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all.
        What is the maximum profit you can obtain with an optimum trading strategy?

        ex: 1,2,100
        but the first 2 days = 3
        sell your 2 on day 3 = 200
        res = 200-3

        System.out.println(stockmax(Arrays.asList(1,2,100)));
     */
    public static long stockmax(List<Integer> prices) {
        // Write your code here
        long ans = 0;
        int max_num=0;

        for(int i= prices.size()-1;i>=0;i--){
            max_num = Integer.max(max_num, prices.get(i));
            ans += Integer.max(0, max_num - prices.get(i));
        }
        return ans;
    }

    /*
    You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
    You can change the height of a stack by removing and discarding its topmost cylinder any number of times.
    Find the maximum possible height of the stacks such that all of the stacks are exactly the same height.
    This means you must remove zero or more cylinders from the top of zero or more of the three stacks until they are all the same height,
    then return the height.

    ex:
    [1,2,1,1]
    [1,1,2]
    [1,1]

    There are 4,3 and 2 cylinders in the three stacks, with their heights in the three arrays.
    Remove the top 2 cylinders from h1 (heights = [1, 2]) and from h2 (heights = [1, 1]) so that the three stacks all are 2 units tall.
    Return 2 as the answer.

    The height of the stack is the sum of its values
    it is really a queue because the element to pop first is the one inserted first
    stack 1 remove 1 and 2
    stack 2 removes 1 and 1
    [1,1]
    [2]
    [1,1]

    so the height of them all is 2 which is the sum of its elements

    Note: An empty stack is still a stack.

    System.out.println(equalStacks(Arrays.asList(1,2,1,1), Arrays.asList(1,1,2), Arrays.asList(1,1)));
   */
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        // Write your code here
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        Queue<Integer> q3 = new LinkedList<>();

        h1.stream().forEach(i -> {
            q1.add(i);
        });
        h2.stream().forEach(i -> {
            q2.add(i);
        });
        h3.stream().forEach(i -> {
            q3.add(i);
        });

        int sumA = h1.stream().mapToInt(i -> i).sum();
        int sumB = h2.stream().mapToInt(i -> i).sum();
        int sumC = h3.stream().mapToInt(i -> i).sum();
        while (!(sumA == sumB && sumB == sumC)) {
            int biggest = Math.max(sumA, Math.max(sumB, sumC));
            if (sumA == biggest) {
                sumA -= q1.poll();
            }
            if (sumB == biggest) {
                sumB -= q2.poll();
            }
            if (sumC == biggest) {
                sumC -= q3.poll();
            }
        }
        return sumA;
    }

    /*
      Given an amount and the denominations of coins available, determine how many ways change can be made for amount.
      There is a limitless supply of each coin type.

      Example
      n=3
      c=8,3,1,2

      There are 3 ways to make change for n=3: {1,1,1}, {1,2} and {3}.

      System.out.println(getWays(3, Arrays.asList(8l,3l,1l,2l)));
   */
    public static long getWays(int n, List<Long> c) {
        // Write your code here
        long[] array = new long[n+1];
        Arrays.fill(array,0l);
        array[0]=1;

        for(Long coin : c){
            for(long total = coin; total< array.length; total++){
                long remainder = total-coin;
                array[(int)total] += array[(int)remainder];
            }
        }
        return array[n];
    }


    /*

    Given a string, remove characters until the string is made up of any two alternating characters.
    When you choose a character to remove, all instances of that character must be removed.
    Determine the longest string possible that contains just two alternating letters.

    Example s= abaacdabd

    Delete a, to leave bcdbd. Now, remove the character c to leave the valid string bdbd with a length of 4.
    Removing either b or d at any point would not result in a valid string. Return 4.

    Given a string s, convert it to the longest possible string t made up only of alternating characters.
    Return the length of string t. If no string t can be formed, return 0.
    must have at least 2 distinct letters alternated

    rows and columns updated with last character found
    if new character is the same to the last character in the position
    then duplicated letter will be so mark it as -1 to ignore
    A | B | C
  A
  B
  C

    System.out.println(alternate("beabeefeab"));
 */
    public static int alternate(String str) {
        // Write your code here
        final int NUM_LETTERS = 26;
        int length = str.length();
        int max = 0;

        if(str.length()<=1){
            return 0;
        }

        /* Create arrays representing the 26^2 subproblems */
        int[][] pair = new int[NUM_LETTERS][NUM_LETTERS];
        int[][] count = new int[NUM_LETTERS][NUM_LETTERS];

        for (int i = 0; i < length; i++) {
            char letter = str.charAt(i);
            int letterNum = letter - 'a';

            /* Update row */
            for (int col = 0; col < NUM_LETTERS; col++) {
                if (pair[letterNum][col] == letter) {
                    count[letterNum][col] = -1;
                }
                if (count[letterNum][col] != -1) {
                    pair[letterNum][col] = letter;
                    count[letterNum][col]++;
                }
            }

            /* Update column */
            for (int row = 0; row < NUM_LETTERS; row++) {
                if (pair[row][letterNum] == letter) {
                    count[row][letterNum] = -1;
                }
                if (count[row][letterNum] != -1) {
                    pair[row][letterNum] = letter;
                    count[row][letterNum]++;
                }
            }


            /* Find max in "count" array */


            //make sure to just take max in the last iteration after all letters have been processed
            max=0;
            for (int row = 0; row < NUM_LETTERS; row++) {
                for (int col = 0; col < NUM_LETTERS; col++) {
                    max = Math.max(max, count[row][col]);
                }
            }

        }

        return max;
    }


    /*
    memoization
    keep track of previous computation and use it to evaluate it with current i iteration only

    We define subsequence as any subset of an array. We define a subarray as a contiguous subsequence in an array.

    Given an array, find the maximum possible sum among:

    all nonempty subarrays.
    all nonempty subsequences.
    Print the two values as space-separated integers on one line.

    Note that empty subarrays/subsequences should not be considered.

    Example
    arr = -1,2,3,-4,5,10

    The maximum subarray sum is comprised of elements at indexes 1 through 5. Their sum is 2+3+-4+5+10 = 16 .
    The maximum subsequence sum is comprised of elements at indexes 1,2,4,5 and their sum is 2+3+5+10 = 20.

    System.out.println(maxSubarray(List.of(-2,-5,6,-2,-3,1,5,-6)));

   */
    public static List<Integer> maxSubarray(List<Integer> arr) {
        // Write your code here

        int subsequence = arr.stream().max(Comparator.naturalOrder()).get();
        if(subsequence>0){
            subsequence=0;
        }

        for(int i:arr){
            if(i>0){
                subsequence+=i;
            }
        }

        int subarray = getMaxValue(arr);

        List<Integer> result = new LinkedList<>();
        result.add(subarray);
        result.add(subsequence);

        return result;
    }

    private static int getMaxValue(List<Integer> arr) {
        int currentMax = arr.get(0);
        int max = arr.get(0);

        for(int i=1; i<arr.size(); i++){
            //if keeping the previous sum is bigger than the current i then keep it
            //if not just keep the current max with the current i element and forget previous
            //values
            currentMax = Math.max(arr.get(i), arr.get(i)+currentMax);
            max = Math.max(max, currentMax);
        }

        return max;
    }


    /*
  Chief's bot is playing an old DOS based game.
  There is a row of buildings of different heights arranged at each index along a number line.
  The bot starts at building 0 and at a height of 0.
  You must determine the minimum energy his bot needs at the start so that he can jump to the top of each building without his energy going below zero.

  Units of height relate directly to units of energy. The bot's energy level is calculated as follows:

  If the bot's botEnergy is less than the height of the building, his newEnergy = botEnergy -(height-botEnergy)
  If the bot's botEnergy is greater than the height of the building, his newEnergy = botEnergy + (botEnergy - height)

  Example arr= 2,3,4,3,2

  Starting with botEnergy =4, we get the following table:

  botEnergy   height  delta
  4               2       +2
  6               3       +3
  9               4       +5
  14              3       +11
  25              2       +23
  48

  That allows the bot to complete the course, but may not be the minimum starting value. The minimum starting botEnergy in this case is 3.

  System.out.println(chiefHopper(List.of(2,3,4,3,2)));
 */
    public static int chiefHopper(List<Integer> arr) {
        int minEnergy = 0;
        // Write your code here
        for(int i = arr.size()-1; i >= 0; i--)
        {
            int buildingHeight = arr.get(i);

            if(buildingHeight > minEnergy)
            {
                minEnergy += (int) Math.ceil((buildingHeight - minEnergy) / 2.0);
            }
            else if(buildingHeight < minEnergy)
            {
                minEnergy = (int) Math.ceil((buildingHeight + minEnergy) / 2.0);
            }

            //Nothing occurs in the == case so we simply don't check for it
        }

        return minEnergy;
    }


    /*
        A weighted string is a string of lowercase English letters where each letter has a weight. Character weights are 1 to 26
         The weight of a string is the sum of the weights of its characters. For example:
         apple = 1+16+12+5 = 50
         A uniform string consists of a single character repeated zero or more times.
         For example, ccc and a are uniform strings, but bcb and cd are not.

         ex:
         s=abbcccdddd
         queries: 1,7,5,4,15

         1 and 4 does exist so return YES
         7,5,15 dont so return NO

         string  weight
        a       1  YES
        b       2
        bb      4
        c       3
        cc      6
        ccc     9
        d       4  YES
        dd      8
        ddd     12
        dddd    16

        response: Yes,No,No,Yes,No
        System.out.println(weightedUniformStrings("abbcccdddd", List.of(1,7,5,4,15)));
     */
    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
        // Write your code here
        List<String> result = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        map.put('i', 9);
        map.put('j', 10);
        map.put('k', 11);
        map.put('l', 12);
        map.put('m', 13);
        map.put('n', 14);
        map.put('o', 15);
        map.put('p', 16);
        map.put('q', 17);
        map.put('r', 18);
        map.put('s', 19);
        map.put('t', 20);
        map.put('u', 21);
        map.put('v', 22);
        map.put('w', 23);
        map.put('x', 24);
        map.put('y', 25);
        map.put('z', 26);

        Stack<Character> stack = new Stack<>();
        Map<Integer, String> resultMap = new HashMap<>();

        for(char c : s.toCharArray()){
            if(stack.isEmpty()){
                stack.push(c);
                processPreviosCharacterInStack(map, stack, resultMap);
            } else {
                if(stack.peek()==c) {
                    stack.push(c);
                    processPreviosCharacterInStack(map, stack, resultMap);
                } else {
                    stack.clear();
                    stack.push(c);
                    processPreviosCharacterInStack(map, stack, resultMap);
                }
            }
        }

        if(!stack.isEmpty()){
            processPreviosCharacterInStack(map, stack, resultMap);
            stack.clear();
        }

        for(int i:queries){
            if(resultMap.containsKey(i)){
                result.add("Yes");
            } else {
                result.add("No");
            }
        }
        return result;
    }

    private static void processPreviosCharacterInStack(Map<Character, Integer> map, Stack<Character> stack, Map<Integer, String> resultMap) {
        int charWeight = map.get(stack.peek());
        int totalWeight = charWeight * stack.size();

        resultMap.put(totalWeight, null);
    }

    /*
    This question is designed to help you get a better understanding of basic heap operations.

    There are 3 types of query:

    "1 v" - Add an element v to the heap.
    "2 v" - Delete the element v from the heap.
    "3" - Print the minimum of all the elements in the heap.
    NOTE: It is guaranteed that the element to be deleted will be there in the heap.
    Also, at any instant, only distinct elements will be in the heap.

    ex:
    5           Q = 5
    1 4         insert 4
    1 9         insert 9
    3           print minimum (4)
    2 4         delete 4
    3           print minimum (9)

    res:
    4
    9
   */
    public static void qheap1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        TreeSet<Integer> set = new TreeSet();
        Integer smallest = null;
        for(int i=0; i<q; i++){
            int op = sc.nextInt();

            switch (op){
                case 1:
                    int n= sc.nextInt();
                    set.add(n);

                    if(smallest==null){
                        smallest=n;
                    }
                    else if(smallest > n){
                        smallest=n;
                    }

                    break;

                case 2:
                    n= sc.nextInt();
                    set.remove(n);
                    if(smallest==n){
                        if(set.isEmpty()){
                            smallest=null;
                        } else {
                            smallest= set.iterator().next();
                        }

                    }

                    break;

                case 3:
                    System.out.println(smallest);
                    break;
            }
        }
    }

    /*
    Skyline Real Estate Developers is planning to demolish a number of old,
    unoccupied buildings and construct a shopping mall in their place.
    Your task is to find the largest solid area in which the mall can be constructed.

    There are a number of buildings in a certain two-dimensional landscape.
    Each building has a height, given by h[i] where i belongs [1,n].
    If you join k adjacent buildings, they will form a solid rectangle of area kxmin(h[i],h[i+1],....

    Example
    h = 3,2,3
    A rectangle of height 2 and length 3 can be constructed within the boundaries.
    The area formed is h*k = 2*3=6.

     System.out.println(largestRectangle(List.of(1,2,3,4,5)));
 */
    public static long largestRectangle(List<Integer> h) {
        // Write your code here
        //size+1 so we get an extra 0 value at the last position
        //of the array
        int heights[] = new int[h.size()+1];
        for(int i=0; i<h.size();i++){
            heights[i]= h.get(i);
        }

        long maxArea = 0;
        Stack<Integer> indices = new Stack<Integer>();
        for (int i = 0; i < heights.length; i++) {
            //since we will have 0 in the last position we make sure it goes in here
            //at least once to evaluate
            while (!indices.empty() && heights[i] <= heights[indices.peek()]) {
                int index = indices.pop();
                //i is always last less value
                //while it is popping it increases the multiplier
                long area = (long) heights[index]
                        * (i - (indices.empty() ? 0 : (indices.peek() + 1)));
                maxArea = Math.max(maxArea, area);
            }
            indices.push(i);
        }
        return maxArea;
    }

    /*
    Jesse loves cookies and wants the sweetness of some cookies to be greater than value k.
    To do this, two cookies with the least sweetness are repeatedly mixed. This creates a special combined cookie with:
    sweetness = 1 x Least sweet cookie + 2 x 2nd least sweet cookie).

    This occurs until all the cookies have a sweetness >=k.

    Given the sweetness of a number of cookies, determine the minimum number of operations required. If it is not possible, return -1.

    Example
    k = 7
    a = 1,2,3,9,10,12

    1x1 + 2x2 = 5
    -> 3,5,9,10,12

    1x3 + 2x5 = 13
    -> 9,10,12,13 all cookies have sweetness >=7 after 2 operations then return 2

    The smallest values are .
    Remove them then return  to the array. Now .
    Remove  and return  to the array. Now .
    Remove , return  and .
    Finally, remove  and return  to . Now .
    All values are  so the process stops after  iterations. Return .

    System.out.println(cookies(7, Arrays.asList(1,2,3,9,10,12)));
 */
    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        if(A.isEmpty()){
            return -1;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        queue.addAll(A);

        int count =0;

        while(queue.peek()<k && queue.size()>=2){
            int newCookie = queue.poll() + (queue.poll()*2);
            queue.add(newCookie);
            count++;
        }

        if(queue.peek()>= k && count>0){
            return count;
        } else if(queue.peek()>=k){
            return 0;
        }

        return -1;
    }

    /*
    Hackerland is a one-dimensional city with houses aligned at integral locations along a road.
    The Mayor wants to install radio transmitters on the roofs of the city's houses.
    Each transmitter has a fixed range meaning it can transmit a signal to all houses within that number of units distance away.

    Given a map of Hackerland and the transmission range, determine the minimum number of transmitters so that every house is within range of at least one transmitter.
    Each transmitter must be installed on top of an existing house.

    Example
    x = 1,2,3,5,9
    k=1

    3 antennae at houses 2 and 5 and 9 provide complete coverage. There is no house at location 7 to cover both 5 and 9.
    Ranges of coverage, are 1,2,3, 5, and 9.

    System.out.println(hackerlandRadioTransmitters(Arrays.asList(7,2,4,6,5,9,12,11), 2));
 */
    public static int hackerlandRadioTransmitters(List<Integer> h, int k) {
        // Write your code here
        Collections.sort(h);
        int x[] = new int[h.size()];
        for(int i=0; i<h.size();i++){
            x[i] = h.get(i);
        }

        int counter = 0;
        int i = 0;
        while (i < x.length) {
            counter++;
            int leftStartingPoint = i;

            while((i + 1 < x.length) && (x[i + 1] - x[leftStartingPoint] <= k)) {
                i++;
            }
            int middlePoint = i;

            while((i + 1 < x.length) && (x[i + 1] - x[middlePoint] <= k)) {
                i++; }
            i++;
        }

        return counter;
    }

    /*
       Consider an n-integer sequence, A= {a0,a1,...an-1}.
       We perform a query on A by using an integer, d, to calculate the result of the following expression:
       min (max aj)
       0<i<n-d   i<=j<i+d

       In other words, if we let mi = max(ai+ai+1,)..., then you need to calculate min(m0,m1,...mn-d).

       Given arr and q queries, return a list of answers to each query.

       Example
       arr = [2,3,4,5,6]
       queries = [2,3]

       The first query uses all of the subarrays of length 2: [2,3],[3,4],[4,5],[5,6]. The maxima of the subarrays are [3,4,5,6]. The minimum of these is 3.

       The second query uses all of the subarrays of length 3: [2,3,4].[3,4,5],[4,5,6] . The maxima of the subarrays are [4,5,6]. The minimum of these is 4.

       Return 3,4.

       System.out.println(solve(Arrays.asList(2,3,4,5,6), Arrays.asList(2,3)));
    */
    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
        // Write your code here
        List<Integer> maxList = new ArrayList<>();

        for(int query : queries){
            int minMax = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            //calculate the initial maximum with in the first window

            for(int i=0; i<query; i++){
                max = Math.max(max, arr.get(i));
            }

            minMax = Math.min(minMax, max);

            //slide the window and update the maximum

            for(int i=query; i<arr.size();i++){
                //remove the element that goes out of the window
                if(arr.get(i-query)==max){
                    max = Integer.MIN_VALUE;
                    //recalculate the maximum within the current window
                    for(int j=i-query+1;j<=i;j++){
                        max = Math.max(max, arr.get(j));
                    }
                }

                //update the window with the new element
                max = Math.max(max, arr.get(i));
                //update the window with the minimums of maximums
                minMax = Math.min(minMax, max);
            }
            maxList.add(minMax);
        }
        return maxList;
    }

    /*
    A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string.
    Letters cannot be rearranged. Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?

    Example
    s1 = ABCD
    s2 = ABDC

    These strings have two children with maximum length 3, ABC and ABD.
    They can be formed by eliminating either the D or C from both strings. Return 3.

    System.out.println(commonChild("HARRY", "SALLY"));
 */
    public static int commonChild(String str1, String str2) {
        // Write your code here
        int L[][] = new int[str1.length()+1][str2.length()+1];
        for(int i=0;i<=str1.length();i++){
            for(int j=0;j<=str2.length();j++){
                if(i==0 || j==0)
                    L[i][j]=0;
                else if(str1.charAt(i-1)==str2.charAt(j-1)){
                    L[i][j] = L[i-1][j-1]+1;
                }
                else{
                    L[i][j] = Math.max(L[i-1][j],L[i][j-1]);
                }
            }
        }
        return L[str1.length()][str2.length()];
    }

    /*
        Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each the array
        element between two given indices, inclusive. Once all operations have been performed, return the maximum value in the array.

        Example
        n=10
        queries = [[1,5,3],[4,8,7],[6,9,1]]

        queries are interpreted as follows:

        a b k
        1 5 3
        4 8 7
        6 9 1

        add the values of k between the indices a and b inclusive (1 5 3 -> add a 3 number from 1 to 5=

        index ->  1 2 3 4 5 6 7 8 9 10
            [     3 3 3 3 3 0 0 0 0 0]

        4 8 7 -> add number 7 from 4 to 8

        index ->  1 2 3  4  5 6 7 8 9 10
             [    3 3 3 10 10 7 7 7 0 0

        6 9 1 -> add number 1 from 6 to 9

        index ->  1 2 3  4  5 6 7 8 9 10
             [    3 3 3 10 10 8 8 8 1 0

             max number = 10


        List<Integer> l = new LinkedList<>();
        l.add(1); l.add(5); l.add(3);
        List<List<Integer>> queries = new LinkedList<>();
        queries.add(l);
        l = new LinkedList<>();
        l.add(4); l.add(8); l.add(7);
        queries.add(l);
        l = new LinkedList<>();
        l.add(6); l.add(9); l.add(1);
        queries.add(l);
        System.out.println(arrayManipulation(10, queries));

     */
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long[] result = new long[n];
        int length = queries.size();
        long max = Long.MIN_VALUE;

        for(int i=0; i< length; i++){
            int start = queries.get(i).get(0) -1;
            int end= queries.get(i).get(1) -1;
            int add= queries.get(i).get(2);
            result[start] += add;
            if(end +1<n){
                result[end+1] -= add;
            }
        }

        for(int i=1; i<n; i++){
            result[i] += result[i-1];
            max = Math.max(max, result[i]);
        }
        return max;
    }
    //END HACKER RANK



    static class ListNode {
        int val;
        QuickTest.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, QuickTest.ListNode next) { this.val = val; this.next = next; }
    }


    public static void main(String[] args) throws ParseException {
       // ranks();
        List<Integer> result = climbingLeaderboard(new ArrayList<Integer>(Arrays.asList(100,100,90,90,80)), new ArrayList<Integer>(Arrays.asList(30,60,80,90,101)));
        System.out.println(result);line();

        plusMinus(Arrays.asList(-4,3,-9,0,4,1)); line();

        miniMaxSum(Arrays.asList(256741038, 623958417, 467905213, 714532089, 938071625));line();

        timeConversion("07:05:45PM");line();

        System.out.println(findMedian(Arrays.asList(1,2,3,4,5)));line();

        System.out.println(lonelyinteger(Arrays.asList(2,2,3,4,4)));line();

        System.out.println(lonelyinteger2(Arrays.asList(1,1,4,2,3,4,2,5,5)));line();

        List<List<Integer>> list = new LinkedList<>();
        list.add(Arrays.asList(1,2,3));
        list.add(Arrays.asList(4,5,6));
        list.add(Arrays.asList(7,8,9));
        System.out.println(diagonalDifference(list));line();
        System.out.println(countingSort(Arrays.asList(63,25, 73, 1, 98, 73, 56, 84, 86, 57, 16, 83, 8, 25, 81, 56, 9, 53, 98, 67, 99, 12, 83, 89, 80, 91, 39, 86)));line();

        List<List<Integer>> matrixList = new LinkedList<>();
        matrixList.add(Arrays.asList(112, 42, 83, 119));
        matrixList.add(Arrays.asList(56, 125, 56, 49));
        matrixList.add(Arrays.asList(15, 78, 101, 43));
        matrixList.add(Arrays.asList(62, 98, 114, 108));
        System.out.println(flippingMatrix(matrixList));

        findZigZagSequence(new int[]{1,3,5,7,9,2,4,6,8,10}, 10);

        System.out.println(towerBreakers(3,1));
        System.out.println(towerBreakers(13,11));
        System.out.println(towerBreakers(30,11));

        System.out.println(caesarCipher("Hello_World!", 4));

        System.out.println(palindromeIndex("aabaa"));
        System.out.println(palindromeIndex("aaabcaaa"));

        System.out.println(gridChallenge(Arrays.asList("zzz", "hjk", "mpq", "rtv")));
        System.out.println(superDigit("148",3));
        minimumBribes(Arrays.asList(2,5,1,3,4));
        minimumBribes(Arrays.asList(2,1,5,3,4));


        minimumBribes2(Arrays.asList(2,5,1,3,4));
        minimumBribes2(Arrays.asList(2,1,5,3,4));

        minimumBribes3(Arrays.asList(2,5,1,3,4));
        minimumBribes3(Arrays.asList(2,1,5,3,4));


        List<List<Integer>> pumpes = new LinkedList<>();
        pumpes.add(new LinkedList<>(){{add(1);add(5);}});
        pumpes.add(new LinkedList<>(){{add(10);add(3);}});
        pumpes.add(new LinkedList<>(){{add(3);add(4);}});

        System.out.println(truckTour(pumpes));

        SinglyLinkedListNode headOne = new SinglyLinkedListNode();
        headOne.data = 1;
        SinglyLinkedListNode headOne2 = new SinglyLinkedListNode();
        headOne2.data=3;
        SinglyLinkedListNode headOne3 = new SinglyLinkedListNode();
        headOne3.data =7;
        headOne.next = headOne2;
        headOne2.next = headOne3;


        SinglyLinkedListNode headTwo = new SinglyLinkedListNode();
        headTwo.data = 1;
        SinglyLinkedListNode headTwo2 = new SinglyLinkedListNode();
        headTwo2.data=2;
        headTwo.next = headTwo2;

        System.out.println(mergeLists(headOne, headTwo));

       // queueUsingTwoStacks();

        System.out.println(isBalanced("{[()]}"));
        System.out.println(isBalanced("{[(])}"));
        System.out.println(isBalanced("{{[[(())]]}}"));

        System.out.println(pairs(3,Arrays.asList(5,3,6,8,99,1)));

        //simpleTextEditor();

        System.out.println(cookies(9,Arrays.asList(2,7,3,6,4,6)));

        System.out.println(legoBlocks(2,3));

        List<List<Integer>> searchList = new LinkedList<>();
        searchList.add(new ArrayList<>(){{add(1);add(2);}});
        searchList.add(new ArrayList<>(){{add(1);add(3);}});
        System.out.println(searchFirstBreath(4,2, searchList, 1));

        Node root = new Node();
        root.data = 1;
        Node right1 = new Node();
        right1.data = 2;
        root.right = right1;
        Node right2 = new Node();
        right2.data=5;
        right1.right = right2;
        Node left = new Node();
        left.data = 3;
        right2.left = left;
        Node right3= new Node();
        right3.data=4;
        left.right = right3;
        Node right4 = new Node();
        right4.data =6;
        right2.right= right4;

        preOrder(root);


        Node2 n1 = new Node2();
        Node2 n2 = new Node2();
        Node2 n3 = new Node2();
        Node2 n4 = new Node2();
        Node2 n5 = new Node2();
        n1.left = n2;
        n3.data = 'A';
        n1.right = n3;
        n4.data = 'B';
        n2.left = n4;
        n5.data = 'C';
        n2.right = n5;

        System.out.println();
        decode("1001011", n1);

        noPrefix(Arrays.asList("aab", "defgab", "abcde", "aabcde", "bbbbbbbbbb", "jabjjjad"));

        System.out.println(matchingStrings(Arrays.asList("ab","ab","abc"),Arrays.asList("ab","abc","bc")));

        System.out.println(flippingBits(9L));

        System.out.println(pangrams("We promptly judged antique ivory buckles for the next prize"));

        System.out.println(birthday(Arrays.asList(2,2,1,3,2),4,2));

        System.out.println(sockMerchant(7,Arrays.asList(1,2,1,2,1,3,2)));

        System.out.println(maxMin(3, Arrays.asList(10,100,300,200,1000,20,30)));

        List<List<Integer>> dynamicList= new ArrayList<>();
        dynamicList.add(Arrays.asList(1,0,5));
        dynamicList.add(Arrays.asList(1,1,7));
        dynamicList.add(Arrays.asList(1,0,3));
        dynamicList.add(Arrays.asList(2,1,0));
        dynamicList.add(Arrays.asList(2,1,1));

        System.out.println(dynamicArray(2,dynamicList));
        System.out.println(balancedSums(Arrays.asList(2,0,0,0)));

        System.out.println(isValid("aaaaabc"));

        List<String> logs = new LinkedList<>();
        logs.add("88 99 200");logs.add("88 99 300"); logs.add("99 32 100"); logs.add("12 12 15");
        System.out.println(processLogs(logs,2));

        List<Integer> startindexes = new LinkedList<>();
        startindexes.add(1);

        List<Integer> endIndexes = new LinkedList<>();
        endIndexes.add(3);
        System.out.println(numberOfItems("*|*|",startindexes,endIndexes));

        SinglyLinkedListNode reverseheadOne = new SinglyLinkedListNode();
        reverseheadOne.data = 1;
        SinglyLinkedListNode reverseheadOne2 = new SinglyLinkedListNode();
        reverseheadOne2.data=2;
        SinglyLinkedListNode reverseheadOne3 = new SinglyLinkedListNode();
        reverseheadOne3.data =3;
        reverseheadOne.next = reverseheadOne2;
        reverseheadOne2.next = reverseheadOne3;
        System.out.println(reverse(reverseheadOne));

        System.out.println(icecreamParlor(100, Arrays.asList(5,75,25)));

        System.out.println(gamingArray(List.of(2,3,5,4,1)));

        spiral(3,4);

        System.out.println(superDigit2("9875",4));

        line();


    }





}
