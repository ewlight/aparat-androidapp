package com.taracorpora.aparatapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.login.LoginManager;
import com.taracorpora.aparatapp.model.AparatPesertaModel;
import com.taracorpora.aparatapp.presenter.PengaturanPresenter;
import com.taracorpora.aparatapp.view.PengaturanView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Pengaturan extends Fragment implements PengaturanView {

    private PengaturanPresenter presenter;
    private CircleImageView profileCircleImage;
    private TextView nameText;
    private TextView emailText;
    private ImageView qrCodeImageView;
    private TextView fbidText;
    private Button createGroup;
    private Button logoutButton;
    private String fbid;
    private HomeActivity parentActivity;

    public static Pengaturan newInstance(String fbid) {
        Bundle args = new Bundle();
        args.putString("fbid", fbid);
        Pengaturan fragment = new Pengaturan();
        fragment.setArguments(args);
        return fragment;
    }


    private void bindViewById(View view) {
        profileCircleImage = view.findViewById(R.id.profile_image);
        nameText = view.findViewById(R.id.textnama);
        emailText = view.findViewById(R.id.textemail);
        qrCodeImageView = view.findViewById(R.id.imagebarcode);
        fbidText = view.findViewById(R.id.textid);
        createGroup = view.findViewById(R.id.buttonbuatgroup);
        logoutButton = view.findViewById(R.id.buttonlogout);

    }

    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (HomeActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDataFromBundle();
        return inflater.inflate(R.layout.pengaturan1, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new PengaturanPresenter(this);
        bindViewById(view);
        addLogoutListener();
        presenter.showProfile(fbid);

    }

    @Override
    public void viewProfileData(AparatPesertaModel peserta) {
        loadImage(peserta.profilepict, profileCircleImage);
        loadQRImage("https://neo.loket.com/barcode/gen_barcode2d/"+peserta.fbid, qrCodeImageView);
        nameText.setText(peserta.name);
        emailText.setText(peserta.email);
        fbidText.setText(peserta.fbid);

    }

    @Override
    public void onError() {

    }


    private void getDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            fbid = bundle.getString("fbid");
        }
    }

    private void loadImage(String img, CircleImageView view){
        Glide
           .with(this)
           .load(img)
           .into(view);
    }

    private  void loadQRImage(String img, ImageView view) {
        Glide
                .with(this)
                .load(img)
                .into(view);
    }

    private void addLogoutListener() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                parentActivity.openSignoutPage();

            }
        });
    }


}
