package com.example.hossam1.foodapp.models;

/**
 * Created by hossam1 on 2/18/18.
 */

public class food_model {

    private String name ,image,Description,Price,Discount,MenuId;

    public food_model() {
    }

    public food_model(String name, String image, String description, String price, String discount, String menuId) {
        this.name = name;
        this.image = image;
        Description = description;
        Price = price;
        Discount = discount;
        MenuId = menuId;
    }


    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return Price;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getMenuId() {
        return MenuId;
    }
}
