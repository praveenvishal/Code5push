package org.code5.code5push.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.*;

import org.code5.code5push.api.database.PushDataBase;

/**
 * Created by sony on 8/19/2017.
 */
@Table(database = PushDataBase.class)
public class User extends BaseModel{


        public int getID() {
                return ID;
        }
        public void setID(int ID) {
                this.ID = ID;
        }
        @Expose
        @Column
        @PrimaryKey
        private int ID;
        @SerializedName("fullname")
        @Expose
        @Column
        private String fullname;

        public String getEmailId() {
                return emailId;
        }

        public void setEmailId(String emailId) {
                this.emailId = emailId;
        }

        @SerializedName("email")
        @Expose
        @Column
        private String emailId;
        @SerializedName("api_token")
        @Expose
        @Column
        private String apiToken;

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }



        public String getApiToken() {
            return apiToken;
        }

        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }
}
