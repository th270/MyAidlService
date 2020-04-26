package com.example.myaidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        Log.d("test","action == " + action);
        if (null != intent && "com.example.myaidlservice.MyService".equals(action)){
            Log.d("test","new MyAidlService");
            MyAidlServiceImpl myAidlService = new MyAidlServiceImpl();
            return myAidlService;
        }
        return new action();
    }


    public class action extends Binder{

        public int add( int a , int b){

            return a + b ;

        }


        public int sub( int a , int b){

            return a - b ;
        }

    }


}
