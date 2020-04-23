 package edu.learn.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

 public class MainActivity extends AppCompatActivity {

     AudioManager audioManager;

     MediaPlayer mediaPlayer;


     public void playSound(View view) {

         mediaPlayer.start();
     }

     public void pauseSound(View view) {

         mediaPlayer.pause();
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //getmax volume of the device.

        int currVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); // getscurrent volume of the stream.

        mediaPlayer = MediaPlayer.create(this, R.raw.dus_bahane);

        // Volume Seek
        final SeekBar volumeSeek = findViewById(R.id.volSeek);

        volumeSeek.setMax(maxVolume); // set seekbar to the max volume of the device.
        volumeSeek.setProgress(currVol); // set seekbar to the current level of volume..

        volumeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
        //Play Seek
        final SeekBar playSeek = findViewById(R.id.playSeek);
        playSeek.setMax(mediaPlayer.getDuration());

        playSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                playSeek.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,2000);


        }
    }
