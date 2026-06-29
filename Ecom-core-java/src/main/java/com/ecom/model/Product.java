package com.ecom.model;

public class Product {
    private int id;
    private String title;
    private double price;
    private String description;
    private int stockCount;

    // private int categoryId; //NO -- this is wrong
    /* I am going to inject Category in product
    * I create a reference of Category in Product
    * */
    private Category category;

    private Seller seller;

    //constructor , getter, setter, toString

}
/*
* A Foreign Key in the DB is a Reference in Java Model
* */