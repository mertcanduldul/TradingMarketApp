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
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private Button buttonKayıtGecisYap, buttonLoginGirisYap;
    private TextInputEditText textLoginMail, textLoginPassword;
    private String kullanicimail, kullanicisifre;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        buttonKayıtGecisYap = findViewById(R.id.buttonKayıtGecisYap);
        buttonLoginGirisYap = findViewById(R.id.buttonLoginGirisYap);
        textLoginMail = findViewById(R.id.textLoginMail);
        textLoginPassword = findViewById(R.id.textLoginPassword);


        List<Urun> urunList = new ArrayList<>();
        urunList.add(new Urun(0, "Monster Abra", "monster", "Intel i7-8750 1050Tİ", 4500));
        urunList.add(new Urun(1, "Lenovo Legion", "lenovo", "Intel i5-8300 1050", 5500));
        urunList.add(new Urun(2, "MSİ MF", "msi", "Intel i7-9750 2060", 4500));

        Kullanici kullanici = new Kullanici();
        kullanici.setKullanici_adi("mertcanduldul");
        kullanici.setKullanici_sifre("123");
        kullanici.setKullanici_urunler(urunList);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("kullanici");
        //kullanici.setKullanici_urunler(urunList);
        //myRef.push().setValue(kullanici);


        buttonLoginGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMail = textLoginMail.getText().toString();
                String strPassword = textLoginPassword.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("kullanici");
                myRef.addValueEventListener(new ValueEventListener() {
                    List<Kullanici> listKullanici = new ArrayList<>();
                    int i = -1;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            i++;
                            Kullanici k1 = d.getValue(Kullanici.class);
                            String key = d.getKey();
                            k1.setKullanici_id(key);
                            listKullanici.add(k1);
                            String a = k1.getKullanici_adi().toString();
                            String b = k1.getKullanici_sifre().toString();

                            if (a.equals(strMail) && b.equals(strPassword)) {
                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("username",a);
                                startActivity(intent);


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

        setKullanicimail(textLoginMail.getText().toString());
        setKullanicisifre(textLoginPassword.getText().toString());

    }

    public String getKullanicimail() {
        return kullanicimail;
    }

    public void setKullanicimail(String kullanicimail) {
        this.kullanicimail = kullanicimail;
    }

    public String getKullanicisifre() {
        return kullanicisifre;
    }

    public void setKullanicisifre(String kullanicisifre) {
        this.kullanicisifre = kullanicisifre;
    }
}
