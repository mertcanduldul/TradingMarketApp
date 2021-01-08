package com.mertcanduldul.app;

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

public class DetailActivity extends Fragment {
    private TextView urun_adi;
    private TextView urun_aciklama;
    private TextView urun_fiyat;
    private ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_layout, container, false);

        int urun_id = getArguments().getInt("urun_id");
        String strurun_adi = getArguments().getString("urun_adi");
        String strurun_aciklama = getArguments().getString("urun_aciklama");
        String strurun_fiyat = getArguments().getString("urun_fiyat");
        String strurun_fotograf = getArguments().getString("urun_fotograf");

        urun_adi = view.findViewById(R.id.detayTextUrunAdi);
        urun_aciklama = view.findViewById(R.id.detayTextUrunAciklama);
        urun_fiyat = view.findViewById(R.id.detayTextUrunFiyat);
        imageView = view.findViewById(R.id.imageView1);

        urun_adi.setText(strurun_adi);
        urun_aciklama.setText(strurun_aciklama);
        urun_fiyat.setText(strurun_fiyat);
        imageView.setImageResource(getContext().getResources().getIdentifier(strurun_fotograf, "drawable", getContext().getOpPackageName()));
        return view;
    }
}
