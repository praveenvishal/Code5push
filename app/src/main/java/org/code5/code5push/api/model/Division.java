package org.code5.code5push.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sony on 8/18/2017.
 */

public class Division extends BaseModel
{
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer stateId) {
        this.cityId = stateId;
    }

    @SerializedName("city_id")
    @Expose
    private Integer cityId;


}
