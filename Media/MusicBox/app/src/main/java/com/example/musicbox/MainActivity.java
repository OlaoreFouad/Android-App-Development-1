package com.example.musicbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.musicbox.adapters.SongAdapter;
import com.example.musicbox.listeners.OnSongSelectedListener;
import com.example.musicbox.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView songsRecyclerView;
    private SongAdapter adapter;
    private List<Song> songs;
    private Media media;
    private OnSongSelectedListener mOnSongSelectedListener;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsRecyclerView = findViewById(R.id.songsRecyclerViewId);
        media = new Media(this);

        this.mOnSongSelectedListener = new OnSongSelectedListener() {
            @Override
            public void songSelected(Song song, int position) {
                intent = new Intent(MainActivity.this, SongActivity.class);
                intent.putExtra("song", song);
                intent.putExtra("position", position);

                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        };

        initialize();
    }

    private void initialize() {
        songs = new ArrayList<>();

        songs.add(new Song("Mawlaya", "Maher Zain", media.getDuration(R.raw.mawlaya), R.raw.mawlaya, R.color.colorPrimary));
        songs.add(new Song("Forgive Me", "Zain Bhikha", media.getDuration(R.raw.forgive_me), R.raw.forgive_me, R.color.colorDarkGray));
        songs.add(new Song("My Little Girl", "Barry Jay", media.getDuration(R.raw.my_little_girl), R.raw.my_little_girl, R.color.colorPrimaryDark));
        songs.add(new Song("Number One For Me", "Olaore Fouad", media.getDuration(R.raw.number_one_for_me), R.raw.number_one_for_me, R.color.colorAccent));
        songs.add(new Song("I Love You So", "Tatiana Manaois", media.getDuration(R.raw.i_love_you_so), R.raw.i_love_you_so, R.color.colorPrimary));
        songs.add(new Song("One Big Family", "Maher Zain", media.getDuration(R.raw.one_big_family), R.raw.one_big_family, R.color.colorAccent));

        songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songsRecyclerView.setHasFixedSize(true);

        adapter = new SongAdapter(this, songs, this.mOnSongSelectedListener);
        songsRecyclerView.setAdapter(adapter);

    }
}
