
package com.example.myaidlservice;
import com.example.myaidlservice.ServiceResult;



interface ServiceAidlInterface {

   void testParams(int param,ServiceResult callBack);

   String sayHello(String name);




}
