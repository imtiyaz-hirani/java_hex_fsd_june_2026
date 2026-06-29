package com.ecom.repository.impl;

import com.ecom.dto.ProductDto;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.model.Seller;
import com.ecom.repository.ProductRepository;
import com.ecom.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    DBConnection db = new DBConnection();
    @Override
    public List<Product> getProductsWithCategoryAndSellerInfo() {
        // Est DB Connection
        List<Product> list = new ArrayList<>();
        try (Connection connection = db.dbConnect()) {
            String sql = "select p.id, p.title, p.price, c.name as c_name, s.name as s_name " +
                    "from products p " +
                    "JOIN category c ON p.category_id = c.id " +
                    "JOIN seller s ON p.seller_id = s.id";
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            while(rst.next()){
                Product product = new Product();
                Category category = new Category();
                Seller seller = new Seller();
                // read the result set into respective objects
                product.setId(rst.getInt("id"));
                product.setTitle(rst.getString("title"));
                product.setPrice(rst.getDouble("price"));

                category.setName(rst.getString("c_name"));
                seller.setName(rst.getString("s_name"));

                // Since Category and Seller are already injected in Product , we can attach them
                product.setCategory(category);
                product.setSeller(seller);
                list.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.dbClose();
        return list;
    }

    @Override
    public List<ProductDto> getProductsWithCategoryAndSellerInfoWithDto() {
        List<ProductDto> list = new ArrayList<>();
        try (Connection connection = db.dbConnect()) {
            String sql = "select p.id, p.title, p.price, c.name as c_name, s.name as s_name " +
                    "from products p " +
                    "JOIN category c ON p.category_id = c.id " +
                    "JOIN seller s ON p.seller_id = s.id";
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            while(rst.next()){
                 ProductDto dto = new ProductDto(
                         rst.getInt("id"),
                         rst.getString("title"),
                         rst.getDouble("price"),
                         rst.getString("c_name"),
                         rst.getString("s_name")
                 );
                list.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.dbClose();
        return list;
    }
}
