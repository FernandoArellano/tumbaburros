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

    //Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
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

    //new methods
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

    public static int findMedian(List<Integer> arr) {
        // Write your code here
        if((arr.size()%2)==0){
            System.out.println("List should have odd elements");
            return -1;
        }

        arr= arr.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        return arr.get(arr.size()/2);
    }

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


    }
}
