package com.taracorpora.aparatapp.presenter;

import android.os.Bundle;
import android.util.Log;

import com.taracorpora.aparatapp.model.AparatPesertaModel;
import com.taracorpora.aparatapp.network.AparatNetworkManager;
import com.taracorpora.aparatapp.network.GeneralNetworkHandler;
import com.taracorpora.aparatapp.util.PrefUtil;
import com.taracorpora.aparatapp.view.MainView;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainPresenter implements GeneralNetworkHandler{

    private AparatNetworkManager aparatNetworkManager;
    private MainView mainView;
    private String TAG = MainPresenter.this.getClass().getSimpleName();


    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        aparatNetworkManager = new AparatNetworkManager(this);

    }

    public void saveFacebookData(JSONObject object, String accessToken) {

        try {
            AparatPesertaModel model = new AparatPesertaModel();
            model.fbtoken = accessToken;
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.d("profile_pic", profile_pic + "");
                model.profilepict = profile_pic.toString();

            } catch (MalformedURLException e) {
                model.profilepict = "http://www.meug.be/wp-content/uploads/2017/05/Team-Member.jpg";

            }

            model.fbid = id;
            String firstName = object.getString("first_name");
            String lastName = object.getString("last_name");
            model.name = firstName +" "+lastName;
            model.email = object.getString("email");
            aparatNetworkManager.postLogin(model)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<AparatPesertaModel>() {
                        @Override
                        public void call(AparatPesertaModel aparatPesertaModel) {
                            mainView.onSuccess(id);
                        }
                    }, throwable -> {
                        mainView.onError();
                    });

        } catch (Exception e) {
            mainView.onError();
        }


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

