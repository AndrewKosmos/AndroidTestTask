package com.kosmos.testtask.data.network;

import com.kosmos.testtask.domain.models.WebResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WebServiceApi {

    @GET("testTask.json")
    Single<WebResponse> getWebResponse();

}
