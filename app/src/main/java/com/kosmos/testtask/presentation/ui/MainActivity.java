package com.kosmos.testtask.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.kosmos.testtask.R;
import com.kosmos.testtask.TestApplication;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.domain.interactors.MainInteractor;
import com.kosmos.testtask.presentation.presenters.MainPresenter;
import com.kosmos.testtask.presentation.presenters.MainPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.View,
        SpecialitiesFragment.FragmentListener,
        EmployeesFragment.FragmentListener {

    @BindView(R.id.loading_layout)
    View loadingLayout;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private MainPresenter mainPresenter;
    private ApplicationComponent applicationComponent;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        applicationComponent = ((TestApplication)getApplication()).getAppComponent();
        fragmentManager = getSupportFragmentManager();
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

    @Override
    public void showSpecialitiesFragment() {
        SpecialitiesFragment specialitiesFragment = SpecialitiesFragment.newInstance(applicationComponent,
                this);
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, specialitiesFragment)
                .commit();
    }

    @Override
    public void specialityClicked(int specialityId) {
        EmployeesFragment employeesFragment = EmployeesFragment.newInstance(applicationComponent,
                this,
                specialityId);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, employeesFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void employeeClicked(String userId) {
        ProfileFragment profileFragment = ProfileFragment.newInstance(applicationComponent,
                userId);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, profileFragment)
                .addToBackStack(null)
                .commit();
    }
}
