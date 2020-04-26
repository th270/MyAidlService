package com.example.myaidlservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private boolean mIsBinded;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.action action = (MyService.action) service;
            int add = action.add(100, 200);
            Log.d("test","add == " +add );
            int sub = action.sub(100, 200);
            Log.d("test","sub == " + sub );
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceConnection = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBindService();
    }

    private void onBindService() {

        Intent intent = new Intent(MainActivity.this, MyService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIsBinded = bindService(intent, serviceConnection, BIND_AUTO_CREATE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsBinded = false;
        serviceConnection = null;
    }
}
