package com.example.kidsteens;

import static com.example.kidsteens.StartActivity.TOKEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ParentProfileActivity extends AppCompatActivity {
    View screen;
    TextView tvProf;
    CardView cardView;
    ImageView imageView;
    Button btnShop, btnMyChild, btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        screen = findViewById(R.id.screen);
        tvProf = findViewById(R.id.tv_parent_profile);
        cardView = findViewById(R.id.cardview3);
        imageView = findViewById(R.id.imageView3);
        btnShop = findViewById(R.id.button_shop);
        btnMyChild = findViewById(R.id.btnMyChild);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnShop.setOnClickListener((v) ->{
            Intent intent = new Intent(ParentProfileActivity.this, MainActivity.class);
            startActivity(intent);
        });
        btnMyChild.setOnClickListener((v) ->{
            Intent intent = new Intent(ParentProfileActivity.this, MyChildActivity.class);
            startActivity(intent);
        });
        btnLogOut.setOnClickListener((v) ->{
            getSharedPreferences("Tokens", MODE_PRIVATE).edit().clear().commit();
            Intent intent = new Intent(ParentProfileActivity.this, StartActivity.class);
            startActivity(intent);
        });
    }
}