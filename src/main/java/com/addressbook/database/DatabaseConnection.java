package com.addressbook.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {}
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {

        if(instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {

        try {

           return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/addressbook",
                    "root",
                    "Avinash269"
            );

        } catch(Exception e) {
            throw new RuntimeException("Database connection failed!!!");
        }
    }
}