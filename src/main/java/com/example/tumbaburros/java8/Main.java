package com.example.tumbaburros.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void line(){
        System.out.println("----------------------------------------");
    }
    //remove even numbers
    public static void remove(){
        System.out.println("Remove");
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);
        System.out.println(list);
        list.removeIf(i->i%2==0);
        System.out.println(list);
    }

    //separate even and odd from list
    public static void separate(){
        System.out.println("Separate");
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);list.add(6);
        System.out.println(list);
        List<Integer> odd = list.stream().filter(i->i%2!=0).collect(Collectors.toList());
        System.out.println(odd);
        System.out.println(list);
        List<Integer> even = list.stream().filter(i->i%2==0).collect(Collectors.toList());
        System.out.println(even);
        System.out.println(list);
    }

    //remove duplicates
    public static void duplicates(){
        System.out.println("Duplicates");
        List<String> list = new ArrayList<>(){{
          add("Fer"); add("Fer"); add("Are"); add("Are"); add("are"); add("fer");
        }
        };
        System.out.println(list);
        List<String> list2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }

    //Count ocurrences
    public static void ocurrencesString(){
        System.out.println("Occurrences");
        String s = "Fernando";
        Map<Character, Long> result =s.chars().mapToObj(c-> (char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(result);
    }

    //count ocurrences in list
    public static void ocurrencesList(){
        System.out.println("Occurrences List");
        List<String> list = new ArrayList<>(){{
            add("Fer"); add("Fer"); add("Are"); add("Are"); add("are"); add("fer");
        }
        };

        Map<String,Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(map);
    }

    //reverse order list
    public static void reverseList(){
        System.out.println("Reverse List");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };

        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    //max
    public static void max(){
        System.out.println("Max");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };
        Optional<Integer> optional = list.stream().max(Comparator.naturalOrder());
        optional.ifPresent(System.out::println);
    }

    //min
    public static void min(){
        System.out.println("Min");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };
        Optional<Integer> optional = list.stream().min(Comparator.naturalOrder());
        optional.ifPresent(System.out::println);
    }

    //two unsorted arrays into a sorted one
    public static void mergeArrays(){
        System.out.println("Merge arrays");
        int [] a = new int[]{1,3,5,7,9};
        int [] b = new int[]{1,2,3,4,5};

        IntStream intStream = IntStream.concat(IntStream.of(a), IntStream.of(b));
        intStream.distinct().sorted().forEach(i-> System.out.println(i));
    }

    //3 max numbers on list
    public static void max3(){
        System.out.println("Max3");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };
        list = list.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        System.out.println(list);
    }

    //3 min numbers on list
    public static void min3(){
        System.out.println("Min3");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };
        list = list.stream().sorted(Comparator.naturalOrder()).limit(3).collect(Collectors.toList());
        System.out.println(list);
    }

    //sum all digits in number
    public static void sumDigits(){
        System.out.println("Sum Digits");
        String number = "123456";
        int result = Stream.of(number.split("")).collect(Collectors.summingInt(Integer::parseInt));

        System.out.println(result);
        int result2=0;
        for(int i=0; i<number.length();i++){
            int value = Integer.parseInt(String.valueOf(number.charAt(i)));
            result2= result2+value;
        }
        System.out.println(result2);
    }

    //max
    public static void secondLarger(){
        System.out.println("Second Larger");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };
        list.stream().sorted(Comparator.reverseOrder()).skip(1).limit(1).forEach(System.out::println);
    }

    //sort per increase order length
    public static void sortedByLengthOrder(){
        System.out.println("Sorted by Length order");
        List<String> list = new ArrayList<>(){{
            add("Fer"); add("Fer1"); add("Are10"); add("Are100"); add("areeddd"); add("fertteeetrt");
        }
        };

        list.stream().sorted(Comparator.comparing(String::length).thenComparing(String::indexOf).reversed()).forEach(System.out::println);
    }

    public static void commonElementsIn2Array(){
        System.out.println("Common Elements 2 lists");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);
        }
        };

        List<Integer> list2 = new ArrayList<>(){{
            add(2);add(3);add(4);
        }
        };
        list.stream().filter(list2::contains).forEach(System.out::println);

    }

    public static void reverseWord(){
        String word = "Fernando";
        char [] array = word.toCharArray();
        for(int i=0; i<array.length/2;i++){
            char temp = array[word.length()-1-i];
            array[array.length-1-i] = array[i];
            array[i] = temp;
        }
        System.out.println(new String(array));
    }

    public static void sumArray(){
        IntStream stream = IntStream.of(1,2,5,4,7);

        System.out.println(stream.sum());
        stream = IntStream.of(1,2,5,4,7);
        System.out.println(stream.average().getAsDouble());
    }

    public static void sumFirst10(){
        System.out.println(IntStream.range(0,10).sum());
    }

    public static void startWithNumber(){
       List<String> list = Arrays.asList("Fer","10Fer","Miguel","1Chisco");

       list.stream().filter(s-> Character.isDigit(s.charAt(0))).forEach(System.out::println);
    }

    public static void findDuplicates(){
        System.out.println("Duplicates");
        List<Integer> list = new ArrayList<>(){{
            add(1);add(2);add(3);add(4);add(5);add(6);add(3);add(4);
        }
        };
        Set<Integer> set = new HashSet<>();

        list.stream().filter(i-> !set.add(i)).forEach(System.out::println);
        System.out.println(set);
    }

    public static void main(String[] args) {
        remove();line();
        separate();line();
        duplicates();line();
        ocurrencesString();line();
        ocurrencesList();line();
        reverseList();line();
        max();line();
        min();line();
        mergeArrays();line();
        max3();line();
        min3();line();
        sumDigits();line();
        secondLarger();line();
        sortedByLengthOrder();line();
        commonElementsIn2Array();line();
        reverseWord();line();
        sumArray();line();
        sumFirst10();line();
        startWithNumber();line();
        findDuplicates();line();
    }
}
