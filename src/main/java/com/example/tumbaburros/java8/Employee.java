package com.example.tumbaburros.java8;

public class Employee {
    private String name;
    private int age;
    private float salary;

    public Employee() {
    }

    public Employee(String name, int age, float salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public void increateSalaryBy(float increase){
        this.salary += increase;
    }
}
