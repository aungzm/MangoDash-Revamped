package org.example.mangodash.use_case.data_processing.Login;

import java.net.MalformedURLException;

public interface LoginInputBoundary {
    void executeLogin (LoginDataInput loginDataInput) throws MalformedURLException;

    void executeForgotPassword(LoginDataInput loginDataInput);
}
