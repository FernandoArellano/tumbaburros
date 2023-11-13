package com.example.tumbaburros.cracking.jackbox;


import java.util.LinkedList;
import java.util.Queue;

public class PlayList {

    private Queue<Song> songs;
    private Song currentSong;
    private Song previosSong = null;

    public PlayList(Song currentSong) {
        this.songs = new LinkedList<>();
        this.currentSong = currentSong;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public void queueSong(Song song){
        songs.add(song);
    }

    public Song getNextSong(){
        Song song = songs.poll();
        previosSong = song;
        return song;
    }
}
