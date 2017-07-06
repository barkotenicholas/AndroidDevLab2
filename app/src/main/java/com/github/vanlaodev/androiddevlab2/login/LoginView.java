package com.github.vanlaodev.androiddevlab2.login;

import com.github.vanlaodev.androiddevlab2.BaseView;

public interface LoginView extends BaseView {
    void navigateToMainView();

    void showErrorMessage(String message);

    void showLoginCancelledMessage();
}
