package com.kosmos.testtask.presentation.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kosmos.testtask.R;
import com.kosmos.testtask.di.global.ApplicationComponent;
import com.kosmos.testtask.domain.interactors.ProfileFragmentInteractor;
import com.kosmos.testtask.domain.models.Profile;
import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.presentation.presenters.ProfileFragmentPresenter;
import com.kosmos.testtask.presentation.presenters.ProfileFragmentPresenterImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ProfileFragmentPresenter.View {

    @BindView(R.id.profile_avatar)
    CircleImageView profileAvatar;

    @BindView(R.id.profile_name)
    TextView nameTextView;

    @BindView(R.id.profile_surename)
    TextView surenameTextView;

    @BindView(R.id.profile_loading_layout)
    View loadingLayout;

    @BindView(R.id.profile_birtday)
    TextView birthdayTextView;

    @BindView(R.id.profile_specialities)
    TextView specialitiesTextView;

    private ProfileFragmentPresenter presenter;
    private ApplicationComponent appComponent;
    private String userId;
    private Unbinder unbinder;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(ApplicationComponent component,
                                              String userId) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.appComponent = component;
        fragment.userId = userId;
        fragment.presenter = new ProfileFragmentPresenterImpl(
                new ProfileFragmentInteractor(fragment.appComponent.getProfileRepository(),
                        fragment.appComponent.getSchedueler()),
                fragment,
                fragment.appComponent.getSchedueler()
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.getProfileInfo(userId);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProfileInfo(Profile profile) {
        setAvatar(profile.getAvatarUrl());
        setName(profile.getName());
        setSurename(profile.getLastName());
        setDates(profile.getBirthday());
        setSpecialities(profile.getSpecialities());
    }

    private void setAvatar(String url) {
        if (url == null || url.length() <= 0) {
            profileAvatar.setImageResource(R.mipmap.ic_launcher_round);
        }
        else {
            Glide.with(getContext()).load(url).into(profileAvatar);
        }
    }

    private void setName(String name) {
        nameTextView.setText(name);
    }

    private void setSurename(String surename) {
        surenameTextView.setText(surename);
    }

    private void setDates(String birthday) {
        String result = "";
        if (birthday == null || birthday.length() <= 0) {
            result += " - (Возраст: - )";
        }
        else {
            try {
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                result += new SimpleDateFormat("dd.MM.yyyy").format(date1);
                LocalDate birthDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate nowDate = LocalDate.now();
                Period diff = Period.between(birthDate, nowDate);
                result += " (Возраст: " + diff.getYears() + " )";
            } catch (ParseException e) {
                e.printStackTrace();
                result += " - (Возраст: - )";
            }
        }

        birthdayTextView.setText(result);
    }

    private void setSpecialities(List<Speciality> specialities) {
        String result = "";
        for (int i = 0; i < specialities.size(); i++) {
            result += specialities.get(i).getName();
            if (i != specialities.size() - 1) {
                result += ", ";
            }
        }
        specialitiesTextView.setText(result);
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
}
