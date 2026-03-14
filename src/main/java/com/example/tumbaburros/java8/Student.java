package com.example.tumbaburros.java8;

public class Student {

    private String name;
    private int rollNo;
    private int grade;
    private String school;

    public Student(String name, int rollNo, int grade, String school) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
        this.school = school;
    }

    public Student(String name, int rollNo, int grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = -1;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNo=" + rollNo +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                '}';
    }
}
