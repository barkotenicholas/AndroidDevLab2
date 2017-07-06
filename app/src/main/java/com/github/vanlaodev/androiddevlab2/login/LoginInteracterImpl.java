package com.github.vanlaodev.androiddevlab2.login;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginInteracterImpl implements LoginInteracter {

    @Inject
    public LoginInteracterImpl() {
    }

    @Override
    public Cancellable login(final String username, final String password, final Callback callback) {
        final Disposable disposable = Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Thread.sleep(3000);
                if (!username.equals(password)) {
                    throw new Exception("Failed to login.");
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                if (callback != null) {
                    callback.onCancelled();
                }
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                if (callback != null) {
                    callback.onSuccess();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                if (callback != null) {
                    callback.onFailed(throwable.getMessage());
                }
            }
        });
        return new Cancellable() {
            @Override
            public void cancel() {
                if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
        };
    }
}
