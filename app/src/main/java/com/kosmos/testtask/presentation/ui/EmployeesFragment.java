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

    @BindView(R.id.employee_loading_layout)
    View loadingLayout;

    private EmployeesAdapter adapter;
    private EmployeesFragmentPresenter presenter;
    private ApplicationComponent component;
    private FragmentListener fragmentListener;
    private int specialityId;

    public EmployeesFragment() {
        // Required empty public constructor
    }

    public static EmployeesFragment newInstance(ApplicationComponent component,
                                                FragmentListener fragmentListener,
                                                int specialityId) {
        EmployeesFragment fragment = new EmployeesFragment();
        fragment.component = component;
        fragment.fragmentListener = fragmentListener;
        fragment.specialityId = specialityId;
        fragment.presenter = new EmployeesFragmentPresenterImpl(
                new EmployeesFragmentInteractor(fragment.component.getEmployeeRepository(),
                        fragment.component.getSchedueler()),
                fragment,
                fragment.component.getSchedueler()
        );
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
        fragmentListener.employeeClicked(userId);
    }

    @Override
    public void showEmployees(List<Employee> employees) {
        adapter = new EmployeesAdapter(getContext(), employees, this);
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
        void employeeClicked(String userId);
    }
}
