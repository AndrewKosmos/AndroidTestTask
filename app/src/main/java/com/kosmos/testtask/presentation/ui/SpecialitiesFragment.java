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

    @BindView(R.id.spec_loading_layout)
    View loadingLayout;

    private SpecialitiesAdapter adapter;
    private SpecialitiesFragmentPresenter presenter;
    private ApplicationComponent applicationComponent;
    private FragmentListener fragmentListener;

    public SpecialitiesFragment() {
        // Required empty public constructor
    }

    public static SpecialitiesFragment newInstance(ApplicationComponent component,
                                                   FragmentListener fragmentListener) {
        SpecialitiesFragment fragment = new SpecialitiesFragment();
        fragment.applicationComponent = component;
        fragment.fragmentListener = fragmentListener;
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
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        presenter.getAllSpecialities();
        return view;
    }

    @Override
    public void itemClicked(int specialityId) {
        fragmentListener.specialityClicked(specialityId);
    }

    @Override
    public void showSpecialities(List<Speciality> specialities) {
        adapter = new SpecialitiesAdapter(getContext(),specialities,this);
        recyclerView.setAdapter(adapter);
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

    }

    public interface FragmentListener {
        void specialityClicked(int specialityId);
    }
}
