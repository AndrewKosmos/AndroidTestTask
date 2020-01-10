package com.kosmos.testtask.presentation.presenters;

import com.kosmos.testtask.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {

    }

    void getWebResponse();

}
