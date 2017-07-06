package com.github.vanlaodev.androiddevlab2;

import java.lang.ref.WeakReference;

public abstract class BaseViewModelEx<V extends BaseView> extends BaseViewModel {

    private WeakReference<V> view;

    protected V getView() {
        return view == null ? null : view.get();
    }

    @Override
    public void attachView(BaseView view) {
        this.view = new WeakReference<>((V) view);
    }

    @Override
    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }
}
