package com.example.tumbaburros.refresher;

import com.example.tumbaburros.java8.Student;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("Alice", 101, 5, "Cervantes"));
        studentList.add(new Student("Bob", 102, 9,"Cervantes"));
        studentList.add(new Student("Charlie", 103, 5,"Cervantes"));
        studentList.add(new Student("David", 104, 3,"Cervantes"));
        studentList.add(new Student("Eve", 105, 5, "Fray Pedro"));
        studentList.add(new Student("Frank", 106, 4,"Fray Pedro"));
        studentList.add(new Student("Grace", 107, 10,"Fray Pedro"));
        studentList.add(new Student("Hannah", 108, 5, "Prepa 7"));
        studentList.add(new Student("Ivy", 109, 5, "Prepa 7"));
        studentList.add(new Student("Jack", 110, 5, "Prepa 7"));
        return studentList;
    }

    public static void generateLine(){
        System.out.println("------------------------------------------------------------------------------");
    }
}
