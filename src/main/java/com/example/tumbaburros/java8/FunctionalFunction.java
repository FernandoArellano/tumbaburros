package com.example.tumbaburros.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalFunction {


    public static void main(String[] args) {

        Function<String, Integer> f = (s) -> s.length();
        System.out.println(f.apply("Fer"));

        Function<Integer, Integer> f2 = (i) -> i*i;
        System.out.println(f2.apply(5));

        Function<String, String> f3 = (s) -> s.replace(" ", "");
        System.out.println(f3.apply("F e  r  "));

        Function<String, Integer> f4 = (s) -> s.length()- s.replace(" ", "").length();
        System.out.println("Number of spaces");
        System.out.println(f4.apply("F e  r  "));

        Function<Integer, Character> f5 = (i) -> {
            if(i>80){
                return 'A';
            } else if(i>70){
                return 'B';
            } else {
                return 'C';
            }
        };
        System.out.println(f5.apply(90));
        System.out.println(f5.apply(70));

        Function<List<Employee>, Float> f6 = (l) -> {
            float sum=0.0f;
            for(Employee e: l){
                sum+= e.getSalary();
            }
            return sum;
        };

        Predicate<Employee> p = (e) -> e.getSalary()<50000;

        Function<List<Employee>, List<Employee>> f7 = (l) -> {
            for(Employee e: l){
                if(p.test(e)){
                    e.increateSalaryBy(150000);
                }
            }
            return l;
        };

        Employee e1 = new Employee("Fer", 20, 35554.0f);
        Employee e2 = new Employee("Fer2", 10, 15554.0f);
        Employee e3 = new Employee("Fer3", 40, 635554.0f);

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        System.out.println("Total nomina:" + f6.apply(employees));

        System.out.println("Before Salary");
        System.out.println(employees);

        System.out.println("After Increase");
        f7.apply(employees);
        System.out.println(employees);


        Function<String, String> f8 = (s)-> s.toUpperCase();
        Function<String, String> f9 = (s) -> s.substring(0,9);

        //apply 2 functions
        System.out.println(f8.andThen(f9).apply("Fernando Arellano"));
    }
}
