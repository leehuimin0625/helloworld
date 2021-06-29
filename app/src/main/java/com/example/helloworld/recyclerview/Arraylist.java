package com.example.helloworld.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.helloworld.R;

import java.util.ArrayList;
public class Arraylist extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        findViews();
        setListener();
        initRecyclerView();

    }
    private void findViews(){
        recyclerView = findViewById(R.id.recyclerview);

    }
    private void setListener(){

    }
    private void initRecyclerView(){
        contactArrayList.add(new Contact("BK", "1232534467"));
        contactArrayList.add(new Contact("John", "1122233887"));
        contactArrayList.add(new Contact("Jack", "1254422312"));
        contactArrayList.add(new Contact("Ali", "1234581818"));
        contactArrayList.add(new Contact("ABC", "2918210118"));
        contactArrayList.add(new Contact("CDE", "129291821"));
        contactArrayList.add(new Contact("EFG", "9199128191"));

      ContactAdapter adapter = new ContactAdapter(Arraylist.this,contactArrayList);
      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}