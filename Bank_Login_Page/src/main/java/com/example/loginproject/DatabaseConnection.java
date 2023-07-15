package com.example.loginproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "user_data";
        String databaseUser = "postgres";
        String databasePassword = "Abdu2001";
        String url = "jdbc:postgresql://localhost:5432/"+databaseName;

        try {
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
