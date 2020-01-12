package com.kosmos.testtask.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kosmos.testtask.R;
import com.kosmos.testtask.TestApplication;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.domain.interactors.MainInteractor;
import com.kosmos.testtask.presentation.presenters.MainPresenter;
import com.kosmos.testtask.presentation.presenters.MainPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @BindView(R.id.loading_layout)
    View loadingLayout;

    private MainPresenter mainPresenter;
    private ApplicationComponent applicationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        applicationComponent = ((TestApplication)getApplication()).getAppComponent();
        mainPresenter = new MainPresenterImpl(new MainInteractor(applicationComponent.getWebResponseRepository(),
                                                applicationComponent.getEmployeeRepository(),
                                                applicationComponent.getSpecialtyRepository(),
                                                applicationComponent.getEmployeeSpecialtyRepository(),
                                                applicationComponent.getSchedueler()),
                this, applicationComponent.getSchedueler());
        mainPresenter.getWebResponse();
    }

    @Override
    public void showProgress() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
