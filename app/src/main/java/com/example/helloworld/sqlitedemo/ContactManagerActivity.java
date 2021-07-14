package com.example.helloworld.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.helloworld.R;
import com.example.helloworld.sqlitedemo.room.ContactAdapter;
import com.example.helloworld.sqlitedemo.room.Contact;
import com.example.helloworld.sqlitedemo.room.ContactDAO;
import com.example.helloworld.sqlitedemo.room.ContactDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactManagerActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerViewContact;
    //private ContactDBHelper helper;
    private ContactDAO contactDAO;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
        //helper=new ContactDBHelper(this);
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
        findViews();
        setListeners();
        initRecyclerview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAdapter();
    }

    private void findViews() {
        btnAdd=findViewById(R.id.btnAddContact);
        recyclerViewContact=findViewById(R.id.recyclerContact);
    }

    private  void setListeners() {
        btnAdd.setOnClickListener(view->{
            Intent i =new Intent(this,AddContactActivity.class);
            startActivity(i);
        });
    }

    private void initRecyclerview() {
        List<Contact> emptyContact = new ArrayList<>();
        adapter = new ContactAdapter(ContactManagerActivity.this, emptyContact);
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContact.setAdapter(adapter);
    }

    private void refreshAdapter(){
        new Thread(()->{
            List<Contact> contacts = contactDAO.getAllContacts();
            runOnUiThread(()->adapter.updateData(contacts));
        }).start();
    }
}