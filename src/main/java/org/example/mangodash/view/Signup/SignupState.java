package org.example.mangodash.view.Signup;

public class SignupState {
    private String error;

    public SignupState(String error) {
        this.error = error;
    }

    // Default constructor
    public SignupState() {
    }

    // Getter for error
    public String getError() {
        return error;
    }

    // Setter for error
    public void setError(String error) {
        this.error = error;
    }
}
