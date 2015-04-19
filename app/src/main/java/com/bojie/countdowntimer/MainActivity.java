package com.bojie.countdowntimer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

    Button btnStart, btnStop;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        tvTime = (TextView) findViewById(R.id.tvTime);

        tvTime.setText("00:03:00");
        final CounterClass timer = new CounterClass(180000,1000);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });
    }

   public class CounterClass extends CountDownTimer{

       /**
        * @param millisInFuture    The number of millis in the future from the call
        *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
        *                          is called.
        * @param countDownInterval The interval along the way to receive
        *                          {@link #onTick(long)} callbacks.
        */
       public CounterClass(long millisInFuture, long countDownInterval) {
           super(millisInFuture, countDownInterval);
       }

       @Override
       public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
           String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                   TimeUnit.MILLISECONDS.toMinutes(millis)- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                   TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
           Log.d("hms", hms);
           tvTime.setText(hms);
       }

       @Override
       public void onFinish() {
            tvTime.setText("Completed.");
       }
   }
}
