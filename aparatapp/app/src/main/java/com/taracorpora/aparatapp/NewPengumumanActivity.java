package com.taracorpora.aparatapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.taracorpora.aparatapp.fragment.DatePickerFragment;
import com.taracorpora.aparatapp.fragment.TimePickerFragment;
import com.taracorpora.aparatapp.model.AparatNewPengumuman;
import com.taracorpora.aparatapp.presenter.NewPengumumanPresenter;
import com.taracorpora.aparatapp.view.NewPengumumanView;

import java.util.Calendar;

import rx.android.schedulers.AndroidSchedulers;

public class NewPengumumanActivity extends AppCompatActivity implements NewPengumumanView  {

    public Button btnPengumumanRapat;
    public TextView textTanggal;
    public TextView textJam;
    public String tanggal ="";
    public String jam = "";
    public String fbid;
    public int groupId;
    public String judulrapat = "";
    public String deskripsiRapat ="";
    public CircleProgressBar progressBar;
    public EditText inputJudulRapat;
    public EditText inputDeskripsiRapat;
    private NewPengumumanPresenter presenter;
    private CardView cardTanggalRapat;
    private CardView cardJamRapat;
    private String TAG = NewPengumumanActivity.class.getSimpleName();
    public String groupName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pengumuman);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fbid = bundle.getString("fbid");
            groupId = bundle.getInt("groupid");
            groupName = bundle.getString("grupname");
        }
        bindViewById();
        getSupportActionBar().setTitle("Pengumuman Baru - "+ groupName);
        presenter = new NewPengumumanPresenter(this);
        addDatePickerListener();
        addTimePickerListener();
        addButtonRapatListener();
    }

    private void bindViewById() {
        btnPengumumanRapat = findViewById(R.id.btn_pengumuman_rapat);
        textTanggal = findViewById(R.id.input_tanggal_rapat);
        textJam = findViewById(R.id.input_jam_rapat);
        inputJudulRapat = findViewById(R.id.input_judul_rapat);
        inputDeskripsiRapat = findViewById(R.id.input_deskripsi_rapat);
        progressBar = findViewById(R.id.progressbar_new_rapat);
        cardTanggalRapat = findViewById(R.id.tanggal_rapat_card);
        cardJamRapat = findViewById(R.id.jam_rapat_card);
    }

    private void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void hideProgressBar(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void populateData() {
        judulrapat = inputJudulRapat.getText().toString();
        deskripsiRapat = inputDeskripsiRapat.getText().toString();
        if(tanggal.equalsIgnoreCase("") || jam.equalsIgnoreCase("") || judulrapat.equalsIgnoreCase("") || deskripsiRapat.equalsIgnoreCase("")) {
            hideProgressBar();
            dialogBuilder("Isian Tidak Lengkap", "Mohon mengisi semua isian !!!");
        } else {

            AparatNewPengumuman pengumuman = new AparatNewPengumuman();
            pengumuman.nama = judulrapat;
            pengumuman.jam = jam;
            pengumuman.tanggal = tanggal;
            pengumuman.deskripsi = deskripsiRapat;
            pengumuman.admin = fbid;
            pengumuman.idgroup = groupId;
            presenter.saveNewPengumuman(pengumuman);
        }


    }

    private void addDatePickerListener() {
        cardTanggalRapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });


    }

    private void addTimePickerListener() {
        cardJamRapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getSupportFragmentManager(), "timepicker");
            }
        });
    }

    private void addButtonRapatListener() {
        btnPengumumanRapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressBar();
                populateData();
            }
        });
    }

    public void bindDataTanggal(String tanggal) {
        this.tanggal = tanggal;
        textTanggal.setText(tanggal);
    }

    public void bindDataJam(String jam) {
        this.jam = jam;
        textJam.setText(jam);
    }


    @Override
    public void onSuccessCreatePengumuman() {
        hideProgressBar();
        dialogBuilder("Proses Berhasil", "Pengumuman Rapat berhasil dibuat");
    }

    @Override
    public void onFailCreatePengumuman() {
        hideProgressBar();
        dialogBuilder("Terjadi Kesalahan", "Pengumuman Rapat gagal dibuat ");

    }

    public void dialogBuilder(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
