package com.example.tumbaburros.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
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

    public static void squareToAll(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> squares = list.stream().map(i->i*i).collect(Collectors.toList());
        System.out.println(squares);
    }

    public static void multiples3(){
        List<Integer> list = Arrays.asList(30,333,1,5,8,9,156,132);
        list.stream().filter(i->i%3==0).forEach(i-> System.out.print(i+"-"));
    }

    public static void sortedByLength(){
        List<String>list = Arrays.asList("a","bb","ccc","#ferrr");
        list.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
    }

    public static void sumAll(){
        List<Integer> list = Arrays.asList(30,333,1,5,8,9,156,132);
        int value = list.stream().reduce(0, (acumulator, i) -> acumulator+i).intValue();
        System.out.println(value);

    }

    public static void allMatch(){
        List<Integer> list = Arrays.asList(30,333,1,5,8,9,156,132);
        boolean result = list.stream().allMatch(i-> i%3==0);
        if(result){
            System.out.println("All are dividers of 3");
        } else{
            System.out.println("Not all");
        }
    }

    public static void anyMatch(){
        List<Integer> list = Arrays.asList(30,333,1,5,8,9,156,132);
        boolean result = list.stream().anyMatch(i->i%3==0);
        if(result){
            System.out.println("One is divider of 3");
        } else{
            System.out.println("None");
        }
    }

    public static void concat(){
        Stream<Integer> stream = Stream.of(1,2,3);
        Stream<Integer> stream2 = Stream.of(5,6,7);

        int value = Stream.concat(stream, stream2).reduce(0, (sub,i) -> sub+i).intValue();
        System.out.println(value);
    }

    public static void timesA(){
        List<String> list = Arrays.asList("Anita","America","Angelica","Fer","Chachil");
        long count = list.stream().filter(s-> s.startsWith("An")).count();
        System.out.println(count);
    }

    public static void flatMap(){
        Stream<String> stream1 = Stream.of("Fer", "Are", "Sal");
        Stream<Stream<String>> stream2 = Stream.of(stream1);
        Stream<Stream<String>> stream3 = Stream.of(stream1);

        List<Stream<String>> list = stream2.collect(Collectors.toList());
        System.out.println(list);
        line();
        List<String> list2 = stream3.flatMap(Function.identity()).collect(Collectors.toList());
        System.out.println(list2);
    }



    public static void forEach(List<String> list){
        list.stream().forEach(System.out::println);
    }

    public static void listForEach(List<String> list){
        list.forEach(System.out::println);
    }

    public static void testPredicate(){
        Predicate<String> p = s->s.length()>10;
        System.out.println(p.test("Arellano"));
    }

    public static void testSupplier(){
        Supplier<Integer> s = ()->new Random().nextInt(5);
        System.out.println(s.get());
    }

    public static void testFunction(){
        Function<String,String> f = (s) -> s.toUpperCase();
        System.out.println(f.apply("fernando"));
    }

    public static void testConsumer(){
        Consumer<String> c = (s)->{
            System.out.println(s);
        } ;
        c.accept("Consumer Test");
    }

    public static void testParallel(){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<100; i++){
            list.add(i);
        }

        list.parallelStream().filter(i->i>90).forEach(System.out::println);
    }

    public static void removeIf(){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<40; i++){
            list.add(i);
        }
        System.out.println(list);

        list.removeIf(i->i%2==0);

        System.out.println(list);
    }

    public static void optional(String s){
        Optional<String> optional = Optional.ofNullable(s);
        System.out.println(optional.orElseGet(()->"Not specified"));
    }

    public static void reduce1(){
        List<String> words = Arrays.asList("GFG", "Geeks", "for",
                "GeeksQuiz", "GeeksforGeeks", "fer");

        Optional<String> optional = words.stream().reduce((word1, word2)-> word1.length()>word2.length()?word1:word2);
        if(optional.isPresent()){
            System.out.println(optional.get());
        }
    }

    public static void reduce2(){
        List<Integer> list = Arrays.asList(3,4,6,7,1,9,22);

        Integer optional = list.stream().reduce(0, (num1,num2) -> num1+num2);
        System.out.println(optional);
    }

    public static void reduce3(){
        List<String> words = Arrays.asList("GFG", "Geeks", "for",
                "GeeksQuiz", "GeeksforGeeks");

        Optional<String> optional = words.stream().reduce((word1, word2) -> word1 + "-" + word2);
        System.out.println(optional.get());
    }

    public static void dropWhile1(){
        System.out.println("Drop While 1");
        // create a stream of numbers from 1 to 10
        Stream<Integer> stream
                = Stream.of(4, 4, 4, 5, 6, 7, 8, 9, 10, 3, 3, 4);

        // apply dropWhile to drop all the numbers
        // matches passed predicate
        List<Integer> list
                = stream.dropWhile(number -> (number / 4 == 1))
                .collect(Collectors.toList());

        // print list
        System.out.println(list);
    }

    public static void takeWhile1(){
        System.out.println("Take While 1");
        // create a stream of numbers from 1 to 10
        Stream<Integer> stream
                = Stream.of(4, 4, 4, 5, 6, 7, 8, 9, 10,3,3,4);

        // apply dropWhile to drop all the numbers
        // matches passed predicate
        List<Integer> list
                = stream.takeWhile(number -> (number / 4 == 1))
                .collect(Collectors.toList());

        // print list
        System.out.println(list);
    }

    //ignore while it is true right after a false will keep them all
    public static void dropWhile2(){
        System.out.println("Drop while 2");
        Stream<String> stream = Stream.of("aman", "amar", "suraj", "suvam", "Zahafuj");
        //Stream<String> stream = Stream.of("aman", "amar", "suraj", "suvam", "Zahafuj");

        List<String> list = stream.dropWhile(name -> (name.charAt(0) == 'a')).collect(Collectors.toList());
        System.out.println(list);
    }

    //mientras se cumpla la condicion se almacena, si desde el primero no se cumplio no se almacena ninguna
    public static void takeWhile(){
        Stream<String> stream = Stream.of("aman", "amar", "auraj", "suvam", "Zahafuj");
        List<String> list= stream.takeWhile(name -> (name.charAt(0) == 'a')).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void asDoubleStream(){
        IntStream stream = IntStream.range(0,10);
        DoubleStream doubleStream = stream.asDoubleStream();
        doubleStream.forEach(System.out::println);
    }

    public static void anyMatch2(){
        List<String> list = Arrays.asList("Fer","Are","Coca","Pepsi","Quesadilla");
        System.out.println(list.stream().anyMatch(s->s.equals("Quesadilla")));
    }

    public static void average(){
        List<Integer> list = new ArrayList<>(){{
            add(33);add(23);add(22);
        }};

        list.stream().mapToInt(i->i).average().ifPresent(System.out::println);
    }

    public static void toUpper(){
        List<String> list = Arrays.asList("Fer","Are","Coca","Pepsi","Quesadilla");
        list.stream().map(s->s.toUpperCase()).forEach(System.out::println);
    }

    public static void sumOddEven(){
        List<Integer> list = new ArrayList<>(){{
            add(33);add(23);add(22);
        }};

        int oddSum = list.stream().filter(i->i%2!=0).mapToInt(Integer::intValue).sum();
        int evenSum = list.stream().filter(i->i%2==0).mapToInt(i->i).sum();
        System.out.println(oddSum);
        System.out.println(evenSum);
    }

    public static void incrementing(){
        System.out.println("Incrementing");

        //Immutable list
        List<Integer> list = List.of(1,2,3,444,555,666,777777,2,3);
        Optional<Integer> value = list.stream().reduce((num1,num2)->num1>num2?num1:num2);
        System.out.println(value);
    }

    public static void removeBlanks(){
        List<String> list = List.of("Fernando", "\n", "  ", " Ana ");
        list = list.stream().filter(Predicate.not(String::isBlank)).collect(Collectors.toList());
        System.out.println(list);
    }

    //Collectors.joining()
    public static void joining(){
        List<String> list = List.of("Fer", "Arellano", "Rattt", "Treees");
        String value = list.stream().map(String::toUpperCase).collect(Collectors.joining(","));
        System.out.println(value);
    }

    public static void factorialReduce(){
        System.out.println("Factorial reduce");
        int value = IntStream.range(1,6).reduce(1,(acum, i) -> {
            return acum*i;
        } );
        System.out.println(value);
    }

    public static void oddsReduce(){
        System.out.println("Odds Reduce");
        int value = IntStream.range(1,10).filter(i->i%2!=0).reduce(0, (partialResult, i) -> i+partialResult);
        System.out.println(value);
    }

    public static void generateReduce(){
        System.out.println("GenerateReduce");
        List<Integer> list = Stream.generate(new Random()::nextInt).limit(5).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void generateSmallRandom(){
        System.out.println("generateSmallRandom");
        List<Integer> list = Stream.generate(()->{
            Random random = new Random();
            return random.nextInt(10);
        }).limit(3).collect(Collectors.toList());
        System.out.println(list);
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
        squareToAll();line();
        multiples3();line();
        sortedByLength();line();
        sumAll();line();
        allMatch();line();
        anyMatch();line();
        concat();line();
        timesA();line();
        flatMap();line();
        forEach(Arrays.asList("Fer", "Arellano","Raton")); line();
        listForEach(Arrays.asList("Gato","Perro","Gremblin")); line();
        testPredicate(); line();
        testSupplier(); line();
        testFunction(); line();
        testConsumer(); line();
        testParallel(); line();
        removeIf(); line();
        optional(null); line();
        optional("Ferrrr"); line();
        anyMatch();line();
        reduce1();line();
        reduce2();line();
        reduce3();line();
        dropWhile1();line();
        dropWhile2();line();
        takeWhile();line();
        asDoubleStream();line();
        anyMatch2();line();
        average();line();
        toUpper();line();
        sumOddEven();line();
        max();line();
        takeWhile1();line();
        incrementing();line();
        removeBlanks();line();
        joining();line();
        factorialReduce();line();
        oddsReduce();line();
        generateReduce();line();
        generateSmallRandom();line();
    }
}
