package org.example.mangodash.use_case.data_processing.Login;

public class LoginDataInput {
    final private String username;

    private String password;

    public LoginDataInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDataInput(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
