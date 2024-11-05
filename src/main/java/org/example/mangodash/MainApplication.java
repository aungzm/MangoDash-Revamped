package org.example.mangodash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.mangodash.controller.*;
import org.example.mangodash.factory.*;
import org.example.mangodash.model.FacebookStats;
import org.example.mangodash.model.InstagramStats;
import org.example.mangodash.model.SocialMediaStats;
import org.example.mangodash.model.TelegramStats;
import org.example.mangodash.use_case.api_data_access.APIDataAccessInterface;
import org.example.mangodash.use_case.api_data_access.FacebookAPIDAO;
import org.example.mangodash.use_case.db_acccess.*;
import org.example.mangodash.view.Dashboard.DashboardViewModel;
import org.example.mangodash.view.Extension.ExtensionViewModel;
import org.example.mangodash.view.Frontpage.FrontPageViewModel;
import org.example.mangodash.view.Login.LoginViewModel;
import org.example.mangodash.view.Settings.SettingsViewModel;
import org.example.mangodash.view.Signup.SignupViewModel;
import org.example.mangodash.view.ViewManagerModel;

import java.io.IOException;
import java.util.HashMap;

public class MainApplication extends Application {
    private final HashMap<String, Parent> views = new HashMap<>();
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Stats
        SocialMediaStats facebookStats = new FacebookStats();
        SocialMediaStats instagramStats = new InstagramStats();
        SocialMediaStats telgramStats = new TelegramStats();

        // Create DAOs
        DBConnection sqliteConnection = new DBConnection("sqlite", "path/to/your/database.db", null, null);
        sqliteConnection.testConnection();
        UserDataAccessInterface userDataAccessInterface = new UserDAO(sqliteConnection);
        APIStorageDataAccessInterface apiStorageDataAccessInterface = new APIStorageDAO(sqliteConnection);
        APIDataAccessInterface facebookAPIDAO =  new FacebookAPIDAO(); // For now we put all 3 as facebook
//        APIDataAccessInterface telegramAPIDAO = new TelegramAPIDAO();
//        APIDataAccessInterface instagramAPIDAO = new InstagramAPIDAO();

        // Create View Models
        LoginViewModel loginViewModel = new LoginViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SettingsViewModel settingsViewModel = new SettingsViewModel();
        ExtensionViewModel extensionViewModel = new ExtensionViewModel();
        DashboardViewModel dashboardViewModel = new DashboardViewModel();
        FrontPageViewModel frontPageViewModel = new FrontPageViewModel();




        // Factories to create controllers
        LoginController loginController = new LoginControllerFactory(userDataAccessInterface, apiStorageDataAccessInterface, facebookAPIDAO, facebookAPIDAO,
                facebookAPIDAO, facebookStats, instagramStats, telgramStats, dashboardViewModel, extensionViewModel, settingsViewModel, loginViewModel, viewManagerModel).createLoginController();
        SignupController signupController = new SignupControllerFactory(userDataAccessInterface, signupViewModel, loginViewModel, viewManagerModel).createSignupController();
        DashboardController dashboardController = new DashboardControllerFactory(facebookStats, instagramStats, telgramStats, dashboardViewModel, viewManagerModel, apiStorageDataAccessInterface,
                facebookAPIDAO, facebookAPIDAO, facebookAPIDAO).createDashboardController();
        FrontpageController frontPageController = new FrontpageController(viewManagerModel, frontPageViewModel);
        SettingsController settingsController = new SettingsControllerFactory(userDataAccessInterface, viewManagerModel, settingsViewModel).createSettingsController();
        ExtensionController extensionController = new ExtensionControllerFactory(userDataAccessInterface, apiStorageDataAccessInterface, facebookAPIDAO, facebookAPIDAO,
                facebookAPIDAO, facebookStats, instagramStats, telgramStats, extensionViewModel, viewManagerModel).createExtensionController();


        //Set controllers and views
        FXMLLoader signupLoader = new FXMLLoader(getClass().getResource("/org.example.mangodash/signup.fxml"));
        signupLoader.setController(signupController);
        Parent signuproot = signupLoader.load();
        views.put(signupViewModel.getViewName(), signuproot);

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/org.example.mangodash/login.fxml"));
        loginLoader.setController(loginController);
        Parent loginRoot = loginLoader.load();
        views.put(loginViewModel.getViewName(), loginRoot);

        FXMLLoader frontPageLoader = new FXMLLoader(getClass().getResource("/org.example.mangodash/frontpage.fxml"));
        frontPageLoader.setController(frontPageController);
        Parent frontPageRoot = frontPageLoader.load();
        views.put(frontPageViewModel.getViewName(), frontPageRoot);

        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/org.example.mangodash/dashboard.fxml"));
        dashboardLoader.setController(dashboardController);
        Parent dashboardRoot = dashboardLoader.load();
        views.put(dashboardViewModel.getViewName(), dashboardRoot);

        FXMLLoader extensionLoader = new FXMLLoader(getClass().getResource("/org.example.mangodash/extensions.fxml"));
        extensionLoader.setController(extensionController);
        Parent extensionRoot = extensionLoader.load();
        views.put(extensionViewModel.getViewName(), extensionRoot);

        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/org.example.mangodash/settings.fxml"));
        settingsLoader.setController(settingsController);
        Parent settingsRoot = settingsLoader.load();
        views.put(settingsViewModel.getViewName(), settingsRoot);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

