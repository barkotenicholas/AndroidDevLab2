package com.github.vanlaodev.androiddevlab2.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.vanlaodev.androiddevlab2.AuthenticatedActivity;
import com.github.vanlaodev.androiddevlab2.R;
import com.github.vanlaodev.androiddevlab2.databinding.ActivityMainBinding;
import com.github.vanlaodev.androiddevlab2.login.LoginActivity;

public class MainActivity extends AuthenticatedActivity<ActivityMainBinding, MainViewModel> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindingContentView(R.layout.activity_main);
    }

    @Override
    public void navigateToLoginView() {
        startActivity(LoginActivity.intent(this));
    }

    public static Intent intent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}
