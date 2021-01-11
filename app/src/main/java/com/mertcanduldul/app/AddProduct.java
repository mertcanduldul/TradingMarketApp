package com.mertcanduldul.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddProduct extends Fragment {
    private EditText textCityname, textProductName, textProductPrice, textProductDescription;
    private Button buttonProductAdd, buttonProductPhotoPicker, buttonuploadimage;
    private ImageView imageProductPhoto;

    List<Urun> urunList;

    public Uri imguri;
    StorageReference mStorageRef;
    private StorageTask uploadTask;
    private final static int IMG_REQUEST_ID = 10;
    private final static int RESULT_OK = -1;

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
        imageProductPhoto = view.findViewById(R.id.imageProductPhoto);

        buttonProductAdd.setEnabled(false);


        mStorageRef = FirebaseStorage.getInstance().getReference("Images/" + UUID.randomUUID().toString());
        buttonProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCityname = textCityname.getText().toString();
                String strProductname = textProductName.getText().toString();
                String strProductprice = textProductPrice.getText().toString();
                String strProductDescription = textProductDescription.getText().toString();


                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference myref = db.getReference("urun");

                mStorageRef.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    String mDownloadUrl;

                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                        firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                mDownloadUrl = uri.toString();

                                myref.addValueEventListener(new ValueEventListener() {
                                    Urun u1 = new Urun();
                                    Boolean unique = true;

                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (unique) {
                                            Bundle bundle = getArguments();
                                            if (bundle != null) {
                                                String userfullname = getArguments().getString("userfullname");
                                                String username = getArguments().getString("username");
                                                String userkey = getArguments().getString("userkey");
                                                Date d = new Date();
                                                CharSequence s = DateFormat.format("MMMM d, yyyy ", d.getTime());


                                                u1.setUrun_id("");
                                                u1.setUrun_adi(strProductname);
                                                u1.setUrun_aciklama(strProductDescription);
                                                u1.setUrun_fiyat(Integer.parseInt(strProductprice));
                                                u1.setUrun_fotograf(mDownloadUrl);
                                                u1.setUrun_sahibi_id(userkey);
                                                u1.setUrun_lokasyon(strCityname);
                                                u1.setUrun_yuklenme_tarih(s + "");

                                                myref.push().setValue(u1);
                                                buttonProductAdd.setText("Urun Eklendi.");
                                                textCityname.setText("");
                                                textProductName.setText("");
                                                textProductPrice.setText("");
                                                textProductDescription.setText("");
                                                Toast.makeText(getActivity(), "URUN EKLENDİ !", Toast.LENGTH_SHORT).show();
                                            }

                                            unique = false;
                                        }
                                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                                            String key = d.getKey();
                                            u1.setUrun_id(key);
                                            Map<String, Object> urunMap = new HashMap<>();
                                            urunMap.put("urun_id", key);
                                            myref.child(key).updateChildren(urunMap);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        buttonProductPhotoPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonProductAdd.setEnabled(true);
                requestImage();
            }

        });
        return view;
    }

    private void requestImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), IMG_REQUEST_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imguri = data.getData();
        if (requestCode == IMG_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri);
                imageProductPhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}