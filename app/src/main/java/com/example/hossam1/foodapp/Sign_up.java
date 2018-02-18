package com.example.hossam1.foodapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossam1.foodapp.models.common;
import com.example.hossam1.foodapp.models.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up extends AppCompatActivity {

    Button signup ;
    TextView login ;
    EditText signup_email ,signup_pass ,signup_name;
    private FirebaseAuth mAuth;
    ProgressBar progressBar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup=findViewById(R.id.Signup_activity);
        login=findViewById(R.id.text_login);
        signup_email=findViewById(R.id.signup_Email);
        signup_pass=findViewById(R.id.signup_pass);
        progressBar=findViewById(R.id.pro_bar_signup);
        signup_name=findViewById(R.id.signup_name);


        mAuth=FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Sign_up.this,login.class));
                finish();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                signupusers();
            }
        });

    }

    private void signupusers() {
       final String email = signup_email.getText().toString();
       final String pass = signup_pass.getText().toString();
       final String name = signup_name.getText().toString();

        if (name.isEmpty()){
            signup_name.setError("Name required");
            signup_name.requestFocus();
        }


        if (email.isEmpty()){
            signup_email.setError("email required");
            signup_email.requestFocus();
        }
        if (pass.isEmpty()){
            signup_pass.setError("pass is required");
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signup_email.setError("Enter vaild Email");
            signup_email.requestFocus();
            return;
        }

        if (pass.length()<6){
            signup_pass.setError("password must greater than 6 ");
            signup_pass.requestFocus();
            return;
        }

//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final  DatabaseReference myRef = database.getReference("users");
      final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        String id = myRef.push().getKey();
        final users user = new users(id,email,name,pass);
        myRef.child(id).setValue(user);



     progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            progressBar.setVisibility(View.GONE);

                            startActivity(new Intent(Sign_up.this,login.class));
                            finish();
                        }
                    }
                });

    }


}
