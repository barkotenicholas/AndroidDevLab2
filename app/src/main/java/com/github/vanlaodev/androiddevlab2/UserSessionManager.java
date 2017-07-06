package com.github.vanlaodev.androiddevlab2;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserSessionManager {

    private static final String KEY_LOGGED_IN_USER = "loggedInUser";
    private final SharedPreferences sharedPreferences;

    @Inject
    public UserSessionManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getLoggedInUser() {
        return sharedPreferences.getString(KEY_LOGGED_IN_USER, null);
    }

    public void setLoggedInUser(String loggedInUser) {
        sharedPreferences.edit().putString(KEY_LOGGED_IN_USER, loggedInUser).apply();
    }
}
