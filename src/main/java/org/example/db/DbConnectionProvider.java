package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionProvider {
    private static DbConnectionProvider dbConnectionProvider = null;
    private Connection connection;
    private final String DB_URL = "jdbc:mysql://localhost:3306/eshop";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "root";


    private DbConnectionProvider() {
    }

    public synchronized static DbConnectionProvider getInstance() {
        if (dbConnectionProvider == null) {
            dbConnectionProvider = new DbConnectionProvider();

        }
        return dbConnectionProvider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
