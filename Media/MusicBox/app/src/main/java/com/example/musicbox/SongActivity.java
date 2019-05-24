package com.example.musicbox;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.musicbox.models.Song;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView songImage;
    private android.widget.TextView songName, songArtist, startTimer, endTimer;
    private int audioAsset;
    private SeekBar mSeekBar;

    private Media media;
    public static final String TAG = "MainActivity";

//    Controls
    private Button prevButton, playButton, nextButton;

    private int currentAudio;

    private Song song;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        media = new Media(this);
        thread = new Thread();

        initializeUi();

        getData();

        media.setSong(audioAsset);

        media.getMediaPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                musicComplete();
            }
        });


    }

    private void getData() {
        Bundle extras = getIntent().getExtras();

        if (extras != null && extras.get("song") != null && extras.get("position") != null) {
            song = (Song) extras.get("song");

            songImage.setImageResource(song.getImageAsset());
            songName.setText(song.getName());
            songArtist.setText(song.getArtist());
            startTimer.setText("0:00");
            endTimer.setText(song.getDuration());

            audioAsset = song.getAudioAsset();
            currentAudio = (int) extras.get("position");

        }
    }

    private void initializeUi() {
        songImage = findViewById(R.id.songImage);
        songName = findViewById(R.id.songName);
        songArtist = findViewById(R.id.songArtist);
        startTimer = findViewById(R.id.startTimer);
        endTimer = findViewById(R.id.endTimer);

        prevButton = findViewById(R.id.prevButtonId);
        playButton = findViewById(R.id.playButtonId);
        nextButton = findViewById(R.id.nextButtonId);

        prevButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        mSeekBar = findViewById(R.id.songSeekbar);


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    media.getMediaPlayer().seekTo(progress);
                }

                int currentPosition = media.getCurrentPosition();

                startTimer.setText(media.format(currentPosition));
                endTimer.setText(media.format(media.getDuration() - media.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prevButtonId: {
                prev();
            }
            break;
            case R.id.playButtonId: {
                if (media.isPlaying()) {
                    pause();
                } else {
                    play();
                }
            }
            break;
            case R.id.nextButtonId: {
                next();
            }
            break;
        }
    }

    private void prev() {

        thread.interrupt();

        pause();
        media.getMediaPlayer().stop();
        media.getMediaPlayer().release();
        media.setPrev(currentAudio);
        mSeekBar.setProgress(0);
        re_render();
    }

    private void next() {

        thread.interrupt();

        pause();
        media.getMediaPlayer().stop();
        media.getMediaPlayer().release();
        media.setNext(currentAudio);
        mSeekBar.setProgress(0);
        re_render();
    }

    private void re_render() {

        if (!(currentAudio == media.songs.size()-1)) {
            currentAudio++;
        }

        song = media.songs.get(currentAudio);

        songImage.setImageResource(song.getImageAsset());
        songName.setText(song.getName());
        songArtist.setText(song.getArtist());
        startTimer.setText("0:00");
        endTimer.setText(song.getDuration());

        audioAsset = song.getAudioAsset();
    }

    private void play() {
        if (media.getMediaPlayer() != null) {
            media.play();
            playButton.setBackgroundResource(android.R.drawable.ic_media_pause);

            Log.i(TAG, "play: called!");
        }
        
        updateThread();
    }

    private void pause() {
        if (media.getMediaPlayer() != null) {
            media.pause();
            playButton.setBackgroundResource(android.R.drawable.ic_media_play);

            Log.i(TAG, "pause: called!");
        }
    }

    private void updateThread() {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    while (media.getMediaPlayer() != null && media.isPlaying()) {
                        Thread.sleep(50);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int position = media.getCurrentPosition();
                                int max = media.getDuration();

                                mSeekBar.setMax(max);
                                mSeekBar.setProgress(position);

                                startTimer.setText(media.format(position));
                                endTimer.setText(media.format(max-position));

                            }
                        });
                    }
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    private void musicComplete(){
        Log.i(TAG, "musicComplete: called!");
        startTimer.setText("0:00");
        endTimer.setText(media.format(media.getDuration()));
        playButton.setBackgroundResource(android.R.drawable.ic_media_play);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (media.isPlaying() && media != null) {
            media.getMediaPlayer().stop();
            media.getMediaPlayer().release();
            media.nullify();
            media = null;

        }

        thread.interrupt();
        thread = null;
    }
}
