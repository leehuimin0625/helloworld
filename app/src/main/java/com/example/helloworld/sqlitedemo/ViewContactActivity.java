package com.example.helloworld.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.sqlitedemo.room.Contact;
import com.example.helloworld.sqlitedemo.room.ContactDAO;
import com.example.helloworld.sqlitedemo.room.ContactDatabase;

public class ViewContactActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    private Contact contact = new Contact();
    //private ContactDBHelper helper;
    private ContactDAO contactDAO;

    private TextView displayName, displayNumber;
    private Button btnEditName, btnEditNumber, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        readExtra();
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
        //helper = new ContactDBHelper(this);
        findViews();
        setListeners();
    }

    private void findViews(){
        displayName = findViewById(R.id.tv_display_name);
        displayNumber = findViewById(R.id.tv_display_number);
        btnEditName = findViewById(R.id.btn_edt_name);
        btnEditNumber = findViewById(R.id.btn_edt_number);
        btnDelete = findViewById(R.id.btn_delete_contact);

        displayName.setText(contact.getName());
        displayNumber.setText(contact.getPhoneNumber());
    }

    private void setListeners(){
        btnEditName.setOnClickListener(view-> showEditNameDialog());

        btnEditNumber.setOnClickListener(view -> showEditNumberDialog());

        btnDelete.setOnClickListener(view -> showDeleteDialog());
    }

    private void showEditNameDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.update_name_dialog);

        EditText edtName = dialog.findViewById(R.id.edtNama);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        edtName.setText(contact.getName());

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnSave.setOnClickListener(view-> {
            String newName =edtName.getText().toString();

            if(newName.isEmpty()){
                edtName.setError("Name cannot be empty");
            }else {
                edtName.setError(null);
                contact.setName(newName);
                updateContact(contact);
                //helper.updateContact(contact);
                displayName.setText(contact.getName());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showEditNumberDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.update_number_dialog);

        EditText edtNumber = dialog.findViewById(R.id.edtNumber);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        edtNumber.setText(contact.getPhoneNumber());

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnSave.setOnClickListener(view -> {
            String newNumber = edtNumber.getText().toString();

            if (newNumber.isEmpty()){
                edtNumber.setError("Number cannot be empty");
            } else{
                edtNumber.setError(null);
                contact.setPhoneNumber(newNumber);
                //helper.updateContact(contact);
                updateContact(contact);
                displayNumber.setText(contact.getPhoneNumber());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Contact")
                .setMessage(" Are you sure you want to delete this contact?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    //helper.deleteContact(contact.getId());
                    deleteContact(contact);
                    dialogInterface.dismiss();
                    finish();
                })
                .setNegativeButton("No",(dialogInterface,i)-> dialogInterface.dismiss())
                .create()
                .show();
    }

    private void readExtra(){
        Bundle extras = getIntent().getExtras();
        contact.setId(extras.getInt(KEY_ID));
        contact.setName(extras.getString(KEY_NAME));
        contact.setPhoneNumber(extras.getString(KEY_NUMBER));
    }

    private void updateContact(Contact contact){
        new Thread(() -> contactDAO.updateContact(contact)).start();
    }

    private void deleteContact(Contact contact) {
        new Thread(() -> contactDAO.deleteContact(contact)).start();
    }
}