package com.mertcanduldul.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment tempFragment;
    private List<Urun> urunList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String usr = getIntent().getStringExtra("username");


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, new SearchActivity()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.profileActionId) {
                    tempFragment = new ProfileActivity();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", usr);
                    tempFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, tempFragment).commit();

                }
                if (item.getItemId() == R.id.searchActionId) {
                    tempFragment = new SearchActivity();
                }
                if (item.getItemId() == R.id.messageActionId) {
                    tempFragment = new MessageActivity();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, tempFragment).commit();
                return true;
            }
        });


    }
}