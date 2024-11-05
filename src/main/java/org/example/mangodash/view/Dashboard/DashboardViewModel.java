package org.example.mangodash.view.Dashboard;

import org.example.mangodash.view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DashboardViewModel extends ViewModel {
    private DashboardState state = new DashboardState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor sets the view name
    public DashboardViewModel() {
        super("dashboard");
    }

    // Sets a new DashboardState and notifies listeners
    public void setState(DashboardState state) {
        this.state = state;
        firePropertyChanged(); // Notify listeners of the state change
    }

    // Returns the current DashboardState
    public DashboardState getState() {
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

