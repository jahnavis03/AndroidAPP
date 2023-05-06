package com.example.login_signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailPet, detailTag, detailAge,detailBreed,detailMedi;
    ImageView detailImage1,detailImage2,detailImage3,detailImage4,detailImage5,detailImage6;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl2= "";
    String imageUrl1 = "";
    String imageUrl3 = "";
    String imageUrl4 = "";
    String imageUrl5 = "";
    String imageUrl6 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailPet = findViewById(R.id.detailPet);
        detailImage1 = findViewById(R.id.detailImage1);
        detailImage2 = findViewById(R.id.detailImage2);
        detailImage3 = findViewById(R.id.detailImage3);
        detailImage4 = findViewById(R.id.detailImage4);
        detailImage5 = findViewById(R.id.detailImage5);
        detailImage6 = findViewById(R.id.detailImage6);
        detailTag = findViewById(R.id.detailTag);
        deleteButton = findViewById(R.id.deleteButton);
//        editButton = findViewById(R.id.editButton);
        detailBreed = findViewById(R.id.detailBreed);
        detailAge = findViewById(R.id.detailAge);
        detailMedi = findViewById(R.id.detailMed);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailPet.setText(bundle.getString("Pet"));
            detailTag.setText(bundle.getString("Tag"));
            detailBreed.setText(bundle.getString("Breed"));
            detailAge.setText(bundle.getString("Age"));
            detailMedi.setText(bundle.getString("Medical"));
            key = bundle.getString("Key");
            imageUrl1 = bundle.getString("Image1");
            imageUrl2 = bundle.getString("Image2");
            imageUrl3 = bundle.getString("Image3");
            imageUrl4 = bundle.getString("Image4");
            imageUrl5 = bundle.getString("Image5");
            imageUrl6 = bundle.getString("Image6");
            Glide.with(this).load(bundle.getString("Image1")).into(detailImage1);
            Glide.with(this).load(bundle.getString("Image2")).into(detailImage2);
            Glide.with(this).load(bundle.getString("Image3")).into(detailImage3);
            Glide.with(this).load(bundle.getString("Image4")).into(detailImage4);
            Glide.with(this).load(bundle.getString("Image5")).into(detailImage5);
            Glide.with(this).load(bundle.getString("Image6")).into(detailImage6);
    }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Android Tutorials");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference1=storage.getReferenceFromUrl(imageUrl1);
                StorageReference storageReference2=storage.getReferenceFromUrl(imageUrl2);
                StorageReference storageReference3=storage.getReferenceFromUrl(imageUrl3);
                StorageReference storageReference4=storage.getReferenceFromUrl(imageUrl4);
                StorageReference storageReference5=storage.getReferenceFromUrl(imageUrl5);
                StorageReference storageReference6=storage.getReferenceFromUrl(imageUrl6);
                storageReference1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });
                storageReference2.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });
                storageReference3.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });
                storageReference4.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });
                storageReference5.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });
                storageReference6.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                });

            }
        });
}}