package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.domain.models.Profile;
import com.kosmos.testtask.presentation.ui.BaseView;

public interface ProfileFragmentPresenter extends BasePresenter {

    interface View extends BaseView {
        void showProfileInfo(Profile profile);
    }

    void getProfileInfo(String userId);

}
