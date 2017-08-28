package org.code5.code5push.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sony on 8/18/2017.
 */

public class City extends BaseModel
{
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @SerializedName("state_id")
    @Expose
    private Integer stateId;

}
