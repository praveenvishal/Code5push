package org.code5.code5push;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import org.code5.code5push.push.Message;
import org.code5.code5push.push.Message_Table;
import org.code5.code5push.push.PushAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;


import static org.code5.code5push.R.id.recyclerView;

public class MainActivity extends AppCompatActivity {


    private RecyclerView list;
    private List<Message> messages = new ArrayList<>();
    private PushAdapter adapter;
    CompactCalendarView compactCalendarView;
    private String TAG="MainACtivity";
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compactCalendarView=(CompactCalendarView)findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        list = (RecyclerView)findViewById(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(linearLayoutManager);
        Message msg = new Message();
        msg.setMessage("Hi Hello");
        Message msg1 = new Message();
        msg1.setMessage("Second Message");
        msg1.save();
        Message msg3 = new Message();
        msg3.setMessage("Third message");
        msg3.save();
        messages = SQLite.select().
                from(Message.class).queryList();
        Log.d("MActivity",String.valueOf(messages.size()));
        adapter = new PushAdapter(this,messages);
        list.setAdapter(adapter);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                String reportDate = df.format(dateClicked);
                Message ms4 =new Message();
                ms4.setMessage("date");
                ms4.setDate(reportDate);
                messages = SQLite.select().
                        from(Message.class).queryList();
                adapter.addAll(messages);

             Log.d(TAG,reportDate);


               // Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });


    }
}
