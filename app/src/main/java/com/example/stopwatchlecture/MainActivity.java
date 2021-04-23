package com.example.stopwatchlecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//variable
    private int seconds=0;  //basic about job of app
    private boolean running = false;  //flag running

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkInstance(savedInstanceState);
        runTimer();

    }

    private void checkInstance(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            seconds=savedInstanceState.getInt("SECONDS");
            running=savedInstanceState.getBoolean("RUNNING");
        }
    }

    //method onSaveInstanceState save data and call  before when the user change orientation app
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SECONDS",seconds);
        outState.putBoolean("RUNNING",running);
    }

    //method run timer for another thread that its counter value will increase if the flag is True
    private void runTimer() {
        final TextView textTime=findViewById(R.id.textTime);   //very important to use final
        final Handler handler=new Handler();       //first step to create another thread
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(running){
                    seconds++;

                int hours= seconds/3600;
                int minutes= (seconds%3600) /60;
                int secs= seconds %60;
                String time =hours + " : " + minutes + " : " + secs;
                textTime.setText(time);
                }  // end about if statement Put here to solve the problem of not entering the calculation in the second Tharid in case the value is false
                handler.postDelayed(this , 1000);   //Repetition thread2 every second --> 1000 millis (same the loop)
            }
        });
    }

    //eventHand btnStart
    public void btnStartOnClick(View view) {
        running=true;
    }

    //eventHand btnStop
    public void btnStopOnClick(View view) {
        running=false;
    }

    //eventHand btnReset
    public void btnResetOnClick(View view) {
        running=false;
        seconds=0;
    }
}