package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.utils.SessionManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String PREF_NAME_SESSION ="session";
    private OkHttpClient client =new OkHttpClient();
    private String username = "test@gmail.com", password = "password123";
    private EditText edtEmail, edtPassword;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setListeners();
        checkUserLoggedIn();
    }

    private void findViews(){
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        btnlogin=findViewById(R.id.btnLogin);
    }

    private void setListeners(){
        btnlogin.setOnClickListener(view->{
            verifyLogin();
        });
    }

    private void checkUserLoggedIn(){
        SharedPreferences pref = getSharedPreferences(PREF_NAME_SESSION, MODE_PRIVATE);
        String username = pref.getString("username", null);

        if (username != null && !username.equals("")){
            Intent i = new Intent(this, InfoActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void verifyLogin() {
        Request request = new Request.Builder()
                .url("https://api.jsonbin.io/b/60dbf0eefe016b59dd587a93")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String res= response.body().string();

                runOnUiThread(()->{
                    boolean verify =false;
                    String inputEmail=edtEmail.getText().toString();
                    String inputPassword=edtPassword.getText().toString();

                    try {
                        JSONObject resObject = new JSONObject(res);
                        JSONArray accountArray = resObject.getJSONArray("accounts");

                        for (int i = 0; i < accountArray.length(); i++) {
                            JSONObject acc = accountArray.getJSONObject(i);

                            if (acc.getString("email").equals(inputEmail)) {
                                if(acc.getString("password").equals(inputPassword)){
                                verify = true;
                                saveToSharedPreferences(acc);
                                Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                showWrongEmailPasswordToast();
                                break;
                            }
                        }
                    }

                    if(!verify) {
                        showWrongEmailPasswordToast();
                    }
                } catch (Exception e) {
                        e.printStackTrace();
                }
            });
        }
    });
 }
    private void saveToSharedPreferences(JSONObject obj){
        SessionManager manager = new SessionManager(this);
        manager.saveData(obj);
    }

    private void showWrongEmailPasswordToast(){
        Toast.makeText(this,"Wrong Username or Password", Toast.LENGTH_SHORT).show();

    }
}