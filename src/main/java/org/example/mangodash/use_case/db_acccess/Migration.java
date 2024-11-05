package org.example.mangodash.use_case.db_acccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Migration {

    private final DBConnection dbConnection;

    public Migration(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Method to run migrations
    public void runMigrations() {
        try (Connection conn = dbConnection.connect(); Statement stmt = conn.createStatement()) {

            // Create users table if it doesn't exist
            String createUserTableSql = """
                    CREATE TABLE IF NOT EXISTS users (
                        name TEXT,
                        username TEXT PRIMARY KEY,
                        password TEXT,
                        bio TEXT,
                        api_keys TEXT,
                        creation_time TEXT,
                        api_status TEXT
                    )
                    """;
            stmt.execute(createUserTableSql);

            // Additional migrations can be added here
            System.out.println("Database migrations completed successfully.");

        } catch (SQLException e) {
            System.err.println("Failed to run migrations.");
            e.printStackTrace();
        }
    }
}
