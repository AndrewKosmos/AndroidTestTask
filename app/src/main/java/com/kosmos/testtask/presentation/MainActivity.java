package com.kosmos.testtask.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kosmos.testtask.R;
import com.kosmos.testtask.TestApplication;
import com.kosmos.testtask.data.database.global.AppDatabase;
import com.kosmos.testtask.data.network.WebServiceApi;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.domain.interactors.MainInteractor;
import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.presentation.presenters.MainPresenter;
import com.kosmos.testtask.presentation.presenters.MainPresenterImpl;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter mainPresenter;
    private ApplicationComponent applicationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applicationComponent = ((TestApplication)getApplication()).getAppComponent();
        mainPresenter = new MainPresenterImpl(new MainInteractor(applicationComponent.getWebResponseRepository(),
                                                applicationComponent.getSchedueler()),
                this, applicationComponent.getSchedueler());
        mainPresenter.getWebResponse();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
