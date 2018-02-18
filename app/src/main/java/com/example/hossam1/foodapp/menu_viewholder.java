package com.example.hossam1.foodapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hossam1 on 2/16/18.
 */

public class menu_viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
   ImageView image ;
   TextView name ;
   ItemClickListener itemClickListener ;

    public menu_viewholder(View itemView) {
        super(itemView);

        image=itemView.findViewById(R.id.menu_img);
        name=itemView.findViewById(R.id.menu_name);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onclick(view,getAdapterPosition(),false);

    }
}
