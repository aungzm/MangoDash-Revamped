package org.example.mangodash.view.Login;

import org.example.mangodash.use_case.data_processing.Dashboard.DashboardDataOutput;
import org.example.mangodash.use_case.data_processing.Extension.ExtensionDataOutput;
import org.example.mangodash.use_case.data_processing.Settings.SettingsDataOutput;
import org.example.mangodash.view.Dashboard.DashboardState;
import org.example.mangodash.view.Dashboard.DashboardViewModel;
import org.example.mangodash.view.Extension.ExtensionState;
import org.example.mangodash.view.Extension.ExtensionViewModel;
import org.example.mangodash.view.Settings.SettingsState;
import org.example.mangodash.view.Settings.SettingsViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.util.List;

public class LoginPresenter implements LoginOutputBoundary {
    private final DashboardViewModel dashboardViewModel;
    private final ExtensionViewModel extensionViewModel;
    private final SettingsViewModel settingsViewModel;
    private final LoginViewModel loginViewModel;

    private final ViewManagerModel viewManagerModel;

    // Constructor to initialize the presenter with the three states
    public LoginPresenter(DashboardViewModel dashboardViewModel, ExtensionViewModel extensionViewModel,
                          SettingsViewModel settingsViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.dashboardViewModel = dashboardViewModel;
        this.extensionViewModel = extensionViewModel;
        this.settingsViewModel = settingsViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SettingsDataOutput settingsDataOutput, ExtensionDataOutput extensionDataOutput, DashboardDataOutput dashboardDataOutput, List<String> errors) {
        // Update DashboardState
        DashboardState dashboardState = dashboardViewModel.getState();
        ExtensionState extensionState = extensionViewModel.getState();
        SettingsState settingsState = settingsViewModel.getState();
        if (dashboardDataOutput.getFacebookStats() != null) {
            dashboardState.setFacebookStats(dashboardDataOutput.getFacebookStats());
        }
        if (dashboardDataOutput.getInstagramStats() != null) {
            dashboardState.setInstagramStats(dashboardDataOutput.getInstagramStats());
        }
        if (dashboardDataOutput.getTelegramStats() != null) {
            dashboardState.setTelegramStats(dashboardDataOutput.getTelegramStats());
        }
        dashboardState.clearErrors();
        if (errors != null && !errors.isEmpty()) {
            dashboardState.setErrors(errors);
        }

        // Update ExtensionState
        extensionState.getApiDetailsMap().clear(); // Clear existing data to avoid duplicates
        for (ExtensionDataOutput.ApiDetail apiDetail : extensionDataOutput.getApiDetails()) {
            extensionState.addApiDetail(apiDetail.getApiType(), apiDetail.getApiKey(), apiDetail.getApiStatus());
        }

        // Update SettingsState
        settingsState.setUsername(settingsDataOutput.getUsername());
        settingsState.setName(settingsDataOutput.getName());
        settingsState.setBio(settingsDataOutput.getBio());

        // Display updated states (for demonstration)
        System.out.println("Login success - states updated:");
        System.out.println("DashboardState: " + dashboardState);
        System.out.println("ExtensionState: " + extensionState);
        System.out.println("SettingsState: " + settingsState);

        dashboardViewModel.firePropertyChanged();
        extensionViewModel.firePropertyChanged();
        settingsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(dashboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // Clear relevant data in all states and set error messages
        loginViewModel.getState().setErors(error);
        loginViewModel.firePropertyChanged();
    }
}
