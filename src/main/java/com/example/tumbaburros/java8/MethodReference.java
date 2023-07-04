package com.example.tumbaburros.java8;

public class MethodReference {

    public static void m2(){
        System.out.println("Method Reference");
    }

    public void m3(){
        System.out.println("Instance method reference");
    }

    public static void main(String[] args) {
        MethodReferenceInterf i = MethodReference::m2;
        i.m1();

        MethodReference methodReference = new MethodReference();
        MethodReferenceInterf i2 = methodReference::m3;
        i2.m1();

        ConstructorReference constructorReference = () -> new Sample();
        constructorReference.get();

        ConstructorReference constructorReference2 = Sample::new;
        constructorReference2.get();
    }
}
