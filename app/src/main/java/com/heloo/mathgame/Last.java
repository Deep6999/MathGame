package com.heloo.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.FitWindowsViewGroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Last extends AppCompatActivity {

    TextView TX,sor;
    Button Exit,PlayAgain;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        TX = findViewById(R.id.TX);
        Exit = findViewById(R.id.Exit);
        PlayAgain = findViewById(R.id.PlayAgain);
        sor = findViewById(R.id.sor);

         Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        sor.setText(""+score);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}