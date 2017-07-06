package com.github.vanlaodev.androiddevlab2.main;

import android.databinding.ObservableField;

import com.github.vanlaodev.androiddevlab2.BaseViewModelEx;
import com.github.vanlaodev.androiddevlab2.UserSessionManager;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModelEx<MainView> {

    private final UserSessionManager userSessionManager;
    public final ObservableField<String> loggedInUser = new ObservableField<>();

    @Inject
    public MainViewModel(UserSessionManager userSessionManager) {
        this.userSessionManager = userSessionManager;

        loggedInUser.set(userSessionManager.getLoggedInUser());
    }

    public void logout() {
        userSessionManager.setLoggedInUser(null);
        MainView view = getView();
        if (view != null) {
            view.navigateToLoginView();
        }
    }

}
