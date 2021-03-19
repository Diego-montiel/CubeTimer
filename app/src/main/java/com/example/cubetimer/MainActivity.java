package com.example.cubetimer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- //
    int random;
    String scramble = "";
    int clicks = 0;
    Button startStop;
    TextView scrambleTv;
    ListView timeList;
    DataBaseHandler dbh;
    ArrayList<HashMap<String, String>> timesList;
    String[] movements = {"U", "L", "F", "R", "B", "D", "U'", "L'", "F'", "R'", "B'", "D'", "U2", "L2", "F2", "R2", "B2", "D2"};
    Thread thread;
    int min = 0, seg = 0, mil = 0;
    Handler h = new Handler();
    boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startStop = findViewById(R.id.button);
        scrambleTv = findViewById(R.id.scrambleTV);
        scramble();
        startStop.setOnClickListener(this);
        thread = new Thread(() -> {
            while (true) {
                if (isOn) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mil++;
                    if (mil == 668) {
                        seg++;
                        mil = 0;
                    }
                    if (seg == 60) {
                        min++;
                        seg = 0;
                    }
                    h.post(() -> {
                        String m, s, mi, result = "";
                        if (mil < 10) {
                            m = "00" + mil;
                        } else if (mil < 100) {
                            m = "0" + mil;
                        } else {
                            m = "" + mil;
                        }
                        if (seg < 10) {
                            s = "0" + seg;
                        } else {
                            s = "" + seg;
                        }
                        if (min < 10) {
                            mi = "0" + min;
                        } else {
                            mi = "" + min;
                        }
                        startStop.setText(result.concat(mi + ":" + s + ":" + m));
                    });

                }
            }
        });
        thread.start();

    }

    @Override
    public void onClick(View v) {
        if (clicks == 0) {
            isOn = true;
            clicks++;
        } else if (clicks == 1) {
            isOn = false;
            clicks++;
        } else if (clicks == 2) {
            String startStopText = "00:00:000";
            startStop.setText(startStopText);
            min = 0;
            seg = 0;
            mil = 0;
            scramble();
            clicks = 0;
        }
    }

    void scramble() {
        scramble = "";
        for (int i = 0; i < 20; i++) {
            random = (int) (Math.random() * (17 + 1) + 0);
            scramble += "  " + movements[random];
        }
        scrambleTv.setText(scramble);

    }

    void refreshTable() {
        dbh = new DataBaseHandler(MainActivity.this);
        timesList = dbh.getTimes();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, timesList, R.layout.list_time, new String[]{"time", "scramble", "date"}, new int[]{R.id.time, R.id.scramble, R.id.date});
        timeList.setAdapter(adapter);
        dbh.close();
    }
}