package com.example.tumbaburros.exercizes.immutable;

import java.util.*;

// Problem: Create an immutable Person class with a mutable field (e.g., Date/List) handled safely.

//Explanation:

//Use immutable types (Instant) and defensive copies for mutable inputs.
//Expose unmodifiable views to prevent external mutation.
//Mark class final and omit setters.

public final class Person {
    private final String name;
    private final Date date;
    private final List<String> roles;

    public Person(String name, Date date, List<String> roles) {
        this.name = name;
        this.date = new Date(date.getTime());
        this.roles = Collections.unmodifiableList(new ArrayList<>(roles));
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getRoles() {
        return roles;
    }
}
