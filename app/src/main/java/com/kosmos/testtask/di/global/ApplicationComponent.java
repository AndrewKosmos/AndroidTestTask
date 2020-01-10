package com.kosmos.testtask.di.global;

import android.content.Context;

import com.kosmos.testtask.data.network.WebServiceApi;
import com.kosmos.testtask.domain.repositories.WebResponseRepository;
import com.kosmos.testtask.presentation.SchedulersProvider;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface ApplicationComponent {

    Context getApplicationContext();
    WebResponseRepository getWebResponseRepository();
    Retrofit getRetrofit();
    WebServiceApi getWebServiceApi();
    SchedulersProvider getSchedueler();

}
