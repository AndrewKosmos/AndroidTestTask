package com.kosmos.testtask.data.repositories;

import com.kosmos.testtask.data.network.WebServiceApi;
import com.kosmos.testtask.domain.models.WebResponse;
import com.kosmos.testtask.domain.repositories.WebResponseRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class WebResponseRepositoryImpl implements WebResponseRepository {

    private WebServiceApi webServiceApi;

    @Inject
    public WebResponseRepositoryImpl(WebServiceApi webServiceApi) {
        this.webServiceApi = webServiceApi;
    }

    @Override
    public Single<WebResponse> getWebResponse() {
        return webServiceApi.getWebResponse();
    }
}
