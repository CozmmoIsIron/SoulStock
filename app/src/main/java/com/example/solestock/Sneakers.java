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

    public void setShoeName(String ShoeName) {this.ShoeName= ShoeName;}
    public void setCategory(String category) {this.category= category;}
    public void setPrice(String price) {this.price=  price;}


}
