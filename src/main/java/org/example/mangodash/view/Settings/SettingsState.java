package org.example.mangodash.view.Settings;

public class SettingsState {
    private String username;
    private String name;
    private String bio;

    private String error;

    // Constructor
    public SettingsState(String username, String name, String bio) {
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.error = "";
    }

    // Default constructor
    public SettingsState() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "SettingsState{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}

