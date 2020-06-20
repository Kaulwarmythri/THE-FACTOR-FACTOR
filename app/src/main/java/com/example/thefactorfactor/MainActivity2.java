package com.example.thefactorfactor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    private static  final long COUNTDOWN_IN_MILLIS = 10000;
    private CountDownTimer mCountDOwnTimer;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private TextView scoreText;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private TextView countDown;
    private int score = 0;

    RandomClass obj = new RandomClass();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        countDown = findViewById(R.id.countdown);

        Intent lastActivity = getIntent();
        int number = lastActivity.getIntExtra("key", 0);
        TextView choose = findViewById(R.id.choose);
        choose.setText(MessageFormat.format("Choose the correct factor for the number{0}", number));

        obj.n = number;
        obj.getRandomValues();
        obj.getOptions();

        RadioGroup rg = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb1.setText(MessageFormat.format("{0} ", obj.optValues[0]));
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obj.answers.contains(obj.optValues[0])){
                    rb1.setBackgroundColor(Color.GREEN);
                    score++;
                    scoreText = findViewById(R.id.score);
                    scoreText.setText(MessageFormat.format("Score : {0}", score));

                    Intent activity3 = new Intent(MainActivity2.this, MainActivity3.class);
                    Log.d(TAG, "onClick: Displaying");
                    startActivity(activity3);
                }else {
                    rb1.setBackgroundColor(Color.RED);
                    if (obj.answers.contains(obj.optValues[1])) {
                        rb2.setBackgroundColor(Color.GREEN);
                    } else {
                        rb3.setBackgroundColor(Color.GREEN);
                    }
                    Intent activity4 = new Intent(MainActivity2.this, MainActivity4.class);
                    Log.d(TAG, "onClick: Starting");
                    startActivity(activity4);
                }
            }
        });

        rb2 = findViewById(R.id.rb2);
        rb2.setText(MessageFormat.format("{0} ", obj.optValues[1]));
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obj.answers.contains(obj.optValues[1])){
                    rb2.setBackgroundColor(Color.GREEN);
                    score++;
                    scoreText = findViewById(R.id.score);
                    scoreText.setText(MessageFormat.format("Score : {0}", score));

                    Intent activity3 = new Intent(MainActivity2.this, MainActivity3.class);
                    Log.d(TAG, "onClick: Displaying");
                    startActivity(activity3);
                }else {
                    rb2.setBackgroundColor(Color.RED);
                    if (obj.answers.contains(obj.optValues[0])) {
                        rb1.setBackgroundColor(Color.GREEN);
                    } else {
                        rb3.setBackgroundColor(Color.GREEN);
                    }
                    Intent activity4 = new Intent(MainActivity2.this, MainActivity4.class);
                    Log.d(TAG, "onClick: Displaying");
                    startActivity(activity4);
                }
            }
        });

        rb3 = findViewById(R.id.rb3);
        rb3.setText(MessageFormat.format("{0} ", obj.optValues[2]));
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obj.answers.contains(obj.optValues[0])){
                    rb3.setBackgroundColor(Color.GREEN);
                    score++;
                    scoreText = findViewById(R.id.score);
                    scoreText.setText(MessageFormat.format("Score : {0}", score));

                    Intent activity3 = new Intent(MainActivity2.this, MainActivity3.class);
                    Log.d(TAG, "onClick: Displaying");
                    startActivity(activity3);
                }else {
                    rb3.setBackgroundColor(Color.RED);
                    if (obj.answers.contains(obj.optValues[0])) {
                        rb1.setBackgroundColor(Color.GREEN);
                    } else {
                        rb2.setBackgroundColor(Color.GREEN);
                    }
                    Intent activity4 = new Intent(MainActivity2.this, MainActivity4.class);
                    startActivity(activity4);
                }
            }
        });

        mTimeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

    }

    private void startCountDown(){
        mCountDOwnTimer = new CountDownTimer(mTimeLeftInMillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mTimeLeftInMillis = 0;
                checkAnswer();
                updateCountDownText();

            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis/1000)/60;
        int seconds = (int) (mTimeLeftInMillis/1000)%60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDown = findViewById(R.id.countdown);
        countDown.setText(timeFormatted);
    }

    private void checkAnswer(){
        mCountDOwnTimer.cancel();
        if (obj.answers.contains(obj.optValues[0])){
            rb1.setBackgroundColor(Color.GREEN);
        }else if (obj.answers.contains(obj.optValues[1])){
            rb2.setBackgroundColor(Color.GREEN);
        }else {
            rb3.setBackgroundColor(Color.GREEN);
        }

        if (mTimeLeftInMillis <= 3000){
            countDown.setTextColor(Color.RED);
        }else{
            countDown.setTextColor(Color.GREEN);
        }
        Intent lastActivity = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(lastActivity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDOwnTimer != null){
            mCountDOwnTimer.cancel();
        }
    }
}
