package com.mertcanduldul.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, new SearchActivity()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.profileActionId) {
                    tempFragment = new ProfileActivity();
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