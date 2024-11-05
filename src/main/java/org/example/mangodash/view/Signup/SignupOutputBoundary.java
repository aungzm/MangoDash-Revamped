package org.example.mangodash.view.Signup;

import org.example.mangodash.use_case.data_processing.Signup.SignupDataOutput;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupDataOutput signupDataOutput);

    void prepareFailView(String error);
}
