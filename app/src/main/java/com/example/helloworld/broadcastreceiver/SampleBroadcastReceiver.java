package com.example.helloworld.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

class SampleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "AIRPLANE MODE STATUS CHANGED", Toast.LENGTH_SHORT)
                .show();
    }
}