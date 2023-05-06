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
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Calendar;

public class UploadActivity extends AppCompatActivity{

    private static final int PICK_IMG = 1;
    private ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private int uploads = 0;
    ImageView faceImage1,faceImage2,faceImage3,muzzleImage1,muzzleImage2,muzzleImage3;
    Button saveButton;
    EditText petName,age,breed,tagNo,medicalHist;
    String imageURL1,imageURL2,imageURL3,imageURL4,imageURL5,imageURL6;
//    Uri uri;
    Uri uri1,uri2,uri3,uri4,uri5,uri6;
    TextView textView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        faceImage1=findViewById(R.id.face1);
        faceImage2=findViewById(R.id.face2);
        faceImage3=findViewById(R.id.face3);
        muzzleImage1=findViewById(R.id.muzzle1);
        muzzleImage2=findViewById(R.id.muzzle2);
        muzzleImage3=findViewById(R.id.muzzle3);
        petName=findViewById(R.id.pet_name);
        age=findViewById(R.id.age_text);
        breed=findViewById(R.id.breed_txt);
        tagNo=findViewById(R.id.tag_no);
        medicalHist=findViewById(R.id.medical_txt);
        saveButton=findViewById(R.id.saveButton);

        ActivityResultLauncher<Intent> activityResultLauncher1=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data1= result.getData();
                            uri1 = data1.getData();
                            faceImage1.setImageURI(uri1);

                        }
                        else{
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        ActivityResultLauncher<Intent> activityResultLauncher2=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data2= result.getData();
                            uri2 = data2.getData();
                            faceImage2.setImageURI(uri2);
                        }
                        else{
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        ActivityResultLauncher<Intent> activityResultLauncher3=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data3= result.getData();
                            uri3 = data3.getData();
                            faceImage3.setImageURI(uri3);

                        }
                        else{
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        ActivityResultLauncher<Intent> activityResultLauncher4=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data4= result.getData();
                            uri4 = data4.getData();
                            muzzleImage1.setImageURI(uri4);

                        }
                        else{
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        ActivityResultLauncher<Intent> activityResultLauncher5=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data5= result.getData();
                            uri5 = data5.getData();
                            muzzleImage2.setImageURI(uri5);

                        }
                        else{
                            Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        ActivityResultLauncher<Intent> activityResultLauncher6=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data6= result.getData();
                            uri6= data6.getData();
                            muzzleImage3.setImageURI(uri6);

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
                Intent photoPicker1 = new Intent(Intent.ACTION_PICK);
                photoPicker1.setType("image/*");
                activityResultLauncher1.launch(photoPicker1);
            }
        });

        faceImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker2 = new Intent(Intent.ACTION_PICK);
                photoPicker2.setType("image/*");
                activityResultLauncher2.launch(photoPicker2);
            }
        });
        faceImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker3 = new Intent(Intent.ACTION_PICK);
                photoPicker3.setType("image/*");
                activityResultLauncher3.launch(photoPicker3);
            }
        });
        muzzleImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker4 = new Intent(Intent.ACTION_PICK);
                photoPicker4.setType("image/*");
                activityResultLauncher4.launch(photoPicker4);
            }
        });
        muzzleImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker5 = new Intent(Intent.ACTION_PICK);
                photoPicker5.setType("image/*");
                activityResultLauncher5.launch(photoPicker5);
            }
        });
        muzzleImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker6 = new Intent(Intent.ACTION_PICK);
                photoPicker6.setType("image/*");
                activityResultLauncher6.launch(photoPicker6);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    public void saveData() {
//        final StorageReference ImageFolder =  FirebaseStorage.getInstance().getReference().child("ImageFolder");
//            for (uploads=0; uploads < ImageList.size(); uploads++) {
//                Uri Image  = ImageList.get(uploads);
//                final StorageReference imagename = ImageFolder.child("image/"+Image.getLastPathSegment());
//
//                imagename.putFile(ImageList.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                url = String.valueOf(uri);
////                                SendLink(url);
//                            }
//                        });
//                    }
//                });
//        }
        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri1.getLastPathSegment());
        StorageReference storageReference2 = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri2.getLastPathSegment());
        StorageReference storageReference3 = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri3.getLastPathSegment());
        StorageReference storageReference4 = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri4.getLastPathSegment());
        StorageReference storageReference5 = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri5.getLastPathSegment());
        StorageReference storageReference6 = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri6.getLastPathSegment());


        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference1.putFile(uri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage1 = uriTask.getResult();
                imageURL1 = urlImage1.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
        storageReference2.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask2  = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask2.isComplete()) ;
                Uri urlImage2= uriTask2.getResult();
                imageURL2 = urlImage2.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
        storageReference3.putFile(uri3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask3 = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask3.isComplete()) ;
                Uri urlImage3 = uriTask3.getResult();
                imageURL3 = urlImage3.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
        storageReference4.putFile(uri4).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask4 = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask4.isComplete()) ;
                Uri urlImage4 = uriTask4.getResult();
                imageURL4 = urlImage4.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
        storageReference5.putFile(uri5).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask5 = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask5.isComplete()) ;
                Uri urlImage5 = uriTask5.getResult();
                imageURL5 = urlImage5.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
        storageReference6.putFile(uri6).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask6 = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask6.isComplete()) ;
                Uri urlImage6 = uriTask6.getResult();
                imageURL6 = urlImage6.toString();
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
        DataClass dataClass = new DataClass(PetName,Age,Breed,TagNo,MedicalHist,imageURL1,imageURL2,imageURL3,imageURL4,imageURL5,imageURL6);
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