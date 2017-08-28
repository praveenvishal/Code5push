package org.code5.code5push.api.rest;

/**
 * Created by sony on 8/20/2017.
 */

public interface RestCallBack
{
    void onSuccess(boolean status);
    void getStatusCode(int statusCode);
    void getMessage(String message);
    void getList(String[] list);

}
