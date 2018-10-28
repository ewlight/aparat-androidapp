package com.taracorpora.aparatapp;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;


public class BarcodeScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener  {

    private BarcodeReader barcodeReader;
    private String TAG = BarcodeScannerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }


    @Override
    public void onScanned(Barcode barcode) {
        Log.e(TAG, "onScanned: " + barcode.displayValue);
        String memberid = barcode.displayValue;
        barcodeReader.playBeep();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent data = new Intent();
                data.setData(Uri.parse(memberid));
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
        finish();
    }
}
