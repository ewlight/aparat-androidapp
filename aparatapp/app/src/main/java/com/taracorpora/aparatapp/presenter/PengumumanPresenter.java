package com.taracorpora.aparatapp.presenter;

import com.taracorpora.aparatapp.model.AparatNewPengumuman;
import com.taracorpora.aparatapp.model.AparatPengumumanModel;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.view.PengumumanView;

import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PengumumanPresenter implements GeneralNetworkHandler {
    private PengumumanView view;
    private AparatNetworkManager networkManager;

    public PengumumanPresenter(PengumumanView view) {
        this.view = view;
        networkManager = new AparatNetworkManager(this);
    }

    public void getListPengumuman(String fbid) {
        networkManager.getListPengumuman(fbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AparatPengumumanModel>>() {
                    @Override
                    public void call(List<AparatPengumumanModel> aparatPengumumanModels) {
                        view.showPengmumulanList(aparatPengumumanModels);
                    }
                }, throwable -> {
                    view.onErrorShowPengumuman();
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
