package com.example.helloworld.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.R;

public class SharedPreferencesDemoActivity extends AppCompatActivity {
    private EditText edtName, edtCourse, edtAge;
    private Button btnSave,btnShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_demo);
        findViews();
        setListeners();
        
    }
    private void findViews(){
       edtName= findViewById(R.id.edtNama);
       edtCourse=findViewById(R.id.edtCourse);
       edtAge=findViewById(R.id.edtAge);
       btnSave=findViewById(R.id.btnSave);
       btnShowData=findViewById(R.id.btnShowData);
    }

    private void setListeners(){
        btnSave.setOnClickListener(view->{
            String name =edtName.getText().toString();
            String course =edtCourse.getText().toString();
            String age =edtAge.getText().toString();

            SharedPreferences pref =this.getSharedPreferences("MyPrefName", Context.MODE_PRIVATE);
            pref.edit()
                    .putString("name",name)
                    .putString("course",course)
                    .putString("age",age)
                    .apply();

            Toast.makeText(this,"Data Saved into Shared Preferences", Toast.LENGTH_SHORT).show();
        });

        btnShowData.setOnClickListener(view->{
            Intent i = new Intent( this,SharedPreferencesDisplayActivity.class);
            startActivity(i);
        });
    }
}