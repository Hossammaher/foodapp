package com.example.hossam1.foodapp;

import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.hossam1.foodapp.models.food_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Food_detail extends AppCompatActivity {

    TextView food_name , food_price,food_description;
    ImageView food_img;
    CollapsingToolbarLayout collapsingToolbarLayout ;
    FloatingActionButton floatingActionButton;
    ElegantNumberButton elegantNumberButton;
    DatabaseReference databaseReference;
    String food_id ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        databaseReference= FirebaseDatabase.getInstance().getReference("Foods");
        food_name=findViewById(R.id.food_name);
        food_price=findViewById(R.id.food_price);
        food_description=findViewById(R.id.food_description);
        food_img=findViewById(R.id.food_image);
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        floatingActionButton=findViewById(R.id.btn_card);
        elegantNumberButton=findViewById(R.id.elegant_btn);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapappbar);

        if (getIntent()!=null)
            food_id=getIntent().getStringExtra("food_id");
        if (!food_id.isEmpty()){
            getFoodDetails(food_id);
        }





    }

    private void getFoodDetails(String food_id) {

        databaseReference.child(food_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                food_model food=dataSnapshot.getValue(food_model.class);
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(food_img);
                collapsingToolbarLayout.setTitle(food.getName());
                food_name.setText(food.getName());
                food_price.setText(food.getPrice());
                food_description.setText(food.getDescription());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
