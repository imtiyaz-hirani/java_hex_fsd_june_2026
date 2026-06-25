package com.java_app.repository;

import com.java_app.model.Product;
import com.java_app.utility.DBConnection;
import com.java_app.utility.ProductUtility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    /*
        I want to call the proc
        - and save each row in an object,
        - and add that object to the list
        - and return the list
    * */
    /*
        Problem: DB Connection
        Solution: Create a Utility class to do this connection
    * */

    DBConnection dbConnection = new DBConnection();
    ProductUtility productUtility = new ProductUtility();

    public List<Product> getAllProducts() throws SQLException {
        Connection connection = dbConnection.dbConnect();
        // call the proc
        CallableStatement callableStatement = connection.prepareCall("{CALL all_products()}");
        ResultSet resultSet =  callableStatement.executeQuery();
        List<Product> list = productUtility.getProductList(resultSet);
        dbConnection.dbClose();
        return list;
    }

    public List<Product> getAllProductsByCategory(String category) throws SQLException {
        Connection connection = dbConnection.dbConnect();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_products_by_category(?)}");
        callableStatement.setString(1,category);
        ResultSet resultSet =  callableStatement.executeQuery();
        List<Product> list =  productUtility.getProductList(resultSet);
        dbConnection.dbClose();
        return list;
    }
}
