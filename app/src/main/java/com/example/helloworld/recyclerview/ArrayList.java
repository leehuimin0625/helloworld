package com.example.helloworld.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.helloworld.R;

public class ArrayList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private java.util.ArrayList contactArrayList = new java.util.ArrayList();

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
        for(int i=0; i<5; i++){
            Contact contact = new Contact("John","0136633776");
            contactArrayList.add(contact);
        }

      ContactAdapter adapter = new ContactAdapter(ArrayList.this,contactArrayList);
      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}