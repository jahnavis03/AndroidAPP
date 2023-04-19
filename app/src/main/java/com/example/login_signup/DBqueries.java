package com.example.login_signup;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DBqueries {
    public static FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    public static String Username, Name, Email, Phone_No, Dob, Occupation, Address,Password;
}
