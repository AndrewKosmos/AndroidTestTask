package com.kosmos.testtask.di.global;

import com.kosmos.testtask.data.network.WebServiceApi;
import com.kosmos.testtask.data.repositories.WebResponseRepositoryImpl;
import com.kosmos.testtask.domain.repositories.WebResponseRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    private final String baseUrl;

    public DataModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    WebResponseRepository provideWebResponseRepository(WebResponseRepositoryImpl webResponseRepository) {
        return webResponseRepository;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WebServiceApi provideWebServiceApi(Retrofit retrofit) {
        return retrofit.create(WebServiceApi.class);
    }
}
