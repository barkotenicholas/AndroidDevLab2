package com.github.vanlaodev.androiddevlab2;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<B extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity implements BaseView {

    public static final String TAG_ACTIVITY_DATA_HOLDER = "activity_data_holder";
    public static final String KEY_VIEW_MODEL = "view_model";
    @Inject
    Lazy<V> lazyVM;

    protected V viewModel;
    protected B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        ActivityDataHolder activityDataHolder = (ActivityDataHolder) fm.findFragmentByTag(TAG_ACTIVITY_DATA_HOLDER);
        if (activityDataHolder == null) {
            activityDataHolder = new ActivityDataHolder();
            fm.beginTransaction().add(activityDataHolder, TAG_ACTIVITY_DATA_HOLDER).commit();
        }
        viewModel = (V) activityDataHolder.get(KEY_VIEW_MODEL);
        if (viewModel == null) {
            viewModel = lazyVM.get();
            activityDataHolder.put(KEY_VIEW_MODEL, viewModel);
        }
        viewModel.attachView(this);
    }

    @Override
    protected void onDestroy() {
        viewModel.detachView();
        super.onDestroy();
    }

    protected void setAndBindingContentView(int layoutResId) {
        binding = DataBindingUtil.setContentView(this, layoutResId);
        binding.setVariable(BR.vm, viewModel);
    }
}
