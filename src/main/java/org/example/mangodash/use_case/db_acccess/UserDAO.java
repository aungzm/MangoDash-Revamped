package org.example.mangodash.use_case.db_acccess;

import org.example.mangodash.model.CommonUser;
import org.example.mangodash.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import java.util.HashMap;

public class UserDAO implements UserDataAccessInterface {
    private final DBConnection dbConnection;

    public UserDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;

        // Initialize table
        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(name TEXT, " +
                    "username TEXT PRIMARY KEY, " +
                    "password TEXT, " +
                    "bio TEXT, " +
                    "creation_time TEXT";
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating users table", e);
        }
    }
    @Override
    public boolean existsByName(String identifier) {
        String sql = "SELECT COUNT(username) FROM users WHERE username = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, identifier);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking username existence", e);
        }
    }
    @Override
    public void save(User user) {
        String userSql = "INSERT OR REPLACE INTO users(name, username, password, bio, creation_time) VALUES(?,?,?,?,?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement userStatement = conn.prepareStatement(userSql)) {
            userStatement.setString(1, user.getName());
            userStatement.setString(2, user.getUserName());
            userStatement.setString(3, user.getPassword());
            userStatement.setString(4, user.getBio());
            userStatement.setString(5, user.getCreationTime().toString());
            userStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }
    @Override
    public void modifyUserPassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user password", e);
        }
    }

    @Override
    public void modifyUserName(String username, String newName) {
        String sql = "UPDATE users SET name = ? WHERE username = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user name", e);
        }
    }
    @Override
    public void modifyUserBio(String username, String newBio) {
        String sql = "UPDATE users SET bio = ? WHERE username = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, newBio);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user bio", e);
        }
    }
    @Override
    public User getUser(String username) {
        String userQuery = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement userStatement = conn.prepareStatement(userQuery)) {

            userStatement.setString(1, username);
            try (ResultSet userResult = userStatement.executeQuery()) {
                if (userResult.next()) {
                    String name = userResult.getString("name");
                    String userPassword = userResult.getString("password");
                    String bio = userResult.getString("bio");
                    LocalDateTime creationTime = LocalDateTime.parse(userResult.getString("creation_time"));

                    // Assuming an empty map for apiKeys and apiStatus, handled by APIStorageDAO
                    HashMap<String, String> apiKeys = new HashMap<>();
                    HashMap<String, Boolean> apiStatus = new HashMap<>();

                    return new CommonUser(name, username, userPassword, bio, creationTime);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching user", e);
        }
        return null;
    }
}
