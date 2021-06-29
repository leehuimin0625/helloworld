package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class OrderCoffee extends AppCompatActivity {
    private Button btnAdd, btnMinus, btnmakeorder;
    private CheckBox checkcream, checkcookie, checkchoco;
    private TextView txtAmountCoffee;

    private int amount = 1;
    private int basePrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercoffee);
        findViews();
        setListeners();
    }
    private void findViews(){
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnmakeorder = findViewById(R.id.btnmakeorder);
        checkcream = findViewById(R.id.checkcream);
        checkcookie = findViewById(R.id.checkcookie);
        checkchoco = findViewById(R.id.checkchoco);
        txtAmountCoffee = findViewById(R.id.txtAmountCoffee);

    }
    private void setListeners(){
        btnAdd.setOnClickListener(view->{
            amount ++;
            txtAmountCoffee.setText(Integer.toString(amount));
        });
        btnMinus.setOnClickListener(view->{
            if(amount!=1){
                amount--;
                txtAmountCoffee.setText(Integer.toString(amount));
            }
        });
        btnmakeorder.setOnClickListener(view->{
            int price = basePrice;
            if (checkcream.isChecked()){
                price +=1;
            }
            if (checkcookie.isChecked()){
                price +=2;
            }
            if (checkchoco.isChecked()){
                price +=3;
            }
            price = price * amount;

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(OrderCoffee.this);
            dialogBuilder.setMessage("Your order is complete! The total price is $" + Integer.toString(price));
            dialogBuilder.setNeutralButton("ok",null);
            dialogBuilder.create().show();

        });
    }
}