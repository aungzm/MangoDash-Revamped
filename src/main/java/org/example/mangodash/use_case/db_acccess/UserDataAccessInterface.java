package org.example.mangodash.use_case.db_acccess;

import org.example.mangodash.model.User;

public interface UserDataAccessInterface {

    /**
     * Checks if a user with the specified username exists.
     * @param identifier The username to check.
     * @return True if the username exists, false otherwise.
     */
    boolean existsByName(String identifier);

    /**
     * Saves a user to the data source.
     * @param user The user to save.
     */
    void save(User user);

    /**
     * Modifies the user's password.
     * @param username The username of the user.
     * @param newPassword The new password to set.
     */
    void modifyUserPassword(String username, String newPassword);

    /**
     * Modifies the user's name and bio.
     * @param username The username of the user.
     * @param newBio The new bio to set.
     */
    void modifyUserBio(String username, String newBio);

    void modifyUserName(String username, String name);

    /**
     * Retrieves a user by username.
     * @param username The username of the user.
     * @return The User object if found, null otherwise.
     */
    User getUser(String username);
}
