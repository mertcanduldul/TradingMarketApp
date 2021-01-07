package com.mertcanduldul.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Fragment {
    private Button detailButton;
    List<ModalClass> mList;
    CustomAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);

        RecyclerView rv = view.findViewById(R.id.rv);
        customAdapter = new CustomAdapter(mList, getContext());
        rv.setAdapter(customAdapter);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Oncreate Metodu içinde veriler eklenmeli yoksa null olan bir context tanımlanır
        //bu da programın çökmesine sebeptir
        mList = new ArrayList<>();
        mList.add(new ModalClass(1, "Monster Abra", "monster", "Intel i7-8750 1050Tİ", 4500));
        mList.add(new ModalClass(2, "Lenovo Legion", "lenovo", "Intel i5-8300 1050", 4500));
        mList.add(new ModalClass(3, "MSİ MF", "msi", "Intel i7-9750 2060", 4500));



    }
}
