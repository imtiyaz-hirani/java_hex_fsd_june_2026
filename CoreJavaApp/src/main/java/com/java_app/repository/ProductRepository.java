package com.java_app.repository;

import com.java_app.model.Product;
import com.java_app.utility.DBConnection;
import com.java_app.utility.ProductUtility;

import java.sql.*;
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

    public long getProductsByPrice(double price) throws SQLException {
        Connection connection = dbConnection.dbConnect();
        // first param ? is input price; second param ? is the one we need to read
        CallableStatement callableStatement = connection.prepareCall("{CALL get_products_by_price_higher(?, ?)}");
        callableStatement.setDouble(1,price);
        callableStatement.registerOutParameter(2, Types.BIGINT);
        // Read the OUT param value coming from the procedure in callableStatement itself
        callableStatement.executeQuery();
        // fetch second param/out param value into a variable
        long count =  callableStatement.getInt(2);
        dbConnection.dbClose();
        return count;
    }
}
