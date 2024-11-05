package org.example.mangodash.factory;

import org.example.mangodash.controller.SettingsController;
import org.example.mangodash.use_case.data_processing.Settings.SettingsInteractor;
import org.example.mangodash.use_case.data_processing.Settings.SettingsInputBoundary;
import org.example.mangodash.view.Settings.SettingsPresenter;
import org.example.mangodash.view.Settings.SettingsViewModel;
import org.example.mangodash.view.ViewManagerModel;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;

public class SettingsControllerFactory {

    private final UserDataAccessInterface userDAO;
    private final ViewManagerModel viewManagerModel;
    private final SettingsViewModel settingsViewModel;

    public SettingsControllerFactory(UserDataAccessInterface userDAO,
                                     ViewManagerModel viewManagerModel,
                                     SettingsViewModel settingsViewModel) {
        this.userDAO = userDAO;
        this.viewManagerModel = viewManagerModel;
        this.settingsViewModel = settingsViewModel;
    }

    public SettingsController createSettingsController() {
        // Instantiate the SettingsPresenter with dependencies
        SettingsPresenter settingsPresenter = new SettingsPresenter(viewManagerModel, settingsViewModel);

        // Instantiate the SettingsInteractor with dependencies
        SettingsInputBoundary settingsInteractor = new SettingsInteractor(userDAO, settingsPresenter);

        // Return a fully initialized SettingsController
        return new SettingsController(settingsInteractor, settingsViewModel, viewManagerModel);
    }
}
