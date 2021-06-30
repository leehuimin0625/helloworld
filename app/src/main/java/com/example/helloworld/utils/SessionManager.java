package com.example.helloworld.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.helloworld.LoginActivity;

import org.json.JSONObject;

public class SessionManager {
    private Context context;
    private String id, username, email, color;

    public SessionManager(Context context){
        this.context=context;

        SharedPreferences pref = context.getSharedPreferences(LoginActivity.PREF_NAME_SESSION, Context.MODE_PRIVATE);

        if(!pref.getString("username", "").equals("")){
            setId(pref.getString("id", ""));
            setUsername(pref.getString("username", ""));
            setEmail(pref.getString("email", ""));
            setColor(pref.getString("settingsColor", ""));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void saveData(JSONObject obj){
        try {
            SharedPreferences pref = context.getSharedPreferences(LoginActivity.PREF_NAME_SESSION, Context.MODE_PRIVATE);
            pref.edit()
                    .putString("id", obj.getString("id"))
                    .putString("email", obj.getString("email"))
                    .putString("username", obj.getString("username"))
                    .putString("settingsColor", obj.getJSONObject("settings").getString("color"))
                    .apply();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
