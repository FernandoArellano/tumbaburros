package com.example.tumbaburros.cracking.jackbox;

public class Song {
    private String name;
    private long duration;
    private String singer;
    private CD cd;
    private long id;

    public Song(String name, long duration, String singer, CD cd, long id) {
        this.name = name;
        this.duration = duration;
        this.singer = singer;
        this.cd = cd;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
