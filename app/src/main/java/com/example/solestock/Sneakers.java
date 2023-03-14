package com.example.solestock;

public class Sneakers {
    private String ShoeName;
    private String category;
    private String price;


    public Sneakers() {

    }

    public Sneakers(String ShoeName,String category,String price){

        this.ShoeName= ShoeName;
        this.category=category;
        this.price=price;

    }

    public String getShoeName() {
        return ShoeName;
    }

    public String getcategory() {
        return category;
    }

    public String getprice() {
        return price;
    }


}
