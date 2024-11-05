package org.example.mangodash.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.mangodash.use_case.data_processing.Login.LoginDataInput;
import org.example.mangodash.use_case.data_processing.Login.LoginInputBoundary;
import org.example.mangodash.view.Login.LoginViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.net.MalformedURLException;
import java.util.Objects;

public class LoginController implements LoginInputBoundary{
    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button logIn;

    @FXML
    private Button back;

    @FXML
    private TextField usernameInputField;

    @FXML
    private PasswordField passwordInputField;

    @FXML
    private ImageView titleImageView;

    private final LoginInputBoundary loginInputBoundary;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LoginController(LoginInputBoundary loginInputBoundary, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.loginInputBoundary = loginInputBoundary;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @FXML
    private void initialize() {
        try {
            Image titleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/MangoDashLoginHomeTitle.png")));
            titleImageView.setImage(titleImage);
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }

        // Set values from LoginViewModel
        usernameLabel.setText(loginViewModel.USERNAME_LABEL);
        passwordLabel.setText(loginViewModel.PASSWORD_LABEL);
        logIn.setText(loginViewModel.LOGIN_BUTTON_LABEL);
        back.setText(loginViewModel.BACK_BUTTON_LABEL);
    }

    @FXML
    private void handleLogin() throws MalformedURLException {
        String username = usernameInputField.getText();
        String password = passwordInputField.getText();
        loginInputBoundary.executeLogin(new LoginDataInput(username, password));
    }

    @FXML
    private void handleBack() {
        viewManagerModel.setActiveView("frontpage");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void executeLogin(LoginDataInput loginDataInput) throws MalformedURLException {
        String username = usernameInputField.getText();
        String password = passwordInputField.getText();
        loginInputBoundary.executeLogin(new LoginDataInput(username, password));
    }

    @Override
    public void executeForgotPassword(LoginDataInput loginDataInput) {
        System.out.println("Not implemented");
    }
}
