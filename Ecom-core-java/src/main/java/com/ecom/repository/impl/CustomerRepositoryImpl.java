package com.ecom.repository.impl;

import com.ecom.dto.CustomerDto;
import com.ecom.repository.CustomerRepository;
import com.ecom.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    DBConnection db = DBConnection.getInstance();

    @Override
    public List<CustomerDto> fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(int productId) {
        System.out.println("DB Conn Object at fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto: this loc ==> " +db);

        List<CustomerDto> list = new ArrayList<>();

        try (Connection connection = db.dbConnect()) {
            String sql = "select c.id, c.name as customer_name, c.email, " +
                    " cp.purchase_date, cp.qty, " +
                    "        p.title, p.price,  " +
                    "        cat.name as category_name,  " +
                    "        s.name as seller_name " +
                    "from customers c  " +
                    "JOIN customer_product cp ON c.id = cp.customers_id  " +
                    "JOIN products p  ON p.id = cp.products_id " +
                    "JOIN seller s ON p.seller_id = s.id  " +
                    "JOIN category cat ON p.category_id = cat.id " +
                    "where p.id=?";

           PreparedStatement preparedStatement =  connection.prepareStatement(sql);
           preparedStatement.setInt(1, productId);
           ResultSet rst =  preparedStatement.executeQuery();
           while(rst.next()){
               CustomerDto dto = new CustomerDto(
                       rst.getInt("id"),
                       rst.getString("customer_name"),
                       rst.getString("email"),
                       LocalDate.parse(rst.getString("purchase_date")),
                       rst.getInt("qty"),
                       rst.getString("title"),
                       rst.getDouble("price"),
                       rst.getString("category_name"),
                       rst.getString("seller_name")
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
