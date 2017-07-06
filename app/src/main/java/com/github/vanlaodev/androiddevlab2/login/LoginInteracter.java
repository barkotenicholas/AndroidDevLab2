package com.github.vanlaodev.androiddevlab2.login;

public interface LoginInteracter {

    interface Callback {
        void onSuccess();

        void onFailed(String msg);

        void onCancelled();
    }

    interface Cancellable {
        void cancel();
    }

    Cancellable login(String username, String password, Callback callback);
}
