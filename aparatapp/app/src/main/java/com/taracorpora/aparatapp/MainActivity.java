package com.taracorpora.aparatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.taracorpora.aparatapp.presenter.MainPresenter;
import com.taracorpora.aparatapp.util.PrefUtil;
import com.taracorpora.aparatapp.view.MainView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements MainView {
    private LoginButton fbButton;
    private CallbackManager callbackManager;
    public static String TAG;
    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TAG = MainActivity.this.getClass().getSimpleName();
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        bindViewById();
        mainPresenter = new MainPresenter(this);
        registeredToken();
        addClickListener();
    }

    private void registeredToken() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) {
            submitFB(accessToken.getUserId());
        }
    }

    private void addClickListener() {
        fbButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        String accessToken = loginResult.getAccessToken().getToken();
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                (jsonObject, response) -> mainPresenter.saveFacebookData(jsonObject, accessToken));
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,email");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }


                    @Override
                    public void onCancel () {
                        Log.d(TAG, "Login attempt cancelled.");
                    }

                    @Override
                    public void onError (FacebookException e){
                        e.printStackTrace();
                        Log.d(TAG, "Login attempt failed.");
                        deleteAccessToken();
                    }
                }
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void submitFB(String fbid) {
        Intent intentHome = new Intent(this, HomeActivity.class);
        intentHome.putExtra("fbid",fbid);
        startActivity(intentHome);
        finish();
    }

    private void bindViewById() {
        fbButton =  findViewById(R.id.login_button);

    }


    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    LoginManager.getInstance().logOut();
                }
            }
        };
    }


    @Override
    public void onSuccess(String fbid) {
        submitFB(fbid);

    }

    @Override
    public void onError() {
        deleteAccessToken();
    }
}
