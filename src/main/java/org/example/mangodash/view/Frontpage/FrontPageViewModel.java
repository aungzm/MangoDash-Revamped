package org.example.mangodash.view.Frontpage;

import org.example.mangodash.view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FrontPageViewModel extends ViewModel {
    private static final String signupLabel = "Signup";

    private static final String loginLabel = "Login";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Constructor sets the view name
    public FrontPageViewModel() {
        super("frontpage");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

}
