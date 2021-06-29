package com.example.helloworld;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private String username = "lee@gmail.com", password = "password123";
    private EditText edtusername, edtpassword;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }
    private void findViews(){
        edtusername=findViewById(R.id.edtUsername);
        edtpassword=findViewById(R.id.edtPassword);
        btnlogin=findViewById(R.id.btnLogin);
    }
    private void setListeners(){
        btnlogin.setOnClickListener(view->{
            String usernameInput = edtusername.getText().toString();
            String passwordInput = edtpassword.getText().toString();
            if(usernameInput.equals(username)&& passwordInput.equals(password)) {
                Intent intent = new Intent(MainActivity.this, OrderCoffee.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this,"Wrong username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}