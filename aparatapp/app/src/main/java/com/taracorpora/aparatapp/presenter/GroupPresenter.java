package com.taracorpora.aparatapp.presenter;

import com.taracorpora.aparatapp.model.AparatGroupModel;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.view.GroupView;

import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class GroupPresenter implements GeneralNetworkHandler {

    private AparatNetworkManager aparatNetworkManager;
    private GroupView view;
    private String TAG = PengaturanPresenter.class.getSimpleName();


    public GroupPresenter(GroupView view) {
        this.view = view;
        this.aparatNetworkManager = new AparatNetworkManager(this);
    }

    public void getListGroup(String fbid) {
        aparatNetworkManager.getGroup(fbid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AparatGroupModel>>() {
                    @Override
                    public void call(List<AparatGroupModel> aparatGroupModels) {
                        view.showListView(aparatGroupModels);
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
