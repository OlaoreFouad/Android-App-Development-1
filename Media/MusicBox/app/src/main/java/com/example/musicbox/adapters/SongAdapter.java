package com.example.musicbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.musicbox.R;
import com.example.musicbox.SongActivity;
import com.example.musicbox.listeners.OnSongSelectedListener;
import com.example.musicbox.models.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private Context context;
    private static List<Song> songs;
    private Intent intent;
    private OnSongSelectedListener mOnSongSelectedListener;

    public SongAdapter(Context context, List<Song> songs, OnSongSelectedListener mOnSongSelectedListener) {
        this.context = context;
        this.songs = songs;
        this.mOnSongSelectedListener = mOnSongSelectedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.song, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songs.get(i);

        viewHolder.name.setText(song.getName());
        viewHolder.artist.setText(song.getArtist());
        viewHolder.duration.setText(song.getDuration());

        viewHolder.image.setImageResource(song.getImageAsset());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static List<Song> getSongs() {
        return songs;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        android.widget.TextView name, artist, duration;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.songImageId);
            name = itemView.findViewById(R.id.songNameId);
            duration = itemView.findViewById(R.id.songDurationId);
            artist = itemView.findViewById(R.id.songArtistId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Song song = songs.get(position);
            mOnSongSelectedListener.songSelected(song, position);
        }
    }

}
