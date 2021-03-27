package com.example.cubetimer;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[][] movements = {{"U", "U'", "U2"}, {"D", "D'", "D2"}, {"L", "L'", "L2"}, {"R", "R'", "R2"}, {"F", "F'", "F2"}, {"B", "B'", "B2"}};
    Random generator = new Random();
    int previusNumber = 0;
    int random;
    String scramble = "";
    int clicks = 0;
    Button startStop;
    TextView scrambleTv;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    int minutes = 0, seconds = 0, miliSeconds = 0;
    Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startStop = findViewById(R.id.button);
        scrambleTv = findViewById(R.id.scrambleTV);
        scramble();
        handler = new Handler();
        startStop.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (clicks == 0) {
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            clicks++;
        } else if (clicks == 1) {
            TimeBuff += MillisecondTime;
            handler.removeCallbacks(runnable);
            clicks++;
        } else if (clicks == 2) {
            String startStopText = "00:00:000";
            startStop.setText(startStopText);
            MillisecondTime = 0L ;
            StartTime = 0L ;
            TimeBuff = 0L ;
            UpdateTime = 0L ;
            seconds = 0 ;
            minutes = 0 ;
            miliSeconds = 0 ;
            scramble();
            clicks = 0;
        }
    }

    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            seconds = (int) (UpdateTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;
            miliSeconds = (int) (UpdateTime % 1000);
            startStop.setText(new StringBuilder().append(String.format("%02d", minutes)).append(":").append(String.format("%02d", seconds)).append(":").append(String.format("%03d", miliSeconds)).toString());
            handler.postDelayed(this, 0);
        }
    };

    void scramble() {
        scramble = "";
        for (int i = 0; i < 20; i++) {
            random = generator.nextInt(6);
            while (previusNumber == random) {
                random = generator.nextInt(6);
            }
            scramble += "  " + movements[random][generator.nextInt(3)];
            previusNumber = random;
        }
        scrambleTv.setText(scramble);
    }

}