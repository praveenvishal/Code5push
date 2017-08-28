package org.code5.code5push.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sony on 8/18/2017.
 */

public class LoginResponse extends BaseResponse
{

    @SerializedName("user")
    @Expose
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


}
