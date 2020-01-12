package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.models.Speciality;
import com.kosmos.testtask.presentation.ui.BaseView;

import java.util.List;

public interface SpecialitiesFragmentPresenter extends BasePresenter {

    interface View extends BaseView {
        void showSpecialities(List<Speciality> specialities);
    }

    void getAllSpecialities();
}
