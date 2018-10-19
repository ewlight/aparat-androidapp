package com.taracorpora.aparatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button fbButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbButton = (Button) findViewById(R.id.buttonFacebook);
        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFB();
            }
        });
    }

    private void submitFB() {
        Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }
}
