package org.code5.code5push.push;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sony on 8/25/2017.
 */
@Table(database = PushDataBase.class)

public class Message extends BaseModel
{
    @Column
    @PrimaryKey
    int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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


    public static List<Message> getList()
    {
        List<Message> messageList = new ArrayList<>();
        Message msg = new Message();
        int j=100;

        for(int i=0;i<100;i++)
        {
            msg.setMessage("Hi This is Code5.Org custom Test message");
            msg.setId(i++);
            msg.save();

        }
        messageList = SQLite.select().
                from(Message.class).queryList();

        return messageList;


    }


}
