package org.example.mangodash.view.Extension;

import org.example.mangodash.view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ExtensionViewModel extends ViewModel {
    private ExtensionState state = new ExtensionState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor sets the view name
    public ExtensionViewModel() {
        super("extension");
    }

    // Sets a new ExtensionState and notifies listeners
    public void setState(ExtensionState state) {
        this.state = state;
        firePropertyChanged(); // Notify listeners of the state change
    }

    // Returns the current ExtensionState
    public ExtensionState getState() {
        return state;
    }

    // Fires a property change event to notify listeners
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Adds a property change listener
}
