package com.example.hossam1.foodapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hossam1.foodapp.ItemClickListener;
import com.example.hossam1.foodapp.R;

/**
 * Created by hossam1 on 2/18/18.
 */

public class food_viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView food_Image;
    TextView food_Name;
    ItemClickListener itemClickListener;

    public food_viewholder(View itemView) {
        super(itemView);

        food_Image = itemView.findViewById(R.id.food_img);
        food_Name = itemView.findViewById(R.id.food_name);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onclick(view, getAdapterPosition(), false);

    }
}