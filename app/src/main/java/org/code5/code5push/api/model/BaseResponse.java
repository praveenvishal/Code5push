package org.code5.code5push.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sony on 8/19/2017.
 */

public class BaseResponse {


    @SerializedName("status")
    @Expose
    protected String status;
    @SerializedName("status_code")
    @Expose
    protected Integer statusCode;
    @SerializedName("message")
    @Expose
    protected String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}