package org.example.mangodash.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.mangodash.use_case.data_processing.Signup.SignupDataInput;
import org.example.mangodash.use_case.data_processing.Signup.SignupInputBoundary;
import org.example.mangodash.view.ViewManagerModel;

import java.time.LocalDateTime;

public class SignupController {

    private final SignupInputBoundary signupInputBoundary;
    private final ViewManagerModel viewManagerModel;

    @FXML
    private TextField usernameInputField;
    @FXML
    private TextField nameInputField;
    @FXML
    private TextField bioInputField;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField repeatNewPassword;
    @FXML
    private Button signup;
    @FXML
    private Button back;

    public SignupController(SignupInputBoundary signupInputBoundary, ViewManagerModel viewManagerModel) {
        this.signupInputBoundary = signupInputBoundary;
        this.viewManagerModel = viewManagerModel;
    }

    @FXML
    private void initialize() {
        // Optionally add initialization code here
    }

    @FXML
    private void handleSignup() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        SignupDataInput signupDataInput = new SignupDataInput(
                nameInputField.getText(),
                usernameInputField.getText(),
                newPassword.getText(),
                repeatNewPassword.getText(),
                currentDateTime,
                bioInputField.getText()
        );
        signupInputBoundary.executeSignup(signupDataInput);
    }

    @FXML
    private void handleBack() {
        viewManagerModel.setActiveView("login");
        viewManagerModel.firePropertyChanged();
    }
}
