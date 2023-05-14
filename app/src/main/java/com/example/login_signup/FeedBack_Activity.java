package com.example.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        EditText editN=(EditText) findViewById(R.id.feed_name);
        EditText editM=(EditText) findViewById(R.id.feedback_msg);
        Button btn=(Button) findViewById(R.id.se_button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("message/html");
                i.putExtra(Intent.EXTRA_EMAIL,new String("abcd@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT,"FeedBack From APP");
                i.putExtra(Intent.EXTRA_TEXT,"Name:"+editN.getText()+"\nMessage:"+editM.getText());
                try{
                    startActivity(Intent.createChooser(i,"Please Select Email"));
                }
                catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(FeedBack_Activity.this,"There are no Email Clients",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}