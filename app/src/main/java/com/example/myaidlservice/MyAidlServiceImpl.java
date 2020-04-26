package com.example.myaidlservice;


import android.os.RemoteException;
import android.util.Log;

public class MyAidlServiceImpl extends ServiceAidlInterface.Stub {

    @Override
    public void testParams(int param, ServiceResult callBack) throws RemoteException {
        Log.d("test","param = " + param);
        int paramResult = param - 1;

        if(null == callBack){
            Log.d("test","callBack == null");
            return;
        }
        if (!(paramResult > param)){
            callBack.failed(1001,"param can't add.");
            return;
        }
        callBack.success();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name;
    }
    
}
