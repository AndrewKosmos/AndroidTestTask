package com.kosmos.testtask.presentation.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kosmos.testtask.R;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.domain.interactors.SpecialitiesFragmentInteractor;
import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.presentation.adapters.SpecialitiesAdapter;
import com.kosmos.testtask.presentation.presenters.SpecialitiesFragmentPresenter;
import com.kosmos.testtask.presentation.presenters.SpecialitiesFragmentPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialitiesFragment extends Fragment implements SpecialitiesAdapter.AdapterListener,
        SpecialitiesFragmentPresenter.View {

    @BindView(R.id.specialities_recyclerView)
    RecyclerView recyclerView;

    private SpecialitiesAdapter adapter;
    private SpecialitiesFragmentPresenter presenter;
    private ApplicationComponent applicationComponent;

    public SpecialitiesFragment() {
        // Required empty public constructor
    }

    public static SpecialitiesFragment newInstance(ApplicationComponent component) {
        SpecialitiesFragment fragment = new SpecialitiesFragment();
        fragment.applicationComponent = component;
        fragment.presenter = new SpecialitiesFragmentPresenterImpl(
                new SpecialitiesFragmentInteractor(fragment.applicationComponent.getSpecialtyRepository(),
                        fragment.applicationComponent.getSchedueler()),
                fragment,
                fragment.applicationComponent.getSchedueler());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialities, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), recyclerView.getLayoutManager().getLayoutDirection()));
        presenter.getAllSpecialities();
        return view;
    }

    @Override
    public void itemClicked(Integer specialityId) {
        Toast.makeText(getContext(), "Speciality " + specialityId.toString() + " clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSpecialities(List<Speciality> specialities) {
        adapter = new SpecialitiesAdapter(getContext(),specialities,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
