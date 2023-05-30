package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {
    View screen1;
    EditText phone1,pass1;
    Button buttonEnter, btnReg1;
    TextView tvEnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        screen1 = findViewById(R.id.screen1);
        phone1 = findViewById(R.id.phone1);
        pass1 = findViewById(R.id.pass1);
        buttonEnter = findViewById(R.id.buttonenter);
        btnReg1 = findViewById(R.id.button_registration1);
        tvEnter = findViewById(R.id.tv_enter);
        buttonEnter.setOnClickListener((v) ->{
            Intent intent = new Intent(StartActivity.this, ParentProfileActivity.class);
            startActivity(intent);
        });
        btnReg1.setOnClickListener((v) ->{
            Intent intent = new Intent(StartActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}