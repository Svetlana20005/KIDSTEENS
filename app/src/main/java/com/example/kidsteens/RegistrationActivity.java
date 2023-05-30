package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    View screen2;
    TextView tvEnter;
    Button buttonParent, buttonChild, btnBack1;
    CardView cardView1, cardView2;
    ImageView imageView1, imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        screen2 = findViewById(R.id.screen2);
        tvEnter = findViewById(R.id.tv_enter);
        cardView1 = findViewById(R.id.cardview1);
        cardView2 = findViewById(R.id.cardview2);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        buttonParent = findViewById(R.id.buttonparent);
        buttonChild = findViewById(R.id.buttonchild);
        btnBack1 = findViewById(R.id.btnback1);
        buttonParent.setOnClickListener((v) ->{
            Intent intent = new Intent(RegistrationActivity.this, RegistrationParentActivity.class);
            startActivity(intent);
        });
        btnBack1.setOnClickListener((v) ->{
            Intent intent = new Intent(RegistrationActivity.this, StartActivity.class);
            startActivity(intent);
        });
    }
}