package com.example.helloworld.sharedpreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helloworld.R;

public class SharedPreferencesDisplayActivity extends AppCompatActivity {
    private TextView nameDisplay, courseDisplay, ageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_display);
        findViews();
        initData();
    }
    private void findViews(){
        nameDisplay=findViewById(R.id.nameDisplay);
        courseDisplay=findViewById(R.id.courseDisplay);
        ageDisplay=findViewById(R.id.ageDisplay);
    }
    private void initData(){
        SharedPreferences pref = getSharedPreferences("MyPrefName", Context.MODE_PRIVATE);
        String name = pref.getString("name","");
        String course = pref.getString("course","");
        String age = pref.getString("age","");

        nameDisplay.setText(name);
        courseDisplay.setText(course);
        ageDisplay.setText(age);
    }
}