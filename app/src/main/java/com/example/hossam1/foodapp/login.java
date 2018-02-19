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

public class login extends AppCompatActivity {

    TextView signup;
    EditText login_email,login_pass;
    Button login ;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.text_signup);
        login_email=findViewById(R.id.login_Email);
        login_pass=findViewById(R.id.login_pass);
        login=findViewById(R.id.login);
        progressBar=findViewById(R.id.pro_bar_login);
        mAuth = FirebaseAuth.getInstance();
        Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        signup.setTypeface(typeface);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(login.this,Sign_up.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginuser();

            }
        });

    }

    private void loginuser() {

        final String email = login_email.getText().toString();
        final String pass= login_pass.getText().toString();

        if (email.isEmpty()){
            login_email.setError("email required");
            login_email.requestFocus();
        }
        if (pass.isEmpty()){
            login_pass.setError("pass is required");
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            login_email.setError("Enter vaild Email");
            login_email.requestFocus();
            return;
        }

        if (pass.length()<6){
            login_pass.setError("password must greater than 6 ");
            login_pass.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(login.this,Home.class));
                            finish();
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(login.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}
