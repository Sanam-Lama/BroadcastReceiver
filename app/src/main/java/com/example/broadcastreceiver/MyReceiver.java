package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SEND)) {
            Toast.makeText(context, "SOME_ACTION is received",Toast.LENGTH_SHORT).show();
        }
        else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = (activeNetwork != null) && (activeNetwork.isConnected());
            if (isConnected) {
                try {
                    Toast.makeText(context, "Network is connected",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
