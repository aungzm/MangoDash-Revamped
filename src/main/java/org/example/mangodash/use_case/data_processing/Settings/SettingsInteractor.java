package org.example.mangodash.use_case.data_processing.Settings;

import org.example.mangodash.model.User;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;
import org.example.mangodash.view.Settings.SettingsOutputBoundary;

public class SettingsInteractor implements SettingsInputBoundary {
    private final UserDataAccessInterface userDataAccessInterface;
    private final SettingsOutputBoundary settingsOutputBoundary;

    public SettingsInteractor(UserDataAccessInterface userDataAccessInterface, SettingsOutputBoundary settingsOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.settingsOutputBoundary = settingsOutputBoundary;
    }

    @Override
    public void executeChangeName(SettingsDataInput settingsDataInput) {
        String username = settingsDataInput.getUsername();
        String newName = settingsDataInput.getName();
        String oldName = userDataAccessInterface.getUser(username).getName();

        if (newName != null){
            if (oldName.equals(newName)){
                settingsOutputBoundary.prepareFailView("No changes made, same name");
            } else {
                userDataAccessInterface.modifyUserName(username, newName);
                SettingsDataOutput output = new SettingsDataOutput.Builder(username)
                        .setName(newName)
                        .build();
                settingsOutputBoundary.prepareSuccessView(output);
            }
        } else {
            settingsOutputBoundary.prepareFailView("New name is needed");
        }
    }

    @Override
    public void executeChangePassword(SettingsDataInput settingsDataInput) {
        String username = settingsDataInput.getUsername();
        String oldPassword = settingsDataInput.getOldPassword();
        String newPassword = settingsDataInput.getNewPassword();
        String repeatPassword = settingsDataInput.getRepeatNewPassword();

        if (oldPassword != null && newPassword!=null
                && repeatPassword != null){
            String correctPassword = userDataAccessInterface.getUser(username).getPassword();
            if (!oldPassword.equals(correctPassword)){
                settingsOutputBoundary.prepareFailView("Incorrect password for " + username + ".");
            }
            if (!repeatPassword.equals(newPassword)){
                settingsOutputBoundary.prepareFailView("New Passwords do not match");
            }
            userDataAccessInterface.modifyUserPassword(username, newPassword);
            SettingsDataOutput output = new SettingsDataOutput.Builder(username).build();
            settingsOutputBoundary.prepareSuccessView(output);
        } else {
            if (oldPassword == null){
                settingsOutputBoundary.prepareFailView("Old password is needed");
            }
            if (newPassword == null){
                settingsOutputBoundary.prepareFailView("New password is needed");
            } else {
                settingsOutputBoundary.prepareFailView("Repeated Password is blank");
            }
        }
    }

    @Override
    public void executeChangeBio(SettingsDataInput settingsDataInput) {
        String username = settingsDataInput.getUsername();
        String newBio = settingsDataInput.getBio();
        String oldBio = userDataAccessInterface.getUser(username).getName();

        if (newBio != null){
            if (oldBio.equals(newBio)){
                settingsOutputBoundary.prepareFailView("No changes made, same bio description");
            } else {
                userDataAccessInterface.modifyUserBio(username, newBio);
                SettingsDataOutput output = new SettingsDataOutput.Builder(username)
                        .setBio(newBio)
                        .build();
                settingsOutputBoundary.prepareSuccessView(output);
            }
        } else {
            settingsOutputBoundary.prepareFailView("New bio is needed");
        }

    }
}
