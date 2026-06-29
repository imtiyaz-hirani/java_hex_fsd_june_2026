package com.ecom.utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection;

    // This method must return Connection obj so that
    // programmer can execute proc call using it
    public Connection dbConnect(){
        try {
            // Step 1: Load the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 2: Establish the connection and return it
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/ecom_june_26",
                            "root",
                            "deepcoder");

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dbClose(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

