package com.mertcanduldul.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileActivity extends Fragment {
    private TextView textName, textAdres;
    private Button buttonProfileAddProduct;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);

        textName = view.findViewById(R.id.textName);
        textAdres = view.findViewById(R.id.textAdres);
        buttonProfileAddProduct = view.findViewById(R.id.buttonProfileAddProduct);

        if (getArguments() != null) {
            String username = this.getArguments().getString("username");
            String userkey = this.getArguments().getString("userkey");

            textName.setText(username);
        }

        buttonProfileAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments() != null) {
                    String username = getArguments().getString("username");
                    String userfullname=getArguments().getString("userfullname");
                    String userkey = getArguments().getString("userkey");


                    AddProduct ap = new AddProduct();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    bundle.putString("userfullname", userfullname);
                    bundle.putString("userkey", userkey);
                    ap.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, ap).addToBackStack(null).commit();
                }
            }
        });


        return view;

    }
}
