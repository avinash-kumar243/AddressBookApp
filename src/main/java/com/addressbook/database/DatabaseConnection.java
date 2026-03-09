package com.addressbook.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {

        if(instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/addressbook",
                    "root",
                    "Avinash269"
            );

        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}