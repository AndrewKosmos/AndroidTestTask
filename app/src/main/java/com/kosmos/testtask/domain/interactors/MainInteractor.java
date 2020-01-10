package com.kosmos.testtask.domain.interactors;

import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.domain.repositories.WebResponseRepository;
import com.kosmos.testtask.presentation.SchedulersProvider;

import javax.inject.Inject;

import io.reactivex.Single;

public class MainInteractor {

    private WebResponseRepository webResponseRepository;
    private SchedulersProvider schedulersProvider;

    @Inject
    public MainInteractor(WebResponseRepository repository, SchedulersProvider provider) {
        this.webResponseRepository = repository;
        this.schedulersProvider = provider;
    }

    public Single<WebResponse> getWebResponse() {
        return webResponseRepository.getWebResponse().subscribeOn(schedulersProvider.io());
    }

}
