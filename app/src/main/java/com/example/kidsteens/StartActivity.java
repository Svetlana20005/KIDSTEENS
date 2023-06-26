package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kidsteens.server.LoginData;
import com.example.kidsteens.server.RetrofitHelper;

public class StartActivity extends AppCompatActivity {
    View screen1;
    EditText phone1,pass1;
    Button buttonEnter, btnReg1;
    TextView tvEnter;
    RetrofitHelper helper;
    LoginData loginData;
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
        loginData = new LoginData();
        loginData.setPhone(phone1.getText().toString());
        loginData.setCode(pass1.getText().toString());
        buttonEnter.setOnClickListener((v) ->{
            helper.login((result)->{

                Intent intent = new Intent(StartActivity.this, ParentProfileActivity.class);
                startActivity(intent);
            }, loginData);
        });
        btnReg1.setOnClickListener((v) ->{
            Intent intent = new Intent(StartActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}