package com.example.login_signup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;


public class secondFragment extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    Button button,saveButton;
    ImageView imageView;
    String ImageURL;
    EditText EnterID;
    Uri uri;
    Button Gallery,Capture,Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_second);

//        button = (Button) rootView.findViewById(R.id.camera_button);
        imageView = findViewById(R.id.click_image);
        saveButton = findViewById(R.id.savebutton);
        EnterID = findViewById(R.id.enterID);
        Gallery=findViewById(R.id.gallery);
        Capture=findViewById(R.id.capture);
        Settings=findViewById(R.id.settings);
        ActivityResultLauncher<Intent> activityResultLauncher1=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data1= result.getData();
                            uri = data1.getData();
                            imageView.setImageURI(uri);

                        }
                        else{
                            Toast.makeText(secondFragment.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker1 = new Intent(Intent.ACTION_PICK);
                photoPicker1.setType("image/*");
                activityResultLauncher1.launch(photoPicker1);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage();
            }
        });
        Gallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(secondFragment.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(secondFragment.this,ThirdFragment.class);
                startActivity(intent);
            }
        });
//        return rootView;
    }

    public void saveImage(){
        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child("Clicked Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(secondFragment.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage1 = uriTask.getResult();
                ImageURL = urlImage1.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void uploadData(){
        String TagNo=EnterID.getText().toString();
        PicClass picClass= new PicClass(TagNo,ImageURL);
        FirebaseDatabase.getInstance().getReference("input").child(TagNo)
                .setValue(picClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(secondFragment.this, "Saved", Toast.LENGTH_SHORT).show();
//                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(secondFragment.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}