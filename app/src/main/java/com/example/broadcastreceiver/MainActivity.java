package com.example.broadcastreceiver;

/* Broadcast Receiver needs to be registered to get the incoming messages
 * 1. Dynamic Approach: need to register the broadcast receiver within the java file
 * 2. Static Approach: need to register broadcasr receiver in the Adnroid Manifest file
 * */


import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;
    IntentFilter intentFilter;
    Button button_broadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter(Intent.ACTION_SEND);


        button_broadcast = (Button)findViewById(R.id.buttonBroadcast);

        button_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                sendBroadcast(intent);
            }
        });
    }

    /* Registering broadcast receiver in onREsume() causes the application to resume again whenever
     * we go back and forth from one screen to another, because of the lifecycle
     *
     * Therefore, if you want to prevent the application from resuming again and again or start the
     * receiver only once throughout the activity lifecycle then you should
     * register the broadcast receiver in onCreate of MainActivity
     * */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
