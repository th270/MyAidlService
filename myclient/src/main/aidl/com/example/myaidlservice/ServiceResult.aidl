// ServiceResult.aidl
package com.example.myaidlservice;

// Declare any non-default types here with import statements

interface ServiceResult {

    void success();
    void failed(int errorCode,String msg);

    void error(int errorCode,String msg);

}
