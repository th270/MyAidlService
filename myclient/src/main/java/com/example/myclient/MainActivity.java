package com.example.myclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myaidlservice.ServiceAidlInterface;
import com.example.myaidlservice.ServiceResult;

public class MainActivity extends AppCompatActivity {

    private ServiceAidlInterface mServiceAidlInterface = null;
    Boolean mIsBind = false;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceAidlInterface = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("test", "connected  service");
            mServiceAidlInterface = ServiceAidlInterface.Stub.asInterface(service);
            if (mServiceAidlInterface == null){
                Log.d("test","mServiceAidlInterface == null");
                return;
            }

            try {

                String result = mServiceAidlInterface.sayHello("徐玲！");

                Log.d("test","result = " + result);

                mServiceAidlInterface.testParams(2, new ServiceResult.Stub() {
                    @Override
                    public void success() throws RemoteException {
                        Log.d("test", "success!");
                    }

                    @Override
                    public void failed(int errorCode, String msg) throws RemoteException {
                        Log.d("test", "errorCode == " + errorCode + "msg == " + msg);
                    }

                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction("com.example.myaidlservice.MyService");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.example.myaidlservice");
        Log.d("test","start bind service");
        mIsBind = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsBind = false;
        serviceConnection = null;
    }
}


