package com.mertcanduldul.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageActivity extends Fragment {
    private RecyclerView rvMessageList;
    private MessageAdapter messageAdapter;
    //  private List<Mesaj> mesajList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_layout, container, false);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("mesaj");
        List<Mesaj> mesajList = new ArrayList<>();
        List<Mesaj> mesajList2 = new ArrayList<>();
        List<String> mesajList3 = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            Boolean aq = true;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    if (getArguments() != null) {
                        String username = getArguments().getString("username");
                        String userfullname = getArguments().getString("userfullname");
                        String userkey = getArguments().getString("userkey");

                        Mesaj mesaj = d.getValue(Mesaj.class);
                        if (mesaj.getToKisi().equals(username)) {
                            //gelen kutusu
                            mesajList.add(mesaj);
                            Map<String, List<Mesaj>> listMap = mesajList.stream().collect(Collectors.groupingBy(Mesaj::getFromKisi));
                            for (String key : listMap.keySet()) {
                                if (!mesajList3.contains(mesaj.getFromKisi()) && key == mesaj.getFromKisi()) {
                                    mesajList3.add(key);
                                    mesajList2.add(mesaj);
                                }
                            }
                        }
                        rvMessageList = view.findViewById(R.id.rvMessageList);
                        messageAdapter = new MessageAdapter(mesajList2, getContext());
                        messageAdapter.setUsername(username);
                        rvMessageList.setAdapter(messageAdapter);
                        rvMessageList.setLayoutManager(new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}