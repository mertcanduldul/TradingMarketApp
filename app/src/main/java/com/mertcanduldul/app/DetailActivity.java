package com.mertcanduldul.app;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends Fragment {
    private TextView urun_adi;
    private TextView urun_aciklama;
    private TextView urun_fiyat;
    private ImageView urun_fotograf;
    private Chip chipLocation, chipDate;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_layout, container, false);

        String urun_id = getArguments().getString("urun_id");
        String strurun_adi = getArguments().getString("urun_adi");
        String strurun_aciklama = getArguments().getString("urun_aciklama");
        String strurun_fiyat = getArguments().getString("urun_fiyat");

        urun_adi = view.findViewById(R.id.detayTextUrunAdi);
        urun_aciklama = view.findViewById(R.id.detayTextUrunAciklama);
        urun_fiyat = view.findViewById(R.id.detayTextUrunFiyat);
        urun_fotograf = view.findViewById(R.id.imageView1);
        chipLocation = view.findViewById(R.id.chipLocation);
        chipDate = view.findViewById(R.id.chipDate);

        urun_adi.setText(strurun_adi);
        urun_aciklama.setText(strurun_aciklama);
        urun_fiyat.setText(strurun_fiyat);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("urun");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String key = d.getKey();
                    Urun u5 = d.getValue(Urun.class);
                    if (urun_id.equals(u5.getUrun_id())) {
                        Picasso.get().load(u5.getUrun_fotograf()).into(urun_fotograf);
                        chipLocation.setText(u5.getUrun_lokasyon());
                        chipDate.setText(u5.getUrun_yuklenme_tarih());
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
