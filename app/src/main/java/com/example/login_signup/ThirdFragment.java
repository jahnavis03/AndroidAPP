package com.example.login_signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ThirdFragment extends AppCompatActivity {

    Button Profile;
    Button Themes;
    Button Transfer_req;
    Button Feedback;
    Button Demo;
    Button Change_Password;
    Button Logout;
    FirebaseAuth auth;
    FirebaseUser user;
    Button Gallery,Capture,Settings;

    public ThirdFragment(){
        // require a empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_third);
        Gallery=findViewById(R.id.gallery);
        Capture=findViewById(R.id.capture);
        Settings=findViewById(R.id.settings);
//        Profile = findViewById(R.id.profile);
        Feedback=findViewById(R.id.feedback);
        Demo=findViewById(R.id.instructions);
        Change_Password=findViewById(R.id.change_password);
        Logout=findViewById(R.id.logout);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
//            if(user==null){
//                Intent intent=new Intent(getApplicationContext(),login.class);
//                startActivity(intent);
//                finish();
//                Intent intent = new Intent(getActivity(), login.class);
//                    getActivity().startActivity(intent);
//
//            }
//            else{
//                textView.setText(user.getEmail());
//            }

            Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(ThirdFragment.this, login.class);
                    startActivity(intent);
                }
            });

//            Profile.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(ThirdFragment.this, Profile.class);
//                    startActivity(intent);
//                }
//            });
            Gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ThirdFragment.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Capture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ThirdFragment.this,secondFragment.class);
                startActivity(intent);
            }
        });

//            Themes.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), Themes.class);
//                    getActivity().startActivity(intent);
//                }
//            });
//            Transfer_req.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), Transfer_requests.class);
//                    getActivity().startActivity(intent);
//                }
//            });
            Feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ThirdFragment.this,FeedBack_Activity.class);
                    startActivity(intent);
                }
            });
            Demo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ThirdFragment.this,instruction.class);
                    startActivity(intent);
                }
            });
            Change_Password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ThirdFragment.this,ChangePassword.class);
                    startActivity(intent);
                }
            });
//

        }
}
