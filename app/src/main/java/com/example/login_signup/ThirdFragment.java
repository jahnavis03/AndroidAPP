package com.example.login_signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ThirdFragment extends Fragment {

    Button Profile;
    Button Themes;
    Button Transfer_req;
    Button Feedback;
    Button Demo;
    Button Change_Password;
    Button Logout;
    FirebaseAuth auth;
    FirebaseUser user;

    public ThirdFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_third, container, false);
    }



        public void onViewCreated(View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);

            Profile = (Button) view.findViewById(R.id.profile);
            Feedback=(Button)view.findViewById(R.id.feedback);
            Demo=(Button)view.findViewById(R.id.instructions);
            Change_Password=(Button)view.findViewById(R.id.change_password);
            Logout=(Button)view.findViewById(R.id.logout);
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
                    Intent intent = new Intent(getActivity(), login.class);
                    getActivity().startActivity(intent);
                }
            });

            Profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Profile.class);
                    getActivity().startActivity(intent);
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
//            Feedback.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), Feedback.class);
//                    getActivity().startActivity(intent);
//                }
//            });
//            Demo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), Instruction.class);
//                    getActivity().startActivity(intent);
//                }
//            });
//            Change_Password.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), Password_change.class);
//                    getActivity().startActivity(intent);
//                }
//            });
//

        }
}
