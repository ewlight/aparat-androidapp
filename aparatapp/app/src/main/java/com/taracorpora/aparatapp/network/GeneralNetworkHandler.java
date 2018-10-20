package com.taracorpora.aparatapp.network;

import retrofit.client.Response;

public interface GeneralNetworkHandler {
    void onNoInternetConnection();

    void onNetworkProblem();

    void onFailedToProcessRequest(Response response);

    void onRequesting();

    void onRequestEnd();
}

