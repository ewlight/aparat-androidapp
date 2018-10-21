package com.taracorpora.aparatapp.presenter;

import com.taracorpora.aparatapp.model.AparatPesertaModel;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.view.PengaturanView;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PengaturanPresenter implements GeneralNetworkHandler {

    private AparatNetworkManager networkManager;
    private PengaturanView view;
    private String TAG = PengaturanPresenter.class.getSimpleName();

    public PengaturanPresenter(PengaturanView view) {
        this.view = view;
        networkManager = new AparatNetworkManager(this);
    }

    public void showProfile(String fbid) {
        networkManager.getPeserta(fbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AparatPesertaModel>() {
                    @Override
                    public void call(AparatPesertaModel aparatPesertaModel) {
                        view.viewProfileData(aparatPesertaModel);
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
