package com.github.vanlaodev.androiddevlab2.login;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;

import com.github.vanlaodev.androiddevlab2.BR;
import com.github.vanlaodev.androiddevlab2.BaseViewModelEx;
import com.github.vanlaodev.androiddevlab2.UserSessionManager;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModelEx<LoginView> {

    private final LoginInteracter loginInteracter;
    private String username;
    private String password;
    private LoginInteracter.Cancellable loginSubscription;
    private final UserSessionManager userSessionManager;

    public final ObservableBoolean loggingIn = new ObservableBoolean(false);

    private final OnPropertyChangedCallback onPropertyChangedCallback = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (sender == loggingIn) {
                notifyPropertyChanged(BR.canLogin);
            }
        }
    };

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
        notifyPropertyChanged(BR.canLogin);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        notifyPropertyChanged(BR.canLogin);
    }

    @Bindable
    public boolean isCanLogin() {
        return !loggingIn.get() && username != null && password != null && !username.isEmpty() && !password.isEmpty();
    }

    @Inject
    public LoginViewModel(LoginInteracter loginInteracter, UserSessionManager userSessionManager) {
        this.loginInteracter = loginInteracter;
        this.userSessionManager = userSessionManager;

        loggingIn.addOnPropertyChangedCallback(onPropertyChangedCallback);
    }

    public void cancelLogin() {
        if (loginSubscription != null) {
            loginSubscription.cancel();
            loginSubscription = null;
        }
    }

    public void login() {
        loggingIn.set(true);

        loginSubscription = loginInteracter.login(username, password, new LoginInteracter.Callback() {
            @Override
            public void onSuccess() {
                loggingIn.set(false);
                userSessionManager.setLoggedInUser(username);
                LoginView view = getView();
                if (view != null) {
                    view.navigateToMainView();
                }
            }

            @Override
            public void onFailed(String msg) {
                loggingIn.set(false);
                LoginView view = getView();
                if (view != null) {
                    view.showErrorMessage(msg);
                }
            }

            @Override
            public void onCancelled() {
                loggingIn.set(false);
                LoginView view = getView();
                if (view != null) {
                    view.showLoginCancelledMessage();
                }
            }
        });
    }

}
