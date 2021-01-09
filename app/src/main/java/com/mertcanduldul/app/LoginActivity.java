package com.mertcanduldul.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class LoginActivity extends AppCompatActivity {
    private Button buttonKayıtGecisYap, buttonLoginGirisYap;
    private TextInputEditText textLoginMail, textLoginPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        buttonKayıtGecisYap = findViewById(R.id.buttonKayıtGecisYap);
        buttonLoginGirisYap = findViewById(R.id.buttonLoginGirisYap);
        textLoginMail = findViewById(R.id.textLoginMail);
        textLoginPassword = findViewById(R.id.textLoginPassword);


        buttonLoginGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMail = textLoginMail.getText().toString();
                String strPassword = textLoginPassword.getText().toString();

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference loginRef = db.getReference("kullanici");
                loginRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot c : dataSnapshot.getChildren()) {
                            String key = c.getKey();
                            Kullanici k1=c.getValue(Kullanici.class);


                            String dbUsername = k1.getKullanici_adi().toString();
                            String dbPassword = k1.getKullanici_sifre().toString();
                            String strFullName = k1.getKullanici_fullname().toString();

                            if (dbUsername.equals(strMail) && dbPassword.equals(strPassword)) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("userfullname", strFullName);
                                intent.putExtra("username",dbUsername);
                                intent.putExtra("userkey", key);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        throw databaseError.toException();
                    }
                });
            }
        });
        buttonKayıtGecisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);

            }
        });
    }
}
