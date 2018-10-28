package com.taracorpora.aparatapp.presenter;

import com.taracorpora.aparatapp.model.AparatGroupMemberModel;
import com.taracorpora.aparatapp.model.AparatNewGroupMemberModel;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.view.GroupDetailView;

import java.util.List;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class GroupDetailPresenter implements GeneralNetworkHandler{

    private GroupDetailView view;
    private AparatNetworkManager networkManager;

    public GroupDetailPresenter(GroupDetailView view) {
        this.view = view;
        networkManager = new AparatNetworkManager(this);
    }

    public void getGroupMember(int groupId) {
        networkManager.getGroupMember(groupId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AparatGroupMemberModel>>() {
                    @Override
                    public void call(List<AparatGroupMemberModel> aparatGroupMemberModels) {
                        view.showGroupMember(aparatGroupMemberModels);
                    }
                },throwable -> {
                    view.onError("Network Error", "System gagal memproses request anda..!");
                });
    }

    public void saveNewMember(AparatNewGroupMemberModel groupMember) {
        networkManager.postGroupMember(groupMember)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {
                        view.updateGroupMember(groupMember);
                    }
                }, throwable -> {
                    view.onError("Proses Gagal", "Maaf... System tidak bisa menambahkan member baru");
                });
    }


    @Override
    public void onNoInternetConnection() {
        view.onError("Jaringan Bermasalah", "Opps... Kami tidak bisa mengakses server aparat. Apakah anda online?");
    }

    @Override
    public void onNetworkProblem() {

    }

    @Override
    public void onFailedToProcessRequest(Response response) {

        view.onError("Network Error", "Gagal Menambahkan member baru");
    }

    @Override
    public void onRequesting() {

    }

    @Override
    public void onRequestEnd() {

    }
}
