package com.example.tumbaburros.hackerrank;

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
        String time = LocalTime.parse("06:00 PM", DateTimeFormatter.ofPattern("hh:mm a", Locale.US))
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
                    leftDiagonal += Math.abs(xList.get(y));
                }
                if(x+y+1 == size){
                    List<Integer> xList = list.get(x);
                    rightDiagonal += Math.abs(xList.get(y));
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
    }
}
