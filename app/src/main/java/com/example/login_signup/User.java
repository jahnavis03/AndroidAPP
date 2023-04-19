package com.example.login_signup;

import android.provider.ContactsContract;

import org.checkerframework.checker.units.qual.A;

public class User {
    String Email, Password,Username,Phone_No,Name,Address,Occupation,Dob;

    public User( String username,String name, String email, String phone, String dob,String occupation,
                String address, String password) {
        this.Email = email;
        this.Name = name;
        this.Occupation= occupation;
        this.Address = address;
        this.Phone_No = phone;
        this.Password = password;
        this.Username = username;
        this.Dob=dob;
    }
    public void setEmail(String email){ this.Email=email;}
    public void setPassword(String password) {
        this.Password = password;
    }
    public void setFullName(String name) {
        this.Name = name;
    }
    public void setOccupation(String occupation) {
        this.Occupation= occupation;
    }
    public void setAddress(String address) {
        this.Address = address;
    }
    public void setPhone(String phone) {
        this.Phone_No = phone;
    }
    public void setDOB(String dob) {
        this.Dob = dob;
    }
    public void setUsername(String username) {
        this.Username = username;
    }

    public String getFullName() {
        return Name;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
    public String getOccupation() {
        return Occupation;
    }
    public String getAddress() {
        return Address;
    }
    public String getPhone() {
        return Phone_No;
    }
    public String getDob() {
        return Dob;
    }
    public String getEmail() {
        return Email;
    }


}
