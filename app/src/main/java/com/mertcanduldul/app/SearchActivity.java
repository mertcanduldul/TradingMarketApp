package com.mertcanduldul.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Fragment {
    private Button detailButton;
    List<Urun> mList = new ArrayList<>();
    private CustomAdapter customAdapter;
    private RecyclerView rv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);
        ImageView img = view.findViewById(R.id.imageView2);

        FirebaseStorage dbstorage = FirebaseStorage.getInstance();
        StorageReference uploadref = dbstorage.getReference("Images");

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myref = db.getReference("urun");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getArguments() != null) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Urun u2 = d.getValue(Urun.class);
                        String key = d.getKey();
                        mList.add(u2);
                        Bundle b = new Bundle();
                        b.putString("productowner", u2.getUrun_sahibi_id());

                        String userfullname = getArguments().getString("userfullname");
                        String username = getArguments().getString("username");
                        String userkey = getArguments().getString("userkey");

                        rv = view.findViewById(R.id.rv);
                        customAdapter = new CustomAdapter(mList, getContext());
                        customAdapter.setUserkey(userkey);
                        customAdapter.setUserfullname(userfullname);
                        customAdapter.setUsername(username);
                        rv.setAdapter(customAdapter);
                        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        rv = view.findViewById(R.id.rv);
        customAdapter = new CustomAdapter(mList, getContext());
        rv.setAdapter(customAdapter);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        Toast.makeText(getActivity(), "URUN ARAMAK İÇİN SEARCHLE !", Toast.LENGTH_LONG).show();
        return view;
    }
}
