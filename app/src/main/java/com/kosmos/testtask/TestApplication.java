package com.kosmos.testtask;

import android.app.Application;

import com.kosmos.testtask.data.network.Constants;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.di.global.ApplicationModule;
import com.kosmos.testtask.di.global.DaggerApplicationComponent;
import com.kosmos.testtask.di.global.DataModule;
import com.kosmos.testtask.presentation.SchedulersProvider;

public class TestApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, new SchedulersProvider()))
                .dataModule(new DataModule(Constants.WEB_BASE_URL))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
