package com.mertcanduldul.app;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ProductHolder> {

    List<Urun> mList;
    Context context;

    public CustomAdapter(List<Urun> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public List<Urun> getmList() {
        return mList;
    }

    public void setmList(List<Urun> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ProductHolder(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Urun m = mList.get(position);
        int urunid = m.getUrun_id();
        holder.textUrunAdi.setText(m.getUrun_adi());
        holder.textAciklama.setText(m.getUrun_aciklama());
        holder.textUrunFiyat.setText(String.valueOf(m.getUrun_fiyat() + " ₺"));
        holder.urunImage.setImageResource(context.getResources().getIdentifier(m.getUrun_fotograf(), "drawable", context.getOpPackageName()));

        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Clicked button at position: " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DetailActivity myFragment = new DetailActivity();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, myFragment).addToBackStack(null).commit();

                Bundle bundle = new Bundle();
                bundle.putInt("urun_id", urunid);
                bundle.putString("urun_adi", holder.textUrunAdi.getText().toString());
                bundle.putString("urun_aciklama", holder.textAciklama.getText().toString());
                bundle.putString("urun_fiyat", holder.textUrunFiyat.getText().toString());
                bundle.putString("urun_fotograf", m.getUrun_fotograf());
                myFragment.setArguments(bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        TextView textUrunAdi;
        TextView textAciklama;
        TextView textUrunFiyat;
        ImageView urunImage;
        Button buttonDetail;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textUrunAdi = itemView.findViewById(R.id.textUrunAdi);
            textUrunFiyat = itemView.findViewById(R.id.textUrunFiyat);
            textAciklama = itemView.findViewById(R.id.textUrunAciklama);
            urunImage = itemView.findViewById(R.id.urunImage);
            buttonDetail = itemView.findViewById(R.id.buttonDetay);

        }
    }
}