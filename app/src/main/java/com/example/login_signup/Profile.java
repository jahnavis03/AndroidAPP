package com.example.login_signup;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.Map;

public class Profile extends AppCompatActivity {
    private TextView occupationTxtView, nameTxtView, addressTxtView;
    private TextView emailTxtView, phoneTxtView, dobTxtView, passwordTxtView, usernameTxtView;
    private ImageView emailImageView, phoneImageView, dobImageView;
    private ImageView passwordImageView, addressView;
    //    private CircleImageView userImageView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    Button profile,back;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    String userid;
    String USERS = "users";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //receive data from login screen
//        Intent intent = getIntent();
//        email = intent.getStringExtra("email");
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference userRef = rootRef.child(USERS);
//        Log.v("USERID", userRef.getKey());
        profile =findViewById(R.id.back);
        occupationTxtView = findViewById(R.id.occ_view);
        nameTxtView = findViewById(R.id.name_textview);
        emailTxtView = findViewById(R.id.email_textview);
        phoneTxtView = findViewById(R.id.phone_textview);
        dobTxtView = findViewById(R.id.dob_textt);
        passwordTxtView = findViewById(R.id.password_textview1);
        addressTxtView = findViewById(R.id.address_textview);
        usernameTxtView = findViewById(R.id.username_view);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this,MainActivity.class);
                startActivity(intent);
            }
        });

//        userImageView = findViewById(R.id.user_imageview);
        emailImageView = findViewById(R.id.email_imageview);
        phoneImageView = findViewById(R.id.phone_imageview);
        dobImageView = findViewById(R.id.dob_image);
        passwordImageView = findViewById(R.id.password_imageview);
        addressView = findViewById(R.id.address_imageview);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Profile.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        showUserData();
    }

    public void showUserData() {
        firebaseAuth=FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        mDatabase=database.getReference("users");

        Intent intent = getIntent();
        String Email, Password, Username, Phone_No, Name, Address, Occupation, Dob;
        Username = intent.getStringExtra("username");
        Query query=mDatabase.orderByChild("username").equalTo(Username);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String Email, Password, Username, Phone_No, Name, Address, Occupation, Dob;
                    Name = ""+ds.child("name").getValue();
                    Email = ""+ds.child("email").getValue();
                    Occupation = ""+ds.child("occupation").getValue();
                    Address = ""+ds.child("address").getValue();
                    Phone_No = ""+ds.child("phone").getValue();
                    Dob = ""+ds.child("dob").getValue();
                    Password = ""+ds.child("password").getValue();
                    Username = ""+ds.child("username").getValue();

                    nameTxtView.setText(Name);
                    emailTxtView.setText(Email);
                    occupationTxtView.setText(Occupation);
                    addressTxtView.setText(Address);
                    phoneTxtView.setText(Phone_No);
                    passwordTxtView.setText(Password);
                    usernameTxtView.setText(Username);
                    dobTxtView.setText(Dob);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
//
