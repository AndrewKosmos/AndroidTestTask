package com.kosmos.testtask.di.global;

import android.content.Context;

import com.kosmos.testtask.presentation.SchedulersProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context context;
    private final SchedulersProvider schedulersProvider;


    public ApplicationModule(Context context, SchedulersProvider provider) {
        this.context = context.getApplicationContext();
        this.schedulersProvider = provider;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    SchedulersProvider provideSchedulersProvider() { return schedulersProvider; }
}
