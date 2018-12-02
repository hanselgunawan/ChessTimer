package com.hanseltritama.chesstimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    Button no_btn;
    Button yes_btn;
    TextView textView;
    TextView textView2;
    CountDownTimer countDownTimer;
    CountDownTimer countDownTimer2;
    View chess_time_layout;
    View time_list_layout;
    View try_again_layout;
    int clickedPosition = 0;
    long endTime1;
    long endTime2;
    long[] time_list_arr = {1, 2, 3};

    public void onBtnClick(View view) {
        if(view.getId() == R.id.button1) {
            countDownTimer2.start();
            countDownTimer.cancel();
            button.setEnabled(false);
            button2.setEnabled(true);
        } else {
            countDownTimer.start();
            countDownTimer2.cancel();
            button.setEnabled(true);
            button2.setEnabled(false);
        }
    }

    public void displayTimesUp() {
        chess_time_layout.setVisibility(View.INVISIBLE);
        time_list_layout.setVisibility(View.INVISIBLE);
        try_again_layout.setVisibility(View.VISIBLE);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTimeOptions();
            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(time_list_arr[clickedPosition]);
            }
        });
    }

    public void setTime(long endTime) {
        long end_time_millis = endTime * 60000;
        endTime1 = end_time_millis;
        endTime2 = end_time_millis;
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        countDownTimer = new CountDownTimer(endTime1, 1000) {

            public void onTick(long millisecondsUntilDone) {
                NumberFormat f = new DecimalFormat("00");
                millisecondsUntilDone = endTime1;
                long min = (millisecondsUntilDone/60000) % 60;
                long sec = (millisecondsUntilDone/1000) % 60;
                textView.setText(f.format(min) + ":" + f.format(sec));
                endTime1 -= 1000;
            }

            public void onFinish() {

                textView.setText("00:00");
                displayTimesUp();
            }
        };

        countDownTimer2 = new CountDownTimer(endTime2, 1000) {

            public void onTick(long millisecondsUntilDone) {
                NumberFormat f = new DecimalFormat("00");
                millisecondsUntilDone = endTime2;
                long min = (millisecondsUntilDone/60000) % 60;
                long sec = (millisecondsUntilDone/1000) % 60;
                textView2.setText(f.format(min) + ":" + f.format(sec));
                endTime2 -= 1000;
            }

            public void onFinish() {

                textView2.setText("00:00");
                displayTimesUp();
            }
        };

        chess_time_layout.setVisibility(View.VISIBLE);
        try_again_layout.setVisibility(View.INVISIBLE);
        time_list_layout.setVisibility(View.INVISIBLE);
        countDownTimer.start();
        NumberFormat f = new DecimalFormat("00");
        long min = (end_time_millis/60000) % 60;
        long sec = (end_time_millis/1000) % 60;
        textView2.setText(f.format(min) + ":" + f.format(sec));
        button.setEnabled(true);
        button2.setEnabled(false);
    }

    public void displayTimeOptions() {
        ListView time_listview = time_list_layout.findViewById(R.id.time_listview);
        chess_time_layout.setVisibility(View.INVISIBLE);
        try_again_layout.setVisibility(View.INVISIBLE);
        time_list_layout.setVisibility(View.VISIBLE);
        final ArrayList<String> time_list_arraylist = new ArrayList<String>();

        for(int i = 0; i < time_list_arr.length; i++) {
            if(i == 0) time_list_arraylist.add(time_list_arr[i] + " Minute");
            else time_list_arraylist.add(time_list_arr[i] + " Minutes");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, time_list_arraylist);
        time_listview.setAdapter(arrayAdapter);

        time_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedPosition = i;
                setTime(time_list_arr[i]);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chess_time_layout = findViewById(R.id.chess_time_layout);
        time_list_layout = findViewById(R.id.time_list_layout);
        try_again_layout = findViewById(R.id.try_again_layout);
        yes_btn = try_again_layout.findViewById(R.id.yesBtn);
        no_btn = try_again_layout.findViewById(R.id.noBtn);
        displayTimeOptions();
    }
}
