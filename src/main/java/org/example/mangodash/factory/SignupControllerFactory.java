package org.example.mangodash.factory;

import org.example.mangodash.controller.SignupController;
import org.example.mangodash.use_case.data_processing.Signup.SignupInputBoundary;
import org.example.mangodash.use_case.data_processing.Signup.SignupInteractor;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;
import org.example.mangodash.view.Signup.SignupPresenter;
import org.example.mangodash.view.Signup.SignupViewModel;
import org.example.mangodash.view.Login.LoginViewModel;
import org.example.mangodash.view.ViewManagerModel;

public class SignupControllerFactory {

    private final UserDataAccessInterface userDAO;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupControllerFactory(UserDataAccessInterface userDAO,
                                   SignupViewModel signupViewModel,
                                   LoginViewModel loginViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.userDAO = userDAO;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public SignupController createSignupController() {
        // Create the SignupPresenter
        SignupPresenter signupPresenter = new SignupPresenter(signupViewModel, loginViewModel, viewManagerModel);

        // Create the SignupInteractor with its dependencies
        SignupInputBoundary signupInteractor = new SignupInteractor(userDAO, signupPresenter);

        // Create and return the fully initialized SignupController
        return new SignupController(signupInteractor, viewManagerModel);
    }
}
