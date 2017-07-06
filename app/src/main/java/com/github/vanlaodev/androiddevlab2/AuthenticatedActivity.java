package com.github.vanlaodev.androiddevlab2;

import android.databinding.ViewDataBinding;

import com.github.vanlaodev.androiddevlab2.login.LoginActivity;

import javax.inject.Inject;

public abstract class AuthenticatedActivity<B extends ViewDataBinding, V extends BaseViewModel> extends BaseActivity<B, V> {

    @Inject
    UserSessionManager userSessionManager;

    @Override
    protected void onResume() {
        super.onResume();
        if (userSessionManager.getLoggedInUser() == null) {
            startActivity(LoginActivity.intent(this));
        }
    }
}
