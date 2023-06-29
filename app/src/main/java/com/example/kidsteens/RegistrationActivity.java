package com.example.kidsteens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidsteens.classes.User;
import com.example.kidsteens.server.RetrofitHelper;
import com.example.kidsteens.server.UserServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity
implements View.OnClickListener {
    View screen;
    TextView tvEnter, tvUserName;
    EditText userName;
    Button buttonParent, buttonChild;
    CardView cardView1, cardView2;
    ImageView imageView1, imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        screen = findViewById(R.id.screen);
        tvEnter = findViewById(R.id.tv_enter);
        tvUserName = findViewById(R.id.tvUserName);
        userName = findViewById(R.id.userName);
        cardView1 = findViewById(R.id.cardview1);
        cardView2 = findViewById(R.id.cardview2);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        buttonParent = findViewById(R.id.buttonparent);
        buttonChild = findViewById(R.id.buttonchild);
        buttonParent.setOnClickListener(this);
        buttonChild.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class cls = null;
        Intent intent = null;
        String name = userName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this, "Введи имя", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (v.getId()){
            case R.id.buttonparent:
                 cls = ParentProfileActivity.class;
                intent = new Intent(RegistrationActivity.this, cls);
                break;
            case R.id.buttonchild:
                cls = QRActivity.class;
                intent = new Intent(RegistrationActivity.this, cls);
                intent.putExtra("name", name);
                break;
        }
        switch (v.getId()){
            case R.id.buttonparent:
                Intent finalIntent = intent;
                RetrofitHelper.getServer().create(UserServer.class).profile(name)
                        .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        startActivity(finalIntent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                break;
            case R.id.buttonchild:

                startActivity(intent);
                break;
        }

    }
}