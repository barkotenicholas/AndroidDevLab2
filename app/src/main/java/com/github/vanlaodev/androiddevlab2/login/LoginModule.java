package com.github.vanlaodev.androiddevlab2.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginInteracter provideLoginInteracter(LoginInteracterImpl loginInteracter) {
        return loginInteracter;
    }

}
