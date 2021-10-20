package com.heloo.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class Game2 extends AppCompatActivity {
    TextView score,life,time, Q,TotalQ;
    Button Ok,nextQ;
    EditText Ans;
    Random random = new Random();
    int num1,num2,usersAnswer;
    int Sum;
    int usersScore=0;
    int Life=3;
    int l = 0;
    CountDownTimer timer;
    private static  final long START_TIMER = 60000;
    Boolean timer_running;
    long time_left = START_TIMER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        score = findViewById(R.id.score);
        life = findViewById(R.id.life);
        time = findViewById(R.id.time);
        Ans = findViewById(R.id.Ans);
        Ok = findViewById(R.id.Ok);
        nextQ = findViewById(R.id.nextQ);
        Q = findViewById(R.id.Q);
        TotalQ = findViewById(R.id.TotalQ);

        gameContinue();

        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersAnswer = Integer.valueOf(Ans.getText().toString());
                pauseTime();
                if(Integer.valueOf(usersAnswer) == null){
                    Q.setText("Your Answer Is Correct");
                }

                if(usersAnswer == Sum){
                    Q.setText("Your Answer Is Correct");
                    usersScore = usersScore+1;
                    score.setText(""+usersScore);
                }else {
                    Q.setText("Your Answer Is Wrong The Answer Is:"+Sum);
                    Life = Life-1;
                    life.setText(""+Life);
                }

                if(Life <= 0){
                    startActivity(new Intent(getApplicationContext(),Last.class));
                    getIntent().putExtra("socre",usersScore);
                    finish();
                }

                l= l+1;
                TotalQ.setText("Total Question Ans:-"+l);
            }
        });
        nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ans.setText("");
                gameContinue();
                resetTimer();
            }
        });
    }
    public void gameContinue(){
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        Sum = num1-num2;
        Q.setText(num1+"-"+num2);
        cont();
    }
    public void cont(){
        timer = new CountDownTimer(time_left,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left = millisUntilFinished;
                updateText();
            }
            @Override
            public void onFinish() {
                timer_running = false;
                pauseTime();
                updateText();
                resetTimer();
                Life = Life-1;
                life.setText(""+Life);
                Q.setText("Time Up");
            }
        }.start();
        timer_running =true;
    }

    public void pauseTime() {
        timer.cancel();
        timer_running =false;
    }
    public void updateText() {
        int sec =(int)(time_left/1000)%60;
        String time_lef = String.format(Locale.getDefault(),"%02d",sec);
        time.setText(time_lef);
    }
    public void resetTimer() {
        time_left = START_TIMER;
        updateText();
    }
}