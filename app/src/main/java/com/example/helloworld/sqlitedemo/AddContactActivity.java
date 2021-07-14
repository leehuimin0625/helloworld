package com.example.helloworld.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.helloworld.sqlitedemo.room.Contact;
import com.example.helloworld.sqlitedemo.room.ContactDAO;
import com.example.helloworld.sqlitedemo.room.ContactDatabase;

public class AddContactActivity extends AppCompatActivity {
    private EditText edtName, edtNumber;
    private Button btnSave;
    //private ContactDBHelper helper;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
        //helper= new ContactDBHelper(this);
        findViews();
        setListeners();
    }

    private void findViews(){
        edtName = findViewById(R.id.edtNama);
        edtNumber = findViewById(R.id.edtNumber);
        btnSave = findViewById(R.id.btnSave);
    }

    private void setListeners(){
        btnSave.setOnClickListener(view-> {
            String name =edtName.getText().toString();
            String number =edtNumber.getText().toString();

            if(name.isEmpty()||number.isEmpty()){
                Toast.makeText(this,"Name or Number cannot be Empty!",Toast.LENGTH_LONG).show();
            }else {
                Contact contact=new Contact(name, number);
                insertContact(contact);
                //helper.insertContant(contact);
                finish();
            }
        });
    }

    private void insertContact(Contact contact){
        new Thread(()-> contactDAO.insertContact(contact)).start();
    }
}