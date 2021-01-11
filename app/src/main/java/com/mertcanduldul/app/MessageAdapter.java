package com.mertcanduldul.app;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    @NonNull
    @Override
    public MessageAdapter.MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class MessageHolder extends RecyclerView.ViewHolder {
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
