package org.code5.code5push.push;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.code5.code5push.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sony on 8/25/2017.
 */

public class PushAdapter extends RecyclerView.Adapter<PushAdapter.MessageViewHolder>
{


    List<Message> messageList = new ArrayList<>();

    public Context getContext() {
        return context;
    }

    public void addAll(List<Message> messages)
    {
        messageList =messages;
        notifyDataSetChanged();

    }

    Context context;

    public PushAdapter(Context context,List<Message> messageList)
    {

        this.messageList=messageList;
        this.context=context;

    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.list_item, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(messageView);
        return messageViewHolder;

    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position)
    {

        Message message = messageList.get(position);
        TextView msg = holder.msgtext;
        TextView time = holder.time;
        msg.setText(message.getMessage());
        time.setText("02-12-2017");
        ImageView imageView =holder.logo;



    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {



        private TextView msgtext;
        private TextView time;
        private ImageView logo;

        public MessageViewHolder(View itemView) {
            super(itemView);
            msgtext = (TextView) itemView.findViewById(R.id.message);
            time = (TextView) itemView.findViewById(R.id.time);
            logo = (ImageView) itemView.findViewById(R.id.logo);
        }
    }
}
