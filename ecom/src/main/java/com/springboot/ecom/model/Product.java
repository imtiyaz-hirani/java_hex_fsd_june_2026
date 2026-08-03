package com.springboot.ecom.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product { //p
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @Column(length = 2000)
    private String description;
    private double price;
    @Column(name = "stock_count")
    private int stockCount;
    @ManyToOne
    @JoinColumn(name = "seller_id" , nullable = false)
    private Seller seller; //join p.seller s

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category; //p.category

    private String imageUrl;
}
