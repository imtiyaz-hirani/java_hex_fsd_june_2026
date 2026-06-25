package com.java_app.utility;

import com.java_app.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductUtility {
    public List<Product> getProductList(ResultSet resultSet) throws SQLException {
        List<Product> list = new ArrayList<>();
        while(resultSet.next()){
            // each db row is an object in java
            Product product = new Product(); // 100X 200X 300X
            product.setId(resultSet.getInt("id"));
            product.setTitle(resultSet.getString("title"));
            product.setPrice(resultSet.getDouble("price"));
            product.setCategory(resultSet.getString("category"));
            list.add(product); // [100X, 200X, 300X]
        }
    return list;
    }
}
