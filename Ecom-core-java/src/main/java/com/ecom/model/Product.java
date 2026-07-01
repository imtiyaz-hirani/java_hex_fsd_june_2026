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

    public Product() {
    }

    public Product(int id, String title, double price, String description, int stockCount, Category category, Seller seller) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.stockCount = stockCount;
        this.category = category;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", stockCount=" + stockCount +
                ", category=" + category +
                ", seller=" + seller +
                '}';
    }
}
/*
* A Foreign Key in the DB is a Reference in Java Model
* */