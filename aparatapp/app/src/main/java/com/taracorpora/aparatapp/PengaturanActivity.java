package com.taracorpora.aparatapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PengaturanActivity extends AppCompatActivity {
    private ImageView groupButton;
    private ImageView pengumumanButton;
    private ImageView pengaturanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        getSupportActionBar().setTitle("Pengaturan");
        bindViewById();
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewGroup();
            }
        });
        pengumumanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPengumuman();
            }
        });
    }
    private void viewGroup()
    {
        Intent intentGroup = new Intent(this, GroupActivity.class);
        startActivity(intentGroup);

    }
    private void viewPengumuman()
    {
        Intent intentPengumuman = new Intent (this, HomeActivity.class);
        startActivity(intentPengumuman);

    }
    private void bindViewById()
    {
        groupButton = findViewById(R.id.groupLogo3);
        pengumumanButton = findViewById(R.id.pengumumanLogo3);
        pengaturanButton = findViewById(R.id.pengaturanLogo3);
        pengaturanButton.setBackgroundColor(Color.parseColor("#002171"));
    }
}
