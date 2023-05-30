package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ParentProfileActivity extends AppCompatActivity {
    View screen4;
    TextView tvProf;
    CardView cardView;
    ImageView imageView;
    Button btnShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        screen4 = findViewById(R.id.screen4);
        tvProf = findViewById(R.id.tv_parent_profile);
        cardView = findViewById(R.id.cardview3);
        imageView = findViewById(R.id.imageView3);
        btnShop = findViewById(R.id.button_shop);
        btnShop.setOnClickListener((v) ->{
            Intent intent = new Intent(ParentProfileActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}