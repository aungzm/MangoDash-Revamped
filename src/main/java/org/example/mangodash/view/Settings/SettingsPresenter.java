package org.example.mangodash.view.Settings;

import org.example.mangodash.use_case.data_processing.Settings.SettingsDataOutput;
import org.example.mangodash.view.ViewManagerModel;

public class SettingsPresenter implements SettingsOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final SettingsViewModel settingsViewModel;
    public SettingsPresenter(ViewManagerModel viewManagerModel, SettingsViewModel settingsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.settingsViewModel = settingsViewModel;
    }

    @Override
    public void prepareFailView(String error) {
        // Handle error display (could be logging, setting an error message in the view, etc.)
        settingsViewModel.getState().setError(error);
        System.out.println("Error: " + error);
        settingsViewModel.firePropertyChanged();

    }

    @Override
    public void prepareSuccessView(SettingsDataOutput settingsDataOutput) {
        // Update the settingsState with the data from settingsDataOutput
        settingsViewModel.getState().setUsername(settingsDataOutput.getUsername());
        settingsViewModel.getState().setName(settingsDataOutput.getName());
        settingsViewModel.getState().setBio(settingsDataOutput.getBio());

        // Display success message or update UI with new settings data
        System.out.println("Settings updated successfully:");
        System.out.println(settingsViewModel.getState());
        settingsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(viewManagerModel.getActiveView());
        viewManagerModel.firePropertyChanged();
    }
}
