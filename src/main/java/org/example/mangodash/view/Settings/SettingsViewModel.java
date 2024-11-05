package org.example.mangodash.view.Settings;

import org.example.mangodash.view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SettingsViewModel extends ViewModel {
    private SettingsState state = new SettingsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor sets the view name
    public SettingsViewModel() {
        super("settings");
    }

    // Sets a new SettingsState and notifies listeners
    public void setState(SettingsState state) {
        this.state = state;
        firePropertyChanged(); // Notify listeners of the state change
    }

    // Returns the current SettingsState
    public SettingsState getState() {
        return state;
    }

    // Fires a property change event to notify listeners
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    // Adds a property change listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
