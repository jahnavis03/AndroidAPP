package com.example.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.auth.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;


public class signup extends AppCompatActivity {
    EditText username;
    EditText name;
    EditText phone;
    EditText address;
    EditText occupation;
    EditText password;
    EditText confirm_password;
    EditText DOB;
    Button confirm;
    TextView already_account;
    EditText email;
    FirebaseAuth mAuth;
    ProgressBar progressbar;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    FirebaseFirestore firebaseFirestore;
    String USERS="users";
    //    String TAG="Signup Activity";
    User user;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.inputuserName);
        name = findViewById(R.id.inputLastName);
        phone = findViewById(R.id.inputPhone);
        address = findViewById(R.id.inputAddress);
        occupation = findViewById(R.id.inputOccupation);
        password = findViewById(R.id.inputPassword);
        confirm_password = findViewById(R.id.inputConfirmPassword);
        confirm = findViewById(R.id.loginButton);
        DOB = findViewById(R.id.inputDOB);
        email = findViewById(R.id.inputEmail);
        progressbar = findViewById(R.id.progressBar);
        already_account = findViewById(R.id.haveAcc);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("users");
        firebaseFirestore=FirebaseFirestore.getInstance();
        already_account.setMovementMethod(LinkMovementMethod.getInstance());
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this, login.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                String Email, Password, Username, Confirm_P, Phone_No, Name, Address, Occupation, Dob;
                Email = String.valueOf(email.getText());
                Name = String.valueOf(email.getText());
                Dob = String.valueOf(DOB.getText());
                Occupation = String.valueOf(occupation.getText());
                Address = String.valueOf(address.getText());
                Password = String.valueOf(password.getText());
                Username = String.valueOf(username.getText());
                Confirm_P = String.valueOf(confirm_password.getText());
                Phone_No = String.valueOf(phone.getText());
                String MobilePattern = "[0-9]{10}";

                if (TextUtils.isEmpty(Username)) {
                    Toast.makeText(signup.this, "enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(signup.this, "enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Phone_No)) {
                    Toast.makeText(signup.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(signup.this, "enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Confirm_P)) {
                    Toast.makeText(signup.this, "Confirm your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Password.equals(Confirm_P)) {
                    Toast.makeText(signup.this, "Confirm password is not correct", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.length() < 6) {
                    Toast.makeText(signup.this, "Enter a password with atleast 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Phone_No.matches(MobilePattern)) {
                    Toast.makeText(signup.this, "Enter Valid Phone Number", Toast.LENGTH_LONG).show();
                    return;
                }
                Query usernameQuery = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("username").equalTo(Username);
                usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.getChildrenCount()>0){
                            Toast.makeText(signup.this,"USER already exists",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            User user = new User(Username, Name, Email, Phone_No, Dob, Occupation, Address,Password);
                            mDatabase.child(Username).setValue(user);
                            Toast.makeText(signup.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                User user = new User(Username, Name, Email, Phone_No, Dob, Occupation, Address,Password);
//                mDatabase.child(Username).setValue(user);


            }
        });
    }
    public void updateUI(FirebaseUser currentUser) {
        String keyId = mDatabase.push().getKey();
        mDatabase.child(keyId).setValue(user); //adding user info to database
        Intent loginIntent = new Intent(signup.this, MainActivity.class);
        startActivity(loginIntent);}
}
