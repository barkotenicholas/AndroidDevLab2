package com.github.vanlaodev.androiddevlab2.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.github.vanlaodev.androiddevlab2.BaseActivity;
import com.github.vanlaodev.androiddevlab2.ProgressDialogFragment;
import com.github.vanlaodev.androiddevlab2.R;
import com.github.vanlaodev.androiddevlab2.databinding.ActivityLoginBinding;
import com.github.vanlaodev.androiddevlab2.main.MainActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginView {

    public static final String TAG_DIALOG_PROGRESS = "dialog_progress";
    private final Observable.OnPropertyChangedCallback onPropertyChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (sender == viewModel.loggingIn) {
                if (viewModel.loggingIn.get()) {
                    tryShowLoggingInDialog();
                } else {
                    tryHideLoggingInDialog();
                }
            }
        }
    };

    private void tryHideLoggingInDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) fm.findFragmentByTag(TAG_DIALOG_PROGRESS);
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
        }
    }

    private void tryShowLoggingInDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) fm.findFragmentByTag(TAG_DIALOG_PROGRESS);
        if (progressDialogFragment == null) {
            progressDialogFragment = ProgressDialogFragment.create();
            progressDialogFragment.setCallback(new ProgressDialogFragment.Callback() {
                @Override
                public void onCancel() {
                    viewModel.cancelLogin();
                }
            });
            progressDialogFragment.show(fm, TAG_DIALOG_PROGRESS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindingContentView(R.layout.activity_login);

        viewModel.loggingIn.addOnPropertyChangedCallback(onPropertyChangedCallback);

        FragmentManager fm = getSupportFragmentManager();
        ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) fm.findFragmentByTag(TAG_DIALOG_PROGRESS);
        if (progressDialogFragment != null) {
            progressDialogFragment.setCallback(new ProgressDialogFragment.Callback() {
                @Override
                public void onCancel() {
                    viewModel.cancelLogin();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        viewModel.loggingIn.removeOnPropertyChangedCallback(onPropertyChangedCallback);
        super.onDestroy();
    }

    @Override
    public void navigateToMainView() {
        startActivity(MainActivity.intent(this));
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginCancelledMessage() {
        Toast.makeText(this, "Login cancelled.", Toast.LENGTH_SHORT).show();
    }

    public static Intent intent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}
