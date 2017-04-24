package com.example.android.top10downloadapp;

/**
 * Created by Enver on 21.04.2017.
 */

/*
* Application class to
* */
public class Application {
    private String name;
    private String artist;
    private String releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n" +
                "Artist: " + this.getArtist() + "\n" +
                "Release date: " + this.getReleaseDate();
    }
}
