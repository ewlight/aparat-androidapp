package com.taracorpora.aparatapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class GroupActivity extends AppCompatActivity {
    private ImageView groupButton;
    private ImageView pengumumanButton;
    private ImageView pengaturanButton;
    private static String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        TAG = GroupActivity.this.getClass().getSimpleName();
        getSupportActionBar().setTitle("Group");
        bindViewById();
        pengaturanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "pengaturan di tekan");
        }
        });

        pengumumanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Pengumuman Di Klik");
            }
        });

    }
    private void viewPengaturan() {
        Intent intentPengaturan = new Intent(this, PengaturanActivity.class);
        startActivity(intentPengaturan);

    }
    private void viewPengumuman() {
        Intent intentPengumuman = new Intent(this, HomeActivity.class);
        startActivity(intentPengumuman);

    }
    private void bindViewById() {
        groupButton = findViewById(R.id.groupLogo2);
        groupButton.setBackgroundColor(Color.parseColor("#002171"));
        pengumumanButton = findViewById(R.id.pengumumanLogo2);
        pengaturanButton = findViewById(R.id.pengaturanLogo2);

    }
}

