package com.kosmos.testtask.domain.repositories;

import com.kosmos.testtask.domain.models.WebResponse;

import io.reactivex.Single;

public interface WebResponseRepository {

    Single<WebResponse> getWebResponse();

}
