package com.example.helloworld.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageView imgPlay, imgNext;
    private TextView tvSongName, tvDuration;
    private ArrayList<Integer> songlist = new ArrayList<>();
    private ArrayList<String>songNameList = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private Handler songDurationHandler = new Handler();
    private Random random = new Random();
    private int currentSong=0;
    private double startTime=0;

    private Runnable songDurationRunnable = new Runnable() {
        @Override
        public void run() {
            startTime=mediaPlayer.getCurrentPosition();
            tvDuration.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            songDurationHandler.postDelayed(this,200);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        initSongLists();
        initMediaPlayer();
        findViews();
        setListeners();
        songDurationHandler.postDelayed(songDurationRunnable,200);
    }

    private void initSongLists(){
        songlist.add(R.raw.song_1);
        songNameList.add("Lion King Sleep Tonight");
        songlist.add(R.raw.song_2);
        songNameList.add("Relax Piano");
        songlist.add(R.raw.song_3);
        songNameList.add("Ghost Choir");
        songlist.add(R.raw.song_4);
        songNameList.add("Ghost Duet");
    }

    private void initMediaPlayer(){
        mediaPlayer=MediaPlayer.create(this,songlist.get(0));
    }

    private void findViews(){
        imgPlay=findViewById(R.id.imgPlay);
        imgNext=findViewById(R.id.imgNext);
        tvSongName=findViewById(R.id.songName);
        tvDuration=findViewById(R.id.songDuration);

        tvSongName.setText(songNameList.get(0));
    }

    private void setListeners(){
        imgPlay.setOnClickListener(view->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                imgPlay.setImageResource(R.drawable.img_play);
            }else{
                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.img_pause);
            }
        });

        imgNext.setOnClickListener(view->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                imgPlay.setImageResource(R.drawable.img_play);
            }

            currentSong=random.nextInt(10) % 4;
            int nextSong = songlist.get(currentSong);
            tvSongName.setText(songNameList.get(currentSong));

            mediaPlayer=MediaPlayer.create(MusicPlayerActivity.this,nextSong);
            mediaPlayer.start();
            imgPlay.setImageResource(R.drawable.img_pause);
        });
    }
}