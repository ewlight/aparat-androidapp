package com.taracorpora.aparatapp.presenter;

import com.taracorpora.aparatapp.model.AparatGroupRequestModel;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.view.HomeView;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class HomePresenter implements GeneralNetworkHandler {

    private HomeView view;
    private AparatNetworkManager networkManager;
    private String TAG = HomePresenter.class.getSimpleName();

    public HomePresenter(HomeView view) {
        this.view = view;
        networkManager = new AparatNetworkManager(this);
    }

    public void saveGroupData(AparatGroupRequestModel group) {
        networkManager.postNewGroup(group)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AparatGroupRequestModel>() {
                    @Override
                    public void call(AparatGroupRequestModel aparatGroupRequestModel) {
                        view.onSuccessSaveGroup(group.groupname);
                    }
                }, throwable -> {
                    view.onError();
                });
    }

    @Override
    public void onNoInternetConnection() {

    }

    @Override
    public void onNetworkProblem() {

    }

    @Override
    public void onFailedToProcessRequest(Response response) {

    }

    @Override
    public void onRequesting() {

    }

    @Override
    public void onRequestEnd() {

    }
}
