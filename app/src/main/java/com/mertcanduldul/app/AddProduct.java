package com.mertcanduldul.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends Fragment {
    private EditText textCityname, textProductName, textProductPrice, textProductDescription;
    private Button buttonProductAdd, buttonProductPhotoPicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addproduct_layout, container, false);

        textCityname = view.findViewById(R.id.textCityname);
        textProductName = view.findViewById(R.id.textProductName);
        textProductPrice = view.findViewById(R.id.textProductPrice);
        textProductDescription = view.findViewById(R.id.textProductDescription);
        buttonProductAdd = view.findViewById(R.id.buttonProductAdd);
        buttonProductPhotoPicker = view.findViewById(R.id.buttonProductPhotoPicker);

        buttonProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strCityname = textCityname.getText().toString();
                String strProductname = textProductName.getText().toString();
                String strProductprice = textProductPrice.getText().toString();
                String strProductDescription = textProductDescription.getText().toString();

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference myref = db.getReference("urun");

                myref.addValueEventListener(new ValueEventListener() {
                    Boolean unique = true;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (unique) {
                            Bundle bundle = getArguments();
                            if (bundle != null) {
                                String userfullname = getArguments().getString("userfullname");
                                String username = getArguments().getString("username");
                                String userkey = getArguments().getString("userkey");

                                Urun u1 = new Urun();

                                u1.setUrun_adi(strProductname);
                                u1.setUrun_aciklama(strProductDescription);
                                u1.setUrun_fiyat(Integer.parseInt(strProductprice));
                                u1.setUrun_fotograf("monster");
                                u1.setUrun_sahibi_id(userkey);

                                myref.push().setValue(u1);
                                Toast.makeText(getActivity(), "URUN SATIŞA ÇIKTI !", Toast.LENGTH_SHORT);
                            }
                            unique = false;
                        }
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
