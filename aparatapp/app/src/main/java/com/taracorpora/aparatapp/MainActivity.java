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
import com.taracorpora.aparatapp.util.PrefUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private LoginButton fbButton;
    private TextView info;
    private CallbackManager callbackManager;
    public static String TAG;
    private AccessToken accessToken;
    PrefUtil prefUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) {
            submitFB();
        }



        TAG = MainActivity.this.getClass().getSimpleName();
        callbackManager = CallbackManager.Factory.create();
        prefUtil = new PrefUtil(this);
        setContentView(R.layout.activity_main);
        bindViewById();
        fbButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        String accessToken = loginResult.getAccessToken().getToken();

                        prefUtil.saveAccessToken(accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject jsonObject,
                                                            GraphResponse response) {

                                       Bundle facebookData = getFacebookData(jsonObject);
                                       submitFB();

                                    }
                                });

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

    private void submitFB() {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
        finish();
    }

    private void bindViewById() {
        fbButton =  findViewById(R.id.login_button);
        info =  findViewById(R.id.textInfo);
    }




    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.d("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));



            prefUtil.saveFacebookUserInfo(object.getString("first_name"),
                    object.getString("last_name"),object.getString("email"),
                    profile_pic.toString());

        } catch (Exception e) {
            Log.d(TAG, "BUNDLE Exception : "+e.toString());
        }

        return bundle;
    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    prefUtil.clearToken();
                    LoginManager.getInstance().logOut();
                }
            }
        };
    }


}
