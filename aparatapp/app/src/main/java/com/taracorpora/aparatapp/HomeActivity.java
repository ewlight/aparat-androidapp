package com.taracorpora.aparatapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class HomeActivity extends AppCompatActivity {
    private ImageView groupButton;
    private ImageView pengumumanButton;
    private ImageView pengaturanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_pengumuman));
        bindViewById();
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewGroup();
            }
        });
        pengaturanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPengaturan();
            }
        });
    }
    private void viewGroup()
    {
        Intent intentGroup = new Intent(this, GroupActivity.class);
        startActivity(intentGroup);

    }
    private void viewPengaturan()
    {
        Intent intentPengaturan = new Intent (this, PengaturanActivity.class);
        startActivity(intentPengaturan);

    }
    private void bindViewById()
    {
        groupButton = findViewById(R.id.groupLogo1);
        pengumumanButton = findViewById(R.id.pengumumanLogo1);
        pengumumanButton.setBackgroundColor(Color.parseColor("#002171"));
        pengaturanButton = findViewById(R.id.pengaturanLogo1);

    }
}
