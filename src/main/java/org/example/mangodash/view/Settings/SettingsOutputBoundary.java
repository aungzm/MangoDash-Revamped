package org.example.mangodash.view.Settings;

import org.example.mangodash.use_case.data_processing.Settings.SettingsDataOutput;

public interface SettingsOutputBoundary {
    void prepareFailView(String error);

    void prepareSuccessView(SettingsDataOutput settingsDataOutput);
}
