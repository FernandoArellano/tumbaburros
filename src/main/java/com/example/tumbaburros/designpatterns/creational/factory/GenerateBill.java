package com.example.tumbaburros.designpatterns.creational.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerateBill {

    public static void main(String[] args) throws IOException {
        PlanFactory factory = new PlanFactory();

        System.out.println("Type of Plan, enter one of options:");
        System.out.println("Domestic");
        System.out.println("Commercial");
        System.out.println("Institucional");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String planType = reader.readLine();

        System.out.println("Ingresa las unidades");

        int units = Integer.parseInt(reader.readLine());

        Plan plan = factory.getPlan(planType);

        System.out.println("Rate: " + plan.getRate());
        System.out.println("Bill amount for " + units + " units: " + plan.calculateBill(units));

    }
}
