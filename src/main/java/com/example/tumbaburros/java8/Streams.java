package com.example.tumbaburros.java8;

/*
    forEach, Stream.of,

    Intermediate Operations (return another Stream)
    filter(), map(),flatMap(),distinct(),sorted(),peek(),limit(), skip()


    Terminal operations
    anyMatch(),allMatch(),noneMatch(),collect(),count(),findAny(),findFirst(),forEach(),min(),max(),reduce(),toArray()
 */

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);list.add(15);list.add(25);list.add(0);list.add(10);list.add(20);

        List<String> stringList = new ArrayList<>();
        stringList.add("Fer");stringList.add("Adriana");stringList.add("Margarita");stringList.add("Miguel");stringList.add("Victoria");stringList.add("Domingo");stringList.add("Antonio");

        Streams streams = new Streams();
        System.out.println(streams.getEvenList(list));
        System.out.println(streams.getDoubledList(list));
        System.out.println(streams.getLongNameList(stringList));
        System.out.println(streams.getCountLongNameList(stringList));
        System.out.println(streams.getSortedList(stringList));
        System.out.println(streams.getReverseSortList(stringList));
        System.out.println(streams.getMinValue(list));
        System.out.println(streams.getMaxValue(list));
        streams.printStrings(stringList);
        streams.convertListToArray(list);

        System.out.println("*********************");
        streams.createStreamFromArray();

        streams.tryMap();

        streams.sortReversed();

        streams.biggest();

        streams.filterNull();

        streams.biggestRollNo();

        streams.toArray();

        streams.increaseAndPrintNewSalary();

        streams.printEvenNumbersSkippingFirst3AndLimitingTo10();

        streams.printDistinct();

        String name = "Fer";

        String name2 = name;

        name2 = "Are";

        System.out.println("Name:" + name);

        name = name2;

        name2 = "Sal";

        System.out.println("Name:" + name);
    }

    private List<String> getLongNameList(List<String> stringList) {
        return stringList.stream().filter(s->s.length()>8).collect(Collectors.toList());
    }

    private List<Integer> getEvenList(List<Integer> list) {

        return list.stream().filter((i)->i%2==0).collect(Collectors.toList());
    }

    private List<Integer> getDoubledList(List<Integer> list){
        return list.stream().map((i)->i*2).collect(Collectors.toList());
    }

    private long getCountLongNameList(List<String> stringList){
        return stringList.stream().filter(s->s.length()>8).count();
    }

    private List<String> getSortedList(List<String> stringList) {
        return stringList.stream().sorted().collect(Collectors.toList());
    }

    private List<String> getReverseSortList(List<String> stringList){
        //return stringList.stream().sorted((s1,s2)->s2.compareTo(s1)).collect(Collectors.toList());
        return stringList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    private int getMinValue(List<Integer> list){
        return list.stream().min(Comparator.naturalOrder()).get();
    }

    private int getMaxValue(List<Integer> list){
        return list.stream().max(Comparator.naturalOrder()).get();
    }

    private void printStrings(List<String> stringList){
        stringList.stream().forEach(s-> System.out.print(s +"\t"));
        System.out.println("--------------------------");
        stringList.stream().forEach(System.out::println);
    }

    private void convertListToArray(List<Integer> list){
        Integer[] array = list.stream().toArray(Integer[]::new);
        Stream.of(array).forEach(a-> System.out.print(a + "\t"));

    }

    private void createStreamFromArray(){
        String [] array = {"Fernando", "Arellano",  "Camioneta"};
        Stream<String> stream = Stream.of(array);
        stream = stream.filter(s->s.startsWith("F"));
        List<String> lista = stream.collect(Collectors.toList());
        System.out.println(lista);

    }

    private void tryMap(){
        Integer [] numeros = {11,12,13,14,15};
        Stream<Integer> stream = Stream.of(numeros);
        //integer++ would not work
        List<Integer> list = stream.map(integer-> integer+1).collect(Collectors.toList());
        System.out.println(list);
    }

    private void sortReversed(){
        Integer [] numbers = {8, 4, 11, 0,20,5};
        Stream<Integer> stream = Stream.of(numbers);
        List<Integer> list = stream.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(list);
    }

    private void biggest(){
        Integer [] numbers = {8, 4, 11, 0,20,5};
        Stream<Integer> stream = Stream.of(numbers);
        System.out.println(stream.max(Comparator.naturalOrder()).get());
    }

    private void filterNull(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Fer", 1));students.add(new Student("Fer", 2));students.add(new Student("Fer", 3));students.add(new Student("Fer", 4));
        students.add(new Student(null,5));students.add(new Student(null,0));
        students.add(null);

        System.out.println("Studenst initial:" + students.size());
        List<Student> filteredStudents = students.stream().filter(s->s!=null).filter(s->s.getName()!=null).collect(Collectors.toList());
        System.out.println(filteredStudents);
        System.out.println("Studenst Final:" + filteredStudents.size());
    }

    private void biggestRollNo(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Fer", 1));students.add(new Student("Fer", 2));students.add(new Student("Fer", 3));students.add(new Student("Fer", 4));
        students.add(new Student(null,5));students.add(new Student(null,0));
        students.add(null);

        Student student = students.stream().filter(s-> s!= null).sorted((student1, student2) -> student2.getRollNo()- student1.getRollNo()).findFirst().orElse(null);
        System.out.println(student);

        Student student2 = students.stream().filter(s-> s!= null).max(Comparator.comparing(Student::getRollNo)).get();
        System.out.println(student2);
        System.out.println("****************************");
    }

    private void toArray(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Fer", 1));students.add(new Student("Fer", 2));students.add(new Student("Fer", 3));students.add(new Student("Fer", 4));
        students.add(new Student(null,5));students.add(new Student(null,0));
        students.add(null);

        Student[] studentsArray = students.stream().toArray(Student[]::new);
        for(Student s : studentsArray){
            System.out.println(s);
        }
    }

    private void increaseAndPrintNewSalary(){
        Employee e1 = new Employee("Fer", 36, 10000);
        Employee e2 = new Employee("Fer2", 37, 20000);
        Employee e3 = new Employee("Fer3", 38, 30000);
        Employee e4 = new Employee("Fer4", 39, 40000);
        List<Employee> employees = new ArrayList<>();
        Employee[] employeeArray = {e1,e2,e3,e4};
        Stream<Employee> stream = Stream.of(employeeArray);
        List<Employee> updatedEmployees = stream.peek(e->e.increateSalaryBy(5000)).peek(e-> System.out.println(e)).collect(Collectors.toList());
        System.out.println(updatedEmployees);

    }

    private void printEvenNumbersSkippingFirst3AndLimitingTo10(){
        Integer[] numbers = {2,3,4,5,6,7,8,9,0,11,12,13,14,15,16,17,18,20,22,25};
        Stream<Integer> stream = Stream.of(numbers);
        stream.filter(i->i%2==0).skip(3).limit(5).forEach(System.out::println);
    }

private void printDistinct(){
    Integer[] numbers = {2,2,2,3,3,5,5,16,17,18,20,22,25};
    Stream<Integer> stream = Stream.of(numbers);
    List<Integer> list = stream.distinct().collect(Collectors.toList());
    System.out.println(list);
}

}
