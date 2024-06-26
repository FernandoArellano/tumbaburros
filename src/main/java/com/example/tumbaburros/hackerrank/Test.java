package com.example.tumbaburros.hackerrank;

import com.fasterxml.jackson.core.JsonToken;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    public static void line(){
        System.out.println("----------------------------------------");
    }

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

    //Sherlock considers a string to be valid if all characters of the string appear the same number of times.
    // It is also valid if he can remove just  character at  index in the string, and the remaining characters will occur the same number of times.
    // Given a string , determine if it is valid. If so, return YES, otherwise return NO.
    //Example
    // abc
    //This is a valid string because frequencies are a=1 b=1 c=1.
    public static String isValid(String s) {
        Map<String, Long> map = Stream.of(s.split("")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        Set<Long> set = map.values().stream().collect(Collectors.toSet());

        if(set.size()>2){
            return "NO";
        } else if(set.size()==1){
            return "YES";
        } else {
            Set<String> keySet = map.keySet();
            if(keySet.size()!=2){
                return "NO";
            }
            Iterator<Long> iterator = set.iterator();
            long a = iterator.next();
            long b = iterator.next();

            int counta=0;
            int countb=0;
            for(Map.Entry<String,Long> entry: map.entrySet()){
                if(entry.getValue() == a){
                    counta++;
                } else if(entry.getValue() == b){
                    countb++;
                }
            }
            if(counta == 1 || countb == 1){
                return "YES";
            }
            if(Math.min(counta,countb) > 1){
                return "NO";
            }
        }

        return "YES";
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


    //Given an array of  distinct integers, transform the array into a zig zag sequence by permuting the array elements.
    // A sequence will be called a zig zag sequence if the first  elements in the sequence are in increasing order
    // and the last  elements are in decreasing order, where . You need to find the lexicographically smallest zig
    // zag sequence of the given array.
    //al ordenar la primera mitad ya esta
    //despues al intercambiar el ultimo con el medio la posicion media y final ya estan
    //ya solo invertir las posiciones medio+1 a tamaño del arreglo menos 2 (el ultimo elemento ya estara donde debe)
    public static void findZigZagSequence(int [] a, int n){
        System.out.println("FindZigZag");
        Arrays.sort(a);
        int mid = (n)/2;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

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

    public static void findZigZagSequence2(int a[], int n){
        System.out.println("finsZigzag2");

        Arrays.sort(a);
        int mid = (n/2)-1;
        List<Integer> result = new LinkedList<>();
        for(int i=0; i<=mid;i++){
            result.add(a[i]);
        }

        for(int i=n-1; i>mid;i--){
            result.add(a[i]);
        }
        System.out.println(result);
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
    //Store the new value of  to an answers array.
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


    //Two friends like to pool their money and go to the ice cream parlor. They always choose two distinct flavors and they spend all of their money.
    //Given a list of prices for the flavors of ice cream, select the two that will cost all of the money they have.
    //Example.
    //m=6  cost[1,3,4,5,6]
    //The two flavors that cost 1 and 5  meet the criteria. Using 1-based indexing, they are at indices 1 and 4.
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here
        List<Integer>result = new LinkedList<>();
        List<Integer> ordered = new ArrayList<>(arr);
        Collections.sort(ordered);
        for(int i=0; i<ordered.size();i++){
            int value = ordered.get(i);
            int index = Collections.binarySearch(ordered, m-value);
            if(index>=0){
                int index1 = (arr.indexOf(ordered.get(i)))+1;
                int index2 = (arr.indexOf(ordered.get(index)))+1;
                if(index1 == index2){
                    index2++;
                }
                result.add(Math.min(index1,index2));
                result.add(Math.max(index1,index2));
                break;
            }
        }
        return result;
    }


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
                    String toAppend = scan.next();
                    sb.append(toAppend);
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

    //You are a waiter at a party. There is a pile of numbered plates. Create an empty  array. At each iteration, ,
    // remove each plate from the top of the stack in order. Determine if the number on the plate is evenly divisible by the
    // prime number. If it is, stack it in pile . Otherwise, stack it in stack . Store the values in  from top to bottom in . In the next iteration,
    // do the same with the values in stack . Once the required number of iterations is complete, store the remaining values in  in ,
    // again from top to bottom. Return the  array.
    //al cambiar a la stack cambia el orden de a, pero no afecto segun?
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

    //PriorityQueue good performance, from smaller to top all the time
    //poll retrieves and remove the tiniest element always
    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        int count=0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(Integer i:A){
            priorityQueue.add(i);
        }
        if(priorityQueue.peek()>=k){
            return 0;
        }

        while(priorityQueue.size()>1 && k>count && priorityQueue.peek()<k){
            int value = priorityQueue.poll()+(priorityQueue.poll()*2);
            count++;
            priorityQueue.add(value);

        }
        if (priorityQueue.size() == 0)
            return -1;

        if (priorityQueue.peek().intValue() >= k)
            return count;
        else
            return -1;
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


    /*
    You are given a string s, which contains stars *.
    In one operation, you can:
    Choose a star in s.
    Remove the closest non-star character to its left, as well as remove the star itself.
    Return the string after all stars have been removed.
    Note:
    The input will be generated such that the operation is always possible.
    It can be shown that the resulting string will always be unique.

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
                        if(Math.abs(stack.peek())==Math.abs(asteroid)){
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

    /*
    You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
    The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes
    the largest integer less than or equal to x.
    For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively

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
        findZigZagSequence2(new int[]{1,3,5,7,9,2,4,6,8,10}, 10);

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
