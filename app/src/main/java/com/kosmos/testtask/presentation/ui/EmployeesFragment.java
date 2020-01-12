package com.kosmos.testtask.presentation.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kosmos.testtask.R;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.domain.interactors.EmployeesFragmentInteractor;
import com.kosmos.testtask.domain.models.Employee;
import com.kosmos.testtask.presentation.adapters.EmployeesAdapter;
import com.kosmos.testtask.presentation.presenters.EmployeesFragmentPresenter;
import com.kosmos.testtask.presentation.presenters.EmployeesFragmentPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeesFragment extends Fragment implements EmployeesAdapter.AdapterListener,
        EmployeesFragmentPresenter.View {

    @BindView(R.id.employees_recyclerView)
    RecyclerView recyclerView;

    private EmployeesAdapter adapter;
    private EmployeesFragmentPresenter presenter;
    private ApplicationComponent component;
    private int specialityId;

    public EmployeesFragment() {
        // Required empty public constructor
    }

    public static EmployeesFragment newInstance(ApplicationComponent component,
                                                int specialityId) {
        EmployeesFragment fragment = new EmployeesFragment();
        fragment.component = component;
        fragment.specialityId = specialityId;
        fragment.presenter = new EmployeesFragmentPresenterImpl(
                new EmployeesFragmentInteractor(fragment.component.getEmployeeRepository(),
                        fragment.component.getSchedueler()),
                fragment,
                fragment.component.getSchedueler()
        );
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        presenter.getEmployeesForSpecialty(specialityId);
        return view;
    }

    @Override
    public void itemClicked(String userId) {
        Toast.makeText(getContext(), "Employee " + userId + " clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmployees(List<Employee> employees) {
        adapter = new EmployeesAdapter(getContext(), employees, this);
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
