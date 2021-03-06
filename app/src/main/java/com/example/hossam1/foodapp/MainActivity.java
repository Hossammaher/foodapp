package com.example.hossam1.foodapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signin , signup;
    TextView solgn ;
    FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin=findViewById(R.id.signin);
        signup=findViewById(R.id.signup);
        solgn=findViewById(R.id.solgn);
        mAuth=FirebaseAuth.getInstance();
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        solgn.setTypeface(typeface);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,login.class));
                finish();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(MainActivity.this,Sign_up.class));
                finish();

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(MainActivity.this,Home.class));
        }

    }
}
