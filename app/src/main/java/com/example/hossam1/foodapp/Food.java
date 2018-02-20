package com.example.hossam1.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.hossam1.foodapp.models.category;
import com.example.hossam1.foodapp.models.food_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Food extends AppCompatActivity {

    RecyclerView recycler_food;
    FirebaseDatabase database_food ;
    DatabaseReference food_reference;
    String category_id;
    FirebaseRecyclerAdapter<food_model,food_viewholder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        database_food=FirebaseDatabase.getInstance();
        food_reference=database_food.getReference("Foods");

        recycler_food=findViewById(R.id.recycler_food);
        recycler_food.setLayoutManager(new LinearLayoutManager(this));

        if (getIntent()!=null)
            category_id=getIntent().getStringExtra("ctegoryid");

        if (!category_id.isEmpty()&& category_id!=null){
            
            loadfood(category_id);
            
        }





    }

    private void loadfood(String category_id) {

      adapter=new FirebaseRecyclerAdapter<food_model, food_viewholder>(food_model.class,
              R.layout.food_item,food_viewholder.class,
              food_reference.orderByChild("MenuId").equalTo(category_id)
              ) {
          @Override
          protected void populateViewHolder(food_viewholder viewHolder, food_model model, int position) {

              viewHolder.food_Name.setText(model.getName());
              Picasso.with(getBaseContext()).load(model.getImage())
                      .into(viewHolder.food_Image);

               final food_model local =model;
               viewHolder.setItemClickListener(new ItemClickListener() {
                   @Override
                   public void onclick(View view, int position, Boolean isLongClick) {


                       Intent intent =new Intent(Food.this,Food_detail.class);
                       intent.putExtra("food_id",adapter.getRef(position).getKey());
                       startActivity(intent);



                   }
               });
          }
      };
        recycler_food.setAdapter(adapter);
    }


}
