package com.taracorpora.aparatapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.taracorpora.aparatapp.fragment.DatePickerFragment;
import com.taracorpora.aparatapp.fragment.TimePickerFragment;
import com.taracorpora.aparatapp.view.NewPengumumanView;

import java.util.Calendar;

public class NewPengumumanActivity extends AppCompatActivity  {

    public Button btnSignUp;
    public EditText textTanggal;
    public String tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pengumuman);
        bindViewById();
        addBtnLoginListener();
        addDatePickerListener();
    }

    private void bindViewById() {
        btnSignUp = findViewById(R.id.btn_signup);
        textTanggal = findViewById(R.id.input_tanggal_rapat);
    }

    private void addBtnLoginListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogFragment newFragment = new TimePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "timePicker");
//                DialogFragment newFragment = new DatePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    private void addDatePickerListener() {
        textTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });


    }

    public void bindDataTanggal(String tanggal) {
        this.tanggal = tanggal;
        textTanggal.setText(tanggal);
    }




}
