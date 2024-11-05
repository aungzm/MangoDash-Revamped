package org.example.mangodash.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class ViewManager implements PropertyChangeListener {

    private final HashMap<String, Parent> views;
    private final ViewManagerModel viewManagerModel;

    private final Stage stage;

    public ViewManager(HashMap<String, Parent> views, ViewManagerModel viewManagerModel, Stage stage) {
        this.views = views;
        this.viewManagerModel = viewManagerModel;
        this.stage = stage;
        this.viewManagerModel.addPropertyChangeListener(this);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("view".equals(evt.getPropertyName())) {
            String newView = (String) evt.getNewValue();
            switchView(newView);
            System.out.println(newView);
        }
    }

    private void switchView(String viewName) {
        Parent viewRoot = views.get(viewName);
        if (viewRoot != null) {
            Scene currentScene = stage.getScene();
            if (currentScene == null) {
                // If there is no current scene, create a new one.
                currentScene = new Scene(viewRoot);
                stage.setScene(currentScene);
            } else {
                // If there is an existing scene, just change its root.
                currentScene.setRoot(viewRoot);
            }
            stage.show();
        } else {
            System.out.println("View not found: " + viewName);
            // Handle view not found
        }
    }

}
