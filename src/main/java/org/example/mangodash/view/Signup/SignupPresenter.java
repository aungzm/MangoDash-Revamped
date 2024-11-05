package org.example.mangodash.view.Signup;

import org.example.mangodash.use_case.data_processing.Signup.SignupDataOutput;
import org.example.mangodash.view.Login.LoginState;
import org.example.mangodash.view.Login.LoginViewModel;
import org.example.mangodash.view.ViewManagerModel;

public class SignupPresenter implements SignupOutputBoundary{

    private final SignupViewModel signupViewModel;

    private final LoginViewModel loginViewModel;

    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SignupDataOutput signupDataOutput) {
        loginViewModel.getState().setUsername(signupDataOutput.getUsername());
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        signupViewModel.getState().setError(error);
        signupViewModel.firePropertyChanged();
    }
}
