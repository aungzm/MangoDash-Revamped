package org.example.mangodash.use_case.db_acccess;

import org.example.mangodash.model.APIStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class APIStorageDAO implements APIStorageDataAccessInterface{
    private final DBConnection dbConnection;

    public APIStorageDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;

        // Initialize the API storage table
        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS user_api_data (" +
                    "username TEXT, " +
                    "api_key TEXT, " +
                    "api_status BOOLEAN, " +
                    "api_type TEXT, " +
                    "FOREIGN KEY(username) REFERENCES users(username) ON DELETE CASCADE)";
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating user_api_data table", e);
        }
    }

    // Save API data for a user
    @Override
    public void save(APIStorage apiStorage) {
        String sql = "INSERT OR REPLACE INTO user_api_data(username, api_key, api_status, api_type) VALUES(?,?,?,?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, apiStorage.getUsername());
            statement.setString(2, apiStorage.getApiKey());
            statement.setBoolean(3, apiStorage.getApiStatus());
            statement.setString(4, apiStorage.getApiType());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving API storage data", e);
        }
    }

    // Update the API status for a specific API type for a user
    @Override
    public void updateApiStatus(String username, String apiType, boolean newStatus) {
        String sql = "UPDATE user_api_data SET api_status = ? WHERE username = ? AND api_type = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBoolean(1, newStatus);
            statement.setString(2, username);
            statement.setString(3, apiType);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating API status", e);
        }
    }

    // Fetch all API data entries for a user
    @Override
    public List<APIStorage> getApisForUser(String username) {
        String query = "SELECT * FROM user_api_data WHERE username = ?";
        List<APIStorage> apiStorages = new ArrayList<>();

        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String apiKey = resultSet.getString("api_key");
                    boolean apiStatus = resultSet.getBoolean("api_status");
                    String apiType = resultSet.getString("api_type");

                    APIStorage apiStorage = new APIStorage(username, apiKey, apiStatus, apiType);
                    apiStorages.add(apiStorage);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching API data for user", e);
        }
        return apiStorages;
    }

    // Check if an API entry exists for a user and type
    @Override
    public boolean apiExists(String username, String apiType) {
        String sql = "SELECT COUNT(*) FROM user_api_data WHERE username = ? AND api_type = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, apiType);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking API entry existence", e);
        }
    }
}