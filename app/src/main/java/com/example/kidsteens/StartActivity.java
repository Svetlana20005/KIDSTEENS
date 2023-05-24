package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    View screen1, backgroundPhone1,backgroundPass1;
    Button buttonEnter;
    TextView tvEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        screen1 = findViewById(R.id.screen1);
        backgroundPhone1 = findViewById(R.id.backgroundphone1);
        backgroundPass1 = findViewById(R.id.backgroundpass1);
        buttonEnter = findViewById(R.id.buttonenter);
        tvEnter = findViewById(R.id.tv_enter);
    }
}