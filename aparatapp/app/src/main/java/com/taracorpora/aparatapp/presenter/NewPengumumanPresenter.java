package com.taracorpora.aparatapp.presenter;

import com.taracorpora.aparatapp.model.AparatNewPengumuman;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.view.NewPengumumanView;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class NewPengumumanPresenter implements GeneralNetworkHandler {
    private NewPengumumanView view;
    private AparatNetworkManager networkManager;

    public NewPengumumanPresenter(NewPengumumanView view) {
        this.view = view;
        networkManager = new AparatNetworkManager(this);
    }

    public void saveNewPengumuman(AparatNewPengumuman newPengumuman) {
        networkManager.postNewPengumuman(newPengumuman)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        view.onSuccessCreatePengumuman();
                    }
                }, throwable -> {
                    view.onFailCreatePengumuman();
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
