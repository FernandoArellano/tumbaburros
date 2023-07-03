package com.example.tumbaburros.java8;

import java.util.function.Consumer;

public class FunctionalConsumer {

    public void printMovieInformation(Movie movie){
        Consumer<Movie> c = (m) -> {
            System.out.println(m);
        };
        c.accept(movie);
    }

    public static void main(String[] args) {
        Movie movie = new Movie("MobieDick", "Mobie", "Whale");
        Movie movie2 = new Movie("Pinocchio", "Pinocchio", "Hale");

        FunctionalConsumer consumer = new FunctionalConsumer();
        consumer.printMovieInformation(movie);
        consumer.printMovieInformation(movie2);
    }
}
