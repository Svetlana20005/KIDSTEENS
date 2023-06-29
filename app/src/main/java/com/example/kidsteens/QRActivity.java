package com.example.kidsteens;

import static com.example.kidsteens.StartActivity.TOKEN;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class QRActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qractivity);
        webView = findViewById(R.id.qr);
        String name = getIntent().getStringExtra("name");
        String tokens = getSharedPreferences("Tokens", MODE_PRIVATE).getString(TOKEN, "");
        webView.loadUrl("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+
                tokens + "===" + name);
    }
}