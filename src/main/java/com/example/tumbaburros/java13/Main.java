package com.example.tumbaburros.java13;

public class Main {

    public static void textBlocks(){
        System.out.println("TextBlocks");
        String block1 = """
                         Fer
                            Are
                            Sal
                         """;

        String block2 = """
                <html>
                    <body>Fer</body>
                </html>
                            
                """;
        System.out.println(block1);
        System.out.println(block2);
    }

    public static void main(String[] args) {
        textBlocks();
    }
}
