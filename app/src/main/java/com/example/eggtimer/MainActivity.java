package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerView;
    SeekBar seekBar;
    boolean counterIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;

    public void restTimer(){
        timerView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsActive=false;
//        mediaPlayer.reset();

    }

    public void buttonClicked (View view){
        if(counterIsActive){
          restTimer();
        } else {

            counterIsActive = true;
            seekBar.setEnabled(false);
            goButton.setText("STOP!");

            countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alram);
                    mediaPlayer.start();
                    restTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int minutes= secondsLeft/60;
        int seconds=secondsLeft-(minutes*60);

        String secondStirng =Integer.toString(seconds);
        if(seconds<=9){
            secondStirng="0"+secondStirng;
        }

        timerView.setText(Integer.toString(minutes)+":"+secondStirng);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= findViewById(R.id.seekBar);
        timerView= findViewById(R.id.timerView);
        goButton= findViewById(R.id.goButton);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}