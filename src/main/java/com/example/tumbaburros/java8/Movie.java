package com.example.tumbaburros.java8;

public class Movie {

    private String name;
    private String hero;
    private String heroine;

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", hero='" + hero + '\'' +
                ", heroine='" + heroine + '\'' +
                '}';
    }

    public Movie(String name, String hero, String heroine) {
        this.name = name;
        this.hero = hero;
        this.heroine = heroine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getHeroine() {
        return heroine;
    }

    public void setHeroine(String heroine) {
        this.heroine = heroine;
    }
}
