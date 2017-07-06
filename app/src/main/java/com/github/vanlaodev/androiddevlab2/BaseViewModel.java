package com.github.vanlaodev.androiddevlab2;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

public abstract class BaseViewModel extends BaseObservable {

    public abstract void attachView(BaseView view);

    public abstract void detachView();
}
