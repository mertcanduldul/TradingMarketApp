package com.mertcanduldul.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileActivity extends Fragment {
    private TextView textName, textAdres;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);

        textName = view.findViewById(R.id.textName);
        textAdres = view.findViewById(R.id.textAdres);

        if (getArguments()!=null){
            String strbundle1 =this.getArguments().getString("username");
            //String strbundle2 = bundle.getString("pwd");
            textName.setText(strbundle1);
            // textAdres.setText(strbundle2);
        }
        return view;

    }
}
