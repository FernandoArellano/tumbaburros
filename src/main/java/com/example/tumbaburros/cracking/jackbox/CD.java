package com.example.tumbaburros.cracking.jackbox;

import java.util.List;

public class CD {

    private int id;
    private String name;
    private List<Song> songs;
    private String artist;


    public CD(int id, String name, List<Song> songs, String artist) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
