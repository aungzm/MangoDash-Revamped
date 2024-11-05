package org.example.mangodash.use_case.data_processing.Signup;

import java.time.LocalDateTime;

public class SignupDataInput {
    final private String name;

    final private String username;

    final private String newPassword;

    final private String repeatNewPassword;

    final private LocalDateTime creationTime;

    final private String bio;

    public SignupDataInput(String name, String username, String newPassword, String repeatNewPassword, LocalDateTime creationTime, String bio) {
        this.name = name;
        this.username = username;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
        this.creationTime = creationTime;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getBio() {
        return bio;
    }
}
