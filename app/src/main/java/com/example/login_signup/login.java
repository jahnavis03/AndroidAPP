package com.example.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.parse.ParseUser;


public class login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button otp;
    Button mail;
    Button signup;
    ProgressBar progressbar;
    FirebaseAuth mAuth;
    TextView forgot_pass;
    Button Profile;

    ProgressDialog loginprogress;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
//            updateUI(currentUser);
            Intent main= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(main);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Profile = findViewById(R.id.profile);
        username= findViewById(R.id.inputUser);
        password = findViewById(R.id.inputPasswordLog);
        login = findViewById(R.id.loginButton);
        mAuth = FirebaseAuth.getInstance();
        otp = findViewById(R.id.otpBtn);
        mail = findViewById(R.id.mailBtn);
        signup = findViewById(R.id.signuplog);
        progressbar=findViewById(R.id.progressBar);
        forgot_pass=findViewById(R.id.forgotPassword);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, signup.class));
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, otp.class));
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, mail.class));
            }
        });
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, forgot_Password.class));
            }
        });
//        Profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(login.this, Profile.class);
//                startActivity(intent);
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressbar.setVisibility(View.VISIBLE);
//                String Email, Password;
//                Email = String.valueOf(email.getText());
                String userUsername = username.getText().toString().trim();
                String userPassword = password.getText().toString().trim();
//                Password = String.valueOf(userPassword.getText());

                if (TextUtils.isEmpty(userUsername)) {
                    Toast.makeText(login.this, "enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userPassword)) {
                    Toast.makeText(login.this, "enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            username.setError(null);
                            String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                            if (passwordFromDB.equals(userPassword)){
                                username.setError(null);
                            String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                            String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                            String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                                String phoneFromDB = snapshot.child(userUsername).child("phone").getValue(String.class);
                                String addressFromDB = snapshot.child(userUsername).child("address").getValue(String.class);
                                String occupationFromDB = snapshot.child(userUsername).child("occupation").getValue(String.class);
                                String dobFromDB = snapshot.child(userUsername).child("dob").getValue(String.class);


                            Intent intent = new Intent(login.this,Profile.class);

                            intent.putExtra("Name", nameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("password", passwordFromDB);
                                intent.putExtra("phone", phoneFromDB);
                                intent.putExtra("address", addressFromDB);
                                intent.putExtra("occupation", occupationFromDB);
                                intent.putExtra("dob", dobFromDB);

                                Intent main= new Intent(login.this,MainActivity.class);

                            startActivity(main);
                        } else {
                            password.setError("Invalid Credentials");
                            password.requestFocus();
                        }
                    } else {
                        username.setError("User does not exist");
                        username.requestFocus();
                    }
                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                mAuth.signInWithEmailAndPassword(Email, Password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressbar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
////                                    Log.d(TAG, "signInWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
//
//                                    Toast.makeText(getApplicationContext(),"LOGIN successful",Toast.LENGTH_SHORT).show();
//                                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    // If sign in fails, display a message to the user.
////                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                    Toast.makeText(login.this, "LOGIN failed.",
//                                            Toast.LENGTH_SHORT).show();
////                                    updateUI(null);
//                                }
//                            }
//                        });
//            }
//
//            });

    };});}
//    public void updateUI(FirebaseUser currentUser) {
//        Intent profileIntent = new Intent(getApplicationContext(), Profile.class);
//        profileIntent.putExtra("email", currentUser.getEmail());
//        Log.v("DATA", currentUser.getUid());
//        startActivity(profileIntent);
//    }
}