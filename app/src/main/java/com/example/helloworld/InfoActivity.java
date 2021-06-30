package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.utils.SessionManager;

public class InfoActivity extends AppCompatActivity {
    private Button btnLogout;
    private LinearLayout rootLayout;
    private TextView tvUsername, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        findViews();
        setListeners();
        initializeInterface();
    }

    private void findViews(){
       rootLayout = findViewById(R.id.rootlayout);
       tvUsername = findViewById(R.id.username);
       tvEmail = findViewById(R.id.email);
       btnLogout=findViewById(R.id.btnLogout);
    }

    private void setListeners(){
        btnLogout.setOnClickListener(view->{
            SharedPreferences pref = getSharedPreferences(LoginActivity.PREF_NAME_SESSION, MODE_PRIVATE);
            pref.edit().clear().apply();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
    private void initializeInterface(){
        SessionManager manager = new SessionManager(this);
        String email = manager.getEmail();
        String username = manager.getUsername();
        String color = manager.getColor();

        tvEmail.setText(email);
        tvUsername.setText(username);

        String colorCode ="##FFFFFF";

        if(color.equals("blue")){
            colorCode="#ABD8FF";
        }else if(color.equals("green")){
            colorCode = "#6EFFB6";
        } else if(color.equals("purple")){
            colorCode = "#D7BAFF";
        }

        rootLayout.setBackgroundColor(Color.parseColor(colorCode));
    }
}