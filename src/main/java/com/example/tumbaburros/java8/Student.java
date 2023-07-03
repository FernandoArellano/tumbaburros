package com.example.tumbaburros.java8;

public class Student {

    private String name;
    private int rollNo;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNo=" + rollNo +
                '}';
    }

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
}
