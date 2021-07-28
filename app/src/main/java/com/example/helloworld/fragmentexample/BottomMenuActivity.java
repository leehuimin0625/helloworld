package com.example.helloworld.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.helloworld.R;

import java.util.ArrayList;

public class BottomMenuActivity extends AppCompatActivity {
    private LinearLayout btnMenu, btnHome, btnProfile;
    private ArrayList<LinearLayout> buttonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        findViews();
        setLinsteners();
        btnMenu.performClick();
    }

    private void findViews(){
        btnMenu = findViewById(R.id.linearButtonMenu);
        btnHome = findViewById(R.id.linearButtonHome);
        btnProfile = findViewById(R.id.linearButtonProfile);

        buttonList.add(btnMenu);
        buttonList.add(btnHome);
        buttonList.add(btnProfile);
    }

    private void setLinsteners(){
        btnMenu.setOnClickListener(view->{
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new MenuFragment())
                    .commit();

            view.setBackgroundColor(Color.parseColor("#8cedea"));

            for(LinearLayout btn : buttonList){
                if (btn.getId() != view.getId()){
                    btn.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        btnHome.setOnClickListener(view ->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();

            view.setBackgroundColor(Color.parseColor("#caff99"));

            for(LinearLayout btn :buttonList){
                if(btn.getId() != view.getId()){
                    btn.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        btnProfile.setOnClickListener(view->{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .commit();

            view.setBackgroundColor(Color.parseColor("#cb94f7"));

            for(LinearLayout btn :buttonList){
                if(btn.getId() != view.getId()){
                    btn.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }
}