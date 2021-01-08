package com.mertcanduldul.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingUpActivity extends AppCompatActivity {
    private TextInputEditText textFullName, textEmail, textPassword;
    private MaterialButton buttonCreateAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup_layout);

        textFullName = findViewById(R.id.textFullName);
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);


        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFullName = textFullName.getText().toString();
                String strEmail = textEmail.getText().toString();
                String strPassword = textPassword.getText().toString();
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = db.getReference("kullanici");


                dbRef.addValueEventListener(new ValueEventListener() {
                    Boolean state = false;
                    int counter = 1;
                    int unique = 0;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Kullanici k1 = new Kullanici();

                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            Kullanici sorguKullanici = d.getValue(Kullanici.class);
                            if (sorguKullanici.getKullanici_adi().equals(strEmail) && unique != 2) {
                                state = true;
                                counter++;

                                if (counter == 2) {
                                    Toast.makeText(getApplicationContext(), "Kullanici Adı Kullanılıyor !", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                        }
                        if (state == false && counter == 1) {//Böyle bir kullanıcı yok
                            k1.setKullanici_adi(strEmail);
                            k1.setKullanici_sifre(strPassword);
                            k1.setKullanici_adi(strFullName);
                            dbRef.push().setValue(k1);
                            Toast.makeText(getApplicationContext(), "Hesap Oluşturuldu", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            state = true;
                            counter = 99;
                            unique = 2;
                        }
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String key = ds.getKey();
                            k1.setKullanici_id(key);
                            Map<String, Object> kullaniciMap = new HashMap<>();
                            kullaniciMap.put("kullanici_id", key);
                            dbRef.child(key).updateChildren(kullaniciMap);
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
