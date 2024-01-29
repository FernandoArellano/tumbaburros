package com.example.tumbaburros.designpatterns.structural.bridge;

public abstract class Shape {
    //Composition - implementor
    protected Color color;

    {
        System.out.println("Non Static block");
    }

    static {
        System.out.println("Static block");
    }

    //constructor with implementor as input argument
    public Shape(Color c){
        this.color=c;
    }

    abstract public void applyColor();
}