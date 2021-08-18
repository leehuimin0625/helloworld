package com.example.helloworld.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.R;

public class BroadcastReceiverActivity extends AppCompatActivity {
    private SampleBroadcastReceiver receiver = new SampleBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broacast_receiver);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void broadcastButtonClick(View view){
        Intent intent = new Intent();
        intent.setAction("com.example.helloworld.SAMPLE_BROADCAST");
        sendBroadcast(intent);
    }
}