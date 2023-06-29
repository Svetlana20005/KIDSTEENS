package com.example.kidsteens;

import static com.example.kidsteens.QRCodeScannerActivity.KEY_QR_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyChildActivity extends AppCompatActivity {
    TextView tvMyChild;
    Button btnChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_child);
        tvMyChild = findViewById(R.id.tvMyChild);
        btnChild = findViewById(R.id.btn_child);
        btnChild.setOnClickListener((v) -> {
            Intent intent = new Intent(MyChildActivity.this, QRCodeScannerActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            String r = data.getStringExtra(KEY_QR_CODE);
            String[] parts = r.split("===");
            String childToken = parts[0];
            String childName = parts[1];
            Toast.makeText(this, "Ребенок с именем " + childName + " добавлен", Toast.LENGTH_SHORT).show();
        }
    }
}