package org.example.mangodash.use_case.data_processing.Settings;

public interface SettingsInputBoundary {
    void executeChangeName(SettingsDataInput settingsDataInput);

    void executeChangePassword(SettingsDataInput settingsDataInput);

    void executeChangeBio(SettingsDataInput settingsDataInput);

}
