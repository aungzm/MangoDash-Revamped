package org.example.mangodash.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.example.mangodash.use_case.data_processing.Settings.SettingsDataInput;
import org.example.mangodash.use_case.data_processing.Settings.SettingsInputBoundary;
import org.example.mangodash.view.Settings.SettingsState;
import org.example.mangodash.view.Settings.SettingsViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.awt.*;

public class SettingsController {

    @FXML
    private TextField usernameInputField;

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField repeatNewPasswordField;

    @FXML
    private TextField nameInputField;

    @FXML
    private TextField bioInputField;

    @FXML
    private Button savePasswordButton;

    @FXML
    private Button saveDetailsButton;

    @FXML
    private Label statusLabel;

    private final SettingsInputBoundary settingsInputBoundary;

    private final SettingsViewModel settingsViewModel;

    private final ViewManagerModel viewManagerModel;

    public SettingsController(SettingsInputBoundary settingsInputBoundary, SettingsViewModel settingsViewModel, ViewManagerModel viewManagerModel) {
        this.settingsInputBoundary = settingsInputBoundary;
        this.settingsViewModel = settingsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @FXML
    private void initialize() {
        // Initialize any default values or perform setup tasks here if needed
        statusLabel.setText("Update your settings");
    }

    @FXML
    private void handleSavePasswordChanges() {
        String username = usernameInputField.getText();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String repeatNewPassword = repeatNewPasswordField.getText();

        if (!newPassword.equals(repeatNewPassword)) {
            statusLabel.setText("New passwords do not match.");
            return;
        }

        SettingsDataInput settingsDataInput = new SettingsDataInput.Builder(username)
                .setPasswordChange(oldPassword, newPassword, repeatNewPassword)
                .build();

        settingsInputBoundary.executeChangePassword(settingsDataInput);
        statusLabel.setText("Password updated successfully!");
    }

    @FXML
    private void handleSaveUserDetails() {
        SettingsState oldState = settingsViewModel.getState();
        String username = usernameInputField.getText();
        String name = nameInputField.getText();
        String bio = bioInputField.getText();
        String oldName = oldState.getName();
        String oldBio = oldState.getBio();

        // Use the builder to create a SettingsDataInput object
        SettingsDataInput.Builder builder = new SettingsDataInput.Builder(username);

        if (!oldName.equals(name)) {
            builder.setName(name);
            SettingsDataInput settingsDataInput = builder.build();
            settingsInputBoundary.executeChangeName(settingsDataInput);
        }
        if (!oldBio.equals(bio)) {
            builder.setBio(bio);
            SettingsDataInput settingsDataInput = builder.build();
            settingsInputBoundary.executeChangeName(settingsDataInput);
        }
    }
}
