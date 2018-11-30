package com.hanseltritama.chesstimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);

        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisecondsUntilDone) {
                NumberFormat f = new DecimalFormat("00");
                long min = (millisecondsUntilDone/60000) % 60;
                long sec = (millisecondsUntilDone/1000) % 60;
                textView.setText(f.format(min) + ":" + f.format(sec));
            }

            public void onFinish() {
                Log.i("Done!", "Countdown is finished!");
            }

        }.start();

        countDownTimer.start();
    }
}
