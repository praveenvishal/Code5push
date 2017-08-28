package org.code5.code5push;

import android.app.Dialog;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.code5.code5push.push.HeaderAdapter;
import org.code5.code5push.push.Message;
import org.code5.code5push.push.PushAdapter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;


import static org.code5.code5push.R.id.recyclerView;

public class MainActivity extends LaunchActivity {


    private RecyclerView list;
    private List<Message> messages = new ArrayList<>();
    private PushAdapter adapter;
    CompactCalendarView compactCalendarView;
    private String TAG="MainACtivity";
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final int REQUEST_CODE_PICKER = 100;
    private Dialog ticketRaise;
    private ImageView mImageView;
    private EditText description;
    private EditText subject;
    private File imageFile;
    private String desc;
    private String subj;
    private ArrayList<Image> images = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
      /*  compactCalendarView=(CompactCalendarView)findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
*/
        list = (RecyclerView)findViewById(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        List<Message> messages = Message.getMessages();
        HeaderAdapter adapter = new HeaderAdapter(messages,this);
        list.setAdapter(adapter);
       /* messages = SQLite.select().
                from(Message.class).queryList();*/
        Log.d("MActivity",String.valueOf(messages.size()));



    }


}
