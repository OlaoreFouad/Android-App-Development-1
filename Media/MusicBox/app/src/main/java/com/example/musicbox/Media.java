package com.example.musicbox;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.musicbox.adapters.SongAdapter;
import com.example.musicbox.models.Song;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Media {

    private Context context;

    private MediaPlayer mediaPlayer;
    private SimpleDateFormat dateFormat;
    public List<Song> songs;

    public Media(Context context) {
        this.context = context;
        dateFormat = new SimpleDateFormat("mm:ss");
        songs = SongAdapter.getSongs();

        mediaPlayer = new MediaPlayer();
    }

    public String getDuration(int resId) {
        mediaPlayer = MediaPlayer.create(context, resId);
        String duration = dateFormat.format(new Date(mediaPlayer.getDuration()));

        mediaPlayer.release();
        mediaPlayer = null;
        return duration;
    }

    public void play() {
        mediaPlayer.start();
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public String format(int millis) {
        return dateFormat.format(new Date(millis));
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public void setSong(int resId) {
        mediaPlayer = MediaPlayer.create(context, resId);
    }

    public void setNext(int currentAudio) {
        Log.i(SongActivity.TAG, "setNext: " + currentAudio);
        Log.i(SongActivity.TAG, "setNext: " + songs.size());

        int resId = currentAudio == songs.size()-1 ? songs.get(currentAudio-2).getAudioAsset() : songs.get(currentAudio+1).getAudioAsset();

        setSong(resId);
    }

    public void setPrev(int currentAudio) {
        int resId = currentAudio == 0 ? songs.get(0).getAudioAsset() : songs.get(currentAudio-1).getAudioAsset();

        setSong(resId);
    }

    public void nullify() {
        this.mediaPlayer = null;
    }





}
