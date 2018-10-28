package com.taracorpora.aparatapp;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.taracorpora.aparatapp.fragment.DatePickerFragment;
import com.taracorpora.aparatapp.fragment.TimePickerFragment;

public class NewPengumumanActivity extends AppCompatActivity {

    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pengumuman);
        bindViewById();
        addBtnLoginListener();
    }

    private void bindViewById() {
        btnSignUp = findViewById(R.id.btn_signup);
    }

    private void addBtnLoginListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment newFragment = new TimePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "timePicker");
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }
}
