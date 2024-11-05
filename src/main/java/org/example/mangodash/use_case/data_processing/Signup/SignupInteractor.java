package org.example.mangodash.use_case.data_processing.Signup;

import org.example.mangodash.model.CommonUser;
import org.example.mangodash.model.User;
import org.example.mangodash.use_case.db_acccess.UserDataAccessInterface;
import org.example.mangodash.view.Signup.SignupOutputBoundary;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    private final UserDataAccessInterface userDataAccessInterface;

    private final SignupOutputBoundary signupOutputBoundary;

    public SignupInteractor(UserDataAccessInterface userDataAccessInterface, SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.signupOutputBoundary = signupOutputBoundary;
    }


    @Override
    public void executeSignup(SignupDataInput signupDataInput) {
        String username = signupDataInput.getUsername();
        String name = signupDataInput.getName();
        String newPassword = signupDataInput.getNewPassword();
        String repeatPassword = signupDataInput.getRepeatNewPassword();
        String bio = signupDataInput.getBio();
        LocalDateTime creationDate = signupDataInput.getCreationTime();

        if(!userDataAccessInterface.existsByName(username)){
            signupOutputBoundary.prepareFailView("Username " + username + "already exists");
        }
        if (!name.matches("^[A-Za-z ]+$")) {
            signupOutputBoundary.prepareFailView(name  + " is not a valid name. Can only contains letters and space");
        }

        if(!newPassword.equals(repeatPassword)){
            signupOutputBoundary.prepareFailView("Passwords does not match");
        }
        User user = new CommonUser(name, username, newPassword, bio, creationDate);
        userDataAccessInterface.save(user);
        SignupDataOutput signUpOutputData = new SignupDataOutput(username);
        signupOutputBoundary.prepareSuccessView(signUpOutputData);
    }
}
