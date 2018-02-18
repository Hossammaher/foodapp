package com.example.hossam1.foodapp.models;

/**
 * Created by hossam1 on 2/16/18.
 */

public class category {

    String name ;
    String image;

    public category() {
    }

    public category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
