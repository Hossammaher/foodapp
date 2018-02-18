package com.example.hossam1.foodapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class complete_user_info extends AppCompatActivity {


    CircleImageView img;
    EditText name , phone ;
    ProgressBar progressBar ;
    Button start ;
    Uri profile_img_uri;
    String Url_profile;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_user_info);
        img=(CircleImageView) findViewById(R.id.profile_img);
        name=findViewById(R.id.username);
        phone=findViewById(R.id.user_phone);
        progressBar=findViewById(R.id.pro_bar_info);
        start=findViewById(R.id.start);
        mAuth=FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"choose"),1);

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addinfo();
            }
        });


    }

    private void uploadImg() {

        StorageReference reference = FirebaseStorage.getInstance().getReference("profile/" + System.currentTimeMillis() + ".jpg");

        if (profile_img_uri!=null){
            reference.putFile(profile_img_uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override

                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Url_profile = taskSnapshot.getDownloadUrl().toString();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(complete_user_info.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    private void addinfo(){

        String username = name.getText().toString();
        String userphone =phone.getText().toString();

        if (username.isEmpty()){
            name.setError("enter name");
            name.requestFocus();
            return;
        }

        if (userphone.isEmpty()){
            phone.setError("enter phone");
            phone.requestFocus();
            return;
        }

        FirebaseUser u = mAuth.getCurrentUser();

        if (u!=null){
            progressBar.setVisibility(View.VISIBLE);
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .setPhotoUri(Uri.parse(Url_profile))
                    .build();

            u.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {

                                startActivity(new Intent(complete_user_info.this, Home.class));
                                finish();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            profile_img_uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),profile_img_uri);
                img.setImageBitmap(bitmap);
                uploadImg();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
