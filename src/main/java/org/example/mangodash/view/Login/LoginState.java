package org.example.mangodash.view.Login;

public class LoginState {
    private String username;
    private String password;

    private String erors;

    // Constructor
    public LoginState(String username, String password, String error) {
        this.username = username;
        this.password = password;
        this.erors = erors;
    }

    public String getErors() {
        return erors;
    }

    public void setErors(String erors) {
        this.erors = erors;
    }

    // Default constructor
    public LoginState() {
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
