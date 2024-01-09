package com.example.tumbaburros.collectors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void line(){
        System.out.println("----------------------------------------");
    }

    public static void averagingDoubleIntLong(){
        System.out.println("averagingintdouble");
        Double collect = Stream.iterate(1, i -> i < 10, i -> i+1 ).collect(Collectors.averagingInt(i -> i));
        Double value = Stream.of(1,2,3,4,5,6,7,8,9).collect(Collectors.averagingDouble(i->Double.valueOf(i)));
        System.out.println(collect);
        System.out.println(value);
    }

    public static void collectAndUnmodifiable(){
        System.out.println("CollectAndUnmodifiable");
        List<String> list = Stream.of("Fer", "Are", "String").collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println(list);
    }

    public static void collectorCount(){
        System.out.println("collectorCount");
        Long collect = Stream.of("Fer", "Are", "String").collect(Collectors.counting());
        System.out.println(collect);
        Map<String, Long> map = Stream.of("Fer", "Are", "String").collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(map);
    }

    public static void collectorJoining(){
        System.out.println("collectorJoining");
        String value = Stream.of("Fer", "Are", "String").collect(Collectors.joining("-"));
        System.out.println(value);
        String value2 = Stream.of("Fer", "Are", "String").collect(Collectors.joining("_","","@hotmail.com"));
        System.out.println(value2);
    }

    public static void collectorsMapping(){
        List<Employee> employeeList
                = Arrays.asList(new Employee("Tom Jones", 45, 15000.00),
                new Employee("Harry Andrews", 45, 7000.00),
                new Employee("Ethan Hardy", 65, 8000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Deborah Sprightly", 29, 9000.00));

        List<String> employeeNames = employeeList.stream().collect(Collectors.mapping(Employee::getName,Collectors.toList()));
        System.out.println(employeeNames);
    }

    public static void collectorsMappingGrouping(){
        List<Employee> employeeList
                = Arrays.asList(new Employee("Tom Jones", 45, 15000.00),
                new Employee("Harry Andrews", 45, 7000.00),
                new Employee("Ethan Hardy", 65, 8000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Deborah Sprightly", 29, 9000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Nancy Smith", 23, 10000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Ethan Hardy", 66, 8000.00)
        );

        Map<String, List<Integer>> result = employeeList.stream().collect(Collectors.groupingBy(e->e.getName(),Collectors.mapping(e->e.getAge(),Collectors.toList())));
        System.out.println(result);
    }

    public static void collectorsMaxBy(){
        Optional<Integer> optional = Stream.iterate(0,i->i<100,i->i+1).collect(Collectors.maxBy(Comparator.naturalOrder()));
        System.out.println(optional);
    }

    public static void collectorsMinBy(){
        Optional<Integer> optional = Stream.iterate(0,i->i<100,i->i+1).min(Comparator.naturalOrder());
        System.out.println(optional);
    }

    public static void collectorsPartitioning(){
        Map<Boolean,List<Integer>> map = Stream.iterate(0,i->i<11,i->i+1).collect(Collectors.partitioningBy(i->i<5));
        System.out.println(map);
    }

    public static void collectorsPartitioning2(){
        Map<Boolean, Long> map = Stream.iterate(0,i->i<11,i->i+1).collect(Collectors.partitioningBy(i->i<5, Collectors.counting()));
        System.out.println(map);
    }

    public static void collectorsReducing(){
        List<Employee> employeeList
                = Arrays.asList(new Employee("Tom Jones", 45, 15000.00),
                new Employee("Harry Andrews", 45, 7000.00),
                new Employee("Ethan Hardy", 65, 8000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Deborah Sprightly", 29, 9000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Deborah Sprightly", 100, 9000.00),
                new Employee("Nancy Smith", 23, 10000.00),
                new Employee("Nancy Smith", 22, 10000.00),
                new Employee("Ethan Hardy", 66, 8000.00)
        );

        Map<String, Optional<Employee>> collect = employeeList.stream().collect(Collectors.groupingBy(e -> e.getName(), Collectors.reducing((e1, e2) -> e1.getAge() > e2.getAge() ? e1 : e2)));
        System.out.println(collect);
    }

        public static void collectorsSummarizingInt(){
            IntSummaryStatistics intSummaryStatistics = Stream.iterate(0, i -> i < 20, i -> i + 1).collect(Collectors.summarizingInt(i -> i));
            System.out.println(intSummaryStatistics);
        }

        public static void collectorSumming(){
            System.out.println("Collector Summing");
            int value = Stream.iterate(0, i->i<20, i->i+1).collect(Collectors.summingInt(i->i));
            System.out.println(value);
        }

        public static void collectorCollection(){
            System.out.println("collector collection");
            List<Integer> list = Stream.iterate(0, i->i<20, i->i+1).collect(Collectors.toCollection(ArrayList::new));
            System.out.println(list);
        }

        public static void collectorToMap(){
            System.out.println("collector map");
            List<String> list = List.of("Ana","Fernando","Pau ");
            Map<String, Integer> map = list.stream().collect(Collectors.toMap(Function.identity(), s->s.length()));
            System.out.println(map);
        }

        public static void collectorToSet(){
            System.out.println("collector Set");
            List<Employee> employeeList
                    = Arrays.asList(new Employee("Tom Jones", 45, 15000.00),
                    new Employee("Harry Andrews", 45, 7000.00),
                    new Employee("Ethan Hardy", 65, 8000.00),
                    new Employee("Nancy Smith", 22, 10000.00),
                    new Employee("Deborah Sprightly", 29, 9000.00),
                    new Employee("Nancy Smith", 22, 10000.00),
                    new Employee("Deborah Sprightly", 100, 9000.00),
                    new Employee("Nancy Smith", 23, 10000.00),
                    new Employee("Nancy Smith", 22, 10000.00),
                    new Employee("Ethan Hardy", 66, 8000.00)
            );

            Set<String> set = employeeList.stream().map(e->e.getName()).collect(Collectors.toSet());
            System.out.println(set);
        }

    public static void main(String[] args) {
        averagingDoubleIntLong();line();
        collectAndUnmodifiable();line();
        collectorCount();line();
        collectorJoining();line();
        collectorsMapping();line();
        collectorsMappingGrouping();line();
        collectorsMaxBy();line();
        collectorsMinBy();line();
        collectorsPartitioning();line();
        collectorsPartitioning2();line();
        collectorsReducing();line();
        collectorsSummarizingInt();line();
        collectorSumming();line();
        collectorCollection();line();
        collectorToMap();line();
        collectorToSet();line();
    }
}
