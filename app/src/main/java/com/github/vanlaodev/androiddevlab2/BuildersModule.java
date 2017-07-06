package com.github.vanlaodev.androiddevlab2;

import com.github.vanlaodev.androiddevlab2.login.LoginActivity;
import com.github.vanlaodev.androiddevlab2.login.LoginModule;
import com.github.vanlaodev.androiddevlab2.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface BuildersModule {

    @ContributesAndroidInjector(modules = {LoginModule.class})
    LoginActivity contributesLoginActivityAndroidInjector();

    @ContributesAndroidInjector
    MainActivity contributesMainActivityAndroidInjector();
}
