package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidsteens.server.LoginData;
import com.example.kidsteens.server.RetrofitHelper;

public class StartActivity extends AppCompatActivity {
    View screen;
    EditText phone,pass;
    Button buttonEnter, btnReg;
    TextView tvEnter;
    RetrofitHelper helper;
    SharedPreferences sharedPreferences;
    LoginData loginData;
    final Context context = this;
    public static final String TOKEN = "Token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        screen = findViewById(R.id.screen);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        buttonEnter = findViewById(R.id.buttonenter);
        btnReg = findViewById(R.id.button_registration);
        tvEnter = findViewById(R.id.tv_enter);
        sharedPreferences = getSharedPreferences("Tokens", MODE_PRIVATE);
        buttonEnter.setOnClickListener((v) ->{
            loginData = new LoginData();
            loginData.setPhone(phone.getText().toString());
            loginData.setCode(pass.getText().toString());
            helper.login((result)->{
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(result != null){
                    editor.putString(TOKEN, result.toString());
                    editor.commit();
                    Intent intent = new Intent(StartActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Неверный код!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }, loginData);
        });
        btnReg.setOnClickListener((v) ->{
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.get_sms, null);
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(context);
            mDialogBuilder.setView(promptsView);
            final EditText userInput = promptsView.findViewById(R.id.input_text);
            mDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    phone.setText(userInput.getText());
                                }
                            })
                    .setNegativeButton("Отмена",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = mDialogBuilder.create();
            alertDialog.show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = sharedPreferences.getString(TOKEN, "");
        if(token.equals("")){
            Intent intent = new Intent(StartActivity.this, ParentProfileActivity.class);
            startActivity(intent);
        }
    }
}