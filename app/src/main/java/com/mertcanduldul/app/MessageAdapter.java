package com.mertcanduldul.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    List<Mesaj> mesajList;
    Context context;
    HashMap<String, List<Mesaj>> mesajmap;
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MessageAdapter(List<Mesaj> mesajList, Context context) {
        this.mesajList = mesajList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item_layout, parent, false);
        return new MessageHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageHolder holder, int position) {
        Mesaj m = mesajList.get(position);
        holder.textUserMessage.setText(m.getFromKisi());
        holder.textDateMessage.setText(m.getZaman());
        Picasso.
                get().
                load("https://pbs.twimg.com/profile_images/2224119204/557523_199181593530846_199176213531384_325140_69930876_n_400x400.jpg").
                into(holder.imageUserMessage);
        holder.buttonGotoMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                UserSpecialMessage userSpecialMessage = new UserSpecialMessage();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, userSpecialMessage).commit();
                Bundle bnd = new Bundle();
                bnd.putString("fromkisi", holder.textUserMessage.getText().toString());
                bnd.putString("tokisi",getUsername());
                userSpecialMessage.setArguments(bnd);



            }
        });

    }

    @Override
    public int getItemCount() {
        return mesajList.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {
        TextView textUserMessage, textDateMessage;
        Button buttonGotoMessage;
        ImageView imageUserMessage;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            textUserMessage = itemView.findViewById(R.id.textUserMessage);
            textDateMessage = itemView.findViewById(R.id.textDateMessage);
            imageUserMessage = itemView.findViewById(R.id.imageUserMessage);
            buttonGotoMessage = itemView.findViewById(R.id.buttonGotoMessage);

        }
    }

}
