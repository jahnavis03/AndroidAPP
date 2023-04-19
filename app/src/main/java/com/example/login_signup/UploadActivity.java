package com.example.login_signup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class UploadActivity extends AppCompatActivity {

    ImageView faceImage1,faceImage2,faceImage3,muzzleImage1,muzzleImage2,muzzleImage3;
    Button saveButton;
    EditText petName,age,breed,tagNo,medicalHist;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        faceImage1=findViewById(R.id.face1);
//        faceImage2=findViewById(R.id.face2);
//        faceImage3=findViewById(R.id.face3);
//        muzzleImage1=findViewById(R.id.muzzle1);
//        muzzleImage2=findViewById(R.id.muzzle2);
//        muzzleImage3=findViewById(R.id.muzzle3);
        petName=findViewById(R.id.pet_name);
        age=findViewById(R.id.age_text);
        breed=findViewById(R.id.breed_txt);
        tagNo=findViewById(R.id.tag_no);
        medicalHist=findViewById(R.id.medical_txt);
        saveButton=findViewById(R.id.saveButton);

        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data= result.getData();
//                            Intent data2= result.getData();
                            uri = data.getData();
//                            uri2 = data2.getData();
//                            uploadImage.setImageURI(uri);
                            faceImage1.setImageURI(uri);
//                            faceImage2.setImageURI(uri2);
//                            faceImage3.setImageURI(uri);
//                            muzzleImage1.setImageURI(uri);
//                            muzzleImage2.setImageURI(uri);
//                            muzzleImage3.setImageURI(uri);
                        }
                        else{
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        faceImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker1= new Intent(Intent.ACTION_PICK);
                photoPicker1.setType("image/*");
//                photoPicker1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                activityResultLauncher.launch(photoPicker1);
//                startActivityForResult(Intent.createChooser(photoPicker1,"Select Picture"), 3);
            }
        });
//        faceImage2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoPicker2 = new Intent(Intent.ACTION_PICK);
//                photoPicker2.setType("image/*");
//                activityResultLauncher.launch(photoPicker2);
//            }
//        });
//        faceImage3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//        muzzleImage1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//        muzzleImage2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//        muzzleImage3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    public void saveData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());
//        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child("Android Images")
//                .child(uri2.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage1 = uriTask.getResult();
                imageURL = urlImage1.toString();
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
        String PetName = petName.getText().toString();
        String Age = age.getText().toString();
        String Breed = breed.getText().toString();
        String TagNo = tagNo.getText().toString();
        String MedicalHist = medicalHist.getText().toString();
        DataClass dataClass = new DataClass(PetName,Age,Breed,TagNo,MedicalHist, imageURL);
        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Android Tutorials").child(TagNo)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}