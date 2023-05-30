package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationParentActivity extends AppCompatActivity {
    View screen3;
    TextView tvReg;
    EditText phone2, pass2;
    Button btnReg, btnBack2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_parent);
        screen3 = findViewById(R.id.screen3);
        tvReg = findViewById(R.id.tv_registration_parent);
        phone2 = findViewById(R.id.phone2);
        pass2 = findViewById(R.id.pass2);
        btnReg = findViewById(R.id.button_registration2);
        btnBack2 = findViewById(R.id.btnback2);
        btnReg.setOnClickListener((v) ->{
            Intent intent = new Intent(RegistrationParentActivity.this, ParentProfileActivity.class);
            startActivity(intent);
        });
        btnBack2.setOnClickListener((v) ->{
            Intent intent = new Intent(RegistrationParentActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}