package com.kosmos.testtask.di.global;

import android.content.Context;

import androidx.room.Room;

import com.kosmos.testtask.data.database.dao.EmployeeDao;
import com.kosmos.testtask.data.database.dao.EmployeeSpecialtyDao;
import com.kosmos.testtask.data.database.dao.ProfileDao;
import com.kosmos.testtask.data.database.dao.SpecialityDao;
import com.kosmos.testtask.data.database.global.AppDatabase;
import com.kosmos.testtask.data.network.WebServiceApi;
import com.kosmos.testtask.data.repositories.EmployeeRepositoryImpl;
import com.kosmos.testtask.data.repositories.EmployeeSpecialtyRepositoryImpl;
import com.kosmos.testtask.data.repositories.ProfileRepositoryImpl;
import com.kosmos.testtask.data.repositories.SpecialityRepositoryImpl;
import com.kosmos.testtask.data.repositories.WebResponseRepositoryImpl;
import com.kosmos.testtask.domain.repositories.EmployeeRepository;
import com.kosmos.testtask.domain.repositories.EmployeeSpecialtyRepository;
import com.kosmos.testtask.domain.repositories.ProfileRepository;
import com.kosmos.testtask.domain.repositories.SpecialityRepository;
import com.kosmos.testtask.domain.repositories.WebResponseRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    private final Context context;
    private final String baseUrl;
    private final String databaseName;

    public DataModule(Context context, String baseUrl, String databaseName) {
        this.context= context;
        this.baseUrl = baseUrl;
        this.databaseName = databaseName;
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

    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    EmployeeDao provideEmployeeDao(AppDatabase appDatabase) {
        return appDatabase.employeeDao();
    }

    @Provides
    @Singleton
    SpecialityDao provideSpecialityDao(AppDatabase appDatabase) {
        return appDatabase.specialityDao();
    }

    @Provides
    @Singleton
    ProfileDao provideProfileDao(AppDatabase appDatabase) {
        return appDatabase.profileDao();
    }

    @Provides
    @Singleton
    EmployeeSpecialtyDao provideEmployeeSpecialtyDao(AppDatabase appDatabase) {
        return appDatabase.employeeSpecialtyDao();
    }

    @Provides
    @Singleton
    EmployeeRepository provideEmployeeRepository(EmployeeRepositoryImpl employeeRepository) {
        return employeeRepository;
    }

    @Provides
    @Singleton
    SpecialityRepository provideSpecialityRepository(SpecialityRepositoryImpl specialityRepository) {
        return specialityRepository;
    }

    @Provides
    @Singleton
    ProfileRepository provideProfileRepository(ProfileRepositoryImpl profileRepository) {
        return profileRepository;
    }

    @Provides
    @Singleton
    EmployeeSpecialtyRepository provideEmployeeSpecialtyRepos(EmployeeSpecialtyRepositoryImpl repository) {
        return repository;
    }
}
