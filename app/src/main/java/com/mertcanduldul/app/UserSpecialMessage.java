package com.mertcanduldul.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserSpecialMessage extends Fragment {
    private TextView textFromUser, textMessageContent, textMessageinput;
    private Button buttonSendMessage;
    private ScrollView scrolltext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usermessage_layout, container, false);

        textFromUser = view.findViewById(R.id.textFromUser);
        textMessageContent = view.findViewById(R.id.textMessageContent);
        buttonSendMessage = view.findViewById(R.id.buttonSendMessage);
        textMessageinput = view.findViewById(R.id.textMessageinput);
        scrolltext = view.findViewById(R.id.scrolltext);
        textMessageContent.setText("");

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("mesaj");

        reference.addValueEventListener(new ValueEventListener() {
            List<Mesaj> mesajlist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (textMessageContent.getText().equals("")) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        if (getArguments() != null) {
                            String fromkisi = getArguments().getString("fromkisi");//sero
                            String tokisi = getArguments().getString("tokisi");//mert
                            textFromUser.setText(fromkisi);

                            Mesaj mesaj = d.getValue(Mesaj.class);
                            if (mesaj.getFromKisi().equals(fromkisi) && mesaj.getToKisi().equals(tokisi)) {
                                textMessageContent.append("\n" + mesaj.getFromKisi() + " : " + mesaj.getMesajicerik() + "\n");
                                scrolltext.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                            if (mesaj.getFromKisi().equals(tokisi) && mesaj.getToKisi().equals(fromkisi)) {
                                textMessageContent.append("\n" + mesaj.getFromKisi() + " : " + mesaj.getMesajicerik() + "\n");
                                scrolltext.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        scrolltext.fullScroll(ScrollView.FOCUS_DOWN);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase Senddb = FirebaseDatabase.getInstance();
                DatabaseReference senddbReference = Senddb.getReference("mesaj");
                senddbReference.addChildEventListener(new ChildEventListener() {
                    Boolean unique = true;

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        if (unique) {
                            if (getArguments() != null) {
                                String fromkisi = getArguments().getString("fromkisi");
                                String tokisi = getArguments().getString("tokisi");

                                Mesaj m1 = new Mesaj();
                                m1.setToKisi(fromkisi);
                                m1.setFromKisi(tokisi);
                                m1.setMesajicerik(textMessageinput.getText().toString());
                                m1.setZaman("12 Ocak");
                                m1.setId("1231231");
                                senddbReference.push().setValue(m1);
                                if (m1.getFromKisi().equals(fromkisi) && m1.getToKisi().equals(tokisi)) {
                                    textMessageContent.append("\n" + m1.getFromKisi() + " : " + m1.getMesajicerik() + "\n");
                                    scrolltext.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                                if (m1.getFromKisi().equals(tokisi) && m1.getToKisi().equals(fromkisi)) {
                                    textMessageContent.append("\n" + m1.getFromKisi() + " : " + m1.getMesajicerik() + "\n");
                                    scrolltext.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                                textMessageinput.setText("");
                            }
                            unique = false;
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }
}
