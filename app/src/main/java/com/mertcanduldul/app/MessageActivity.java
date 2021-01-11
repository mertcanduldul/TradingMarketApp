package com.mertcanduldul.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends Fragment {
    private RecyclerView rvMessageList;
    private MessageAdapter messageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_layout, container, false);

        List<Mesaj> mesajList = new ArrayList<>();
        Mesaj m = new Mesaj();
        m.setId("1");
        m.setFromKisi("sero");
        m.setMesajicerik("mesaj icerik xd");
        m.setToKisi("mert");
        m.setZaman("22 Mayıs");
        Mesaj m1 = new Mesaj();
        m1.setId("2");
        m1.setFromKisi("melihin");
        m1.setMesajicerik("mesaj icerik xd");
        m1.setToKisi("mert");
        m1.setZaman("23 Kasım");
        mesajList.add(m);
        mesajList.add(m1);

            rvMessageList = view.findViewById(R.id.rvMessageList);
            messageAdapter = new MessageAdapter(mesajList, getContext());
            rvMessageList.setAdapter(messageAdapter);
            rvMessageList.setLayoutManager(new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL));


        return view;
    }
}
