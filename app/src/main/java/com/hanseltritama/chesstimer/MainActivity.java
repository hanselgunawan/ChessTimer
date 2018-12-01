package com.hanseltritama.chesstimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    TextView textView;
    TextView textView2;
    CountDownTimer countDownTimer;
    CountDownTimer countDownTimer2;
    long endTime1 = 120000;
    long endTime2 = 120000;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            }
        };

        countDownTimer.start();
        button2.setEnabled(false);
    }
}
