package org.code5.code5push.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sony on 8/20/2017.
 */

public class BaseModel extends BaseResponse
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("registration_id")
    @Expose
    private List<String> regId;
    @SerializedName("connection_number")
    @Expose
    private List<String>  connectionNo;

    public List<String> getRegId() {
        return regId;
    }

    public void setRegId(List<String> regId) {
        this.regId = regId;
    }

    public List<String> getConnectionNo() {
        return connectionNo;
    }

    public void setConnectionNo(List<String> connectionNo) {
        this.connectionNo = connectionNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
