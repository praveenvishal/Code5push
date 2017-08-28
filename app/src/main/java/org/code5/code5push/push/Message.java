package org.code5.code5push.push;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.code5.code5push.api.database.PushDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sony on 8/25/2017.
 */
@Table(database = PushDataBase.class)

public class Message extends BaseModel
{
    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column
    String message;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column
    String date;

    public static List<Message>getMessages()
    {
        List<Message> messages= new ArrayList<Message>();

        for(int i =0;i<100;i++)
        {
            Message msg = new Message();
            msg.setMessage("This is the test notification sent from of the offic e of push.code5.org");
            messages.add(msg);



        }
        return messages;






    }



}
