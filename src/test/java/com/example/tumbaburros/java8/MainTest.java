package com.example.tumbaburros.java8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main main = new Main();
    @Test
    void max() {
        assertEquals(6, main.max10());
    }
}