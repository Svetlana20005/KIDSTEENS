package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Logger;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    public static final String KEY_QR_CODE = "KEY_QR_CODE";
    private ZXingScannerView mScannerView;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    @Override
    public void handleResult(com.google.zxing.Result result) {
        Intent intent = new Intent();
        intent.putExtra(KEY_QR_CODE, result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}