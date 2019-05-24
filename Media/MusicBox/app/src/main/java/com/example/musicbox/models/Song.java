package com.example.musicbox.models;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Song implements Serializable {

    private String name, artist, duration;
    private int audioAsset, imageAsset;

    public Song(String name, String artist, String duration, int audioAsset, int imageAsset) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.audioAsset = audioAsset;
        this.imageAsset = imageAsset;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    public int getAudioAsset() {
        return audioAsset;
    }

    public int getImageAsset() {
        return imageAsset;
    }
}
