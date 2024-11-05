package org.example.mangodash.use_case.db_acccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String dbUrl;
    private final String dbType;
    private final String username;
    private final String password;

    public DBConnection(String dbType, String dbPathOrUrl, String username, String password) {
        this.dbType = dbType.toLowerCase();
        this.username = username;
        this.password = password;
        this.dbUrl = buildDbUrl(dbType, dbPathOrUrl);
        loadDriver();
    }

    // Builds the appropriate URL for each database type
    private String buildDbUrl(String dbType, String dbPathOrUrl) {
        return switch (dbType.toLowerCase()) {
            case "sqlite" -> "jdbc:sqlite:" + dbPathOrUrl;
            case "mysql" -> "jdbc:mysql://" + dbPathOrUrl;
            // Add cases for other databases as needed
            default -> throw new IllegalArgumentException("Unsupported database type: " + dbType);
        };
    }

    // Loads the driver based on the database type
    private void loadDriver() {
        try {
            switch (dbType) {
                case "sqlite" -> Class.forName("org.sqlite.JDBC");
                case "mysql" -> Class.forName("com.mysql.cj.jdbc.Driver");
                // Add cases for other databases as needed
                default -> throw new IllegalArgumentException("Unsupported database type: " + dbType);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found for " + dbType, e);
        }
    }

    // Establishes and returns a connection
    public Connection connect() throws SQLException {
        return dbType.equals("sqlite")
                ? DriverManager.getConnection(dbUrl)
                : DriverManager.getConnection(dbUrl, username, password);
    }

    // Tests the connection to the database
    public boolean testConnection() {
        try (Connection conn = connect()) {
            System.out.println("Connection to " + dbType.toUpperCase() + " has been established.");
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to establish connection to " + dbType.toUpperCase() + ".");
            e.printStackTrace();
            return false;
        }
    }
}