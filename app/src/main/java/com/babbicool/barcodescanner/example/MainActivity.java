package com.babbicool.barcodescanner.example;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.babbicool.lib.barcodescanner.BarcodeReader;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  BarcodeReader.BarcodeReaderListener{

    private BarcodeReader barcodeReader;

    private final static int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                barcodeReader =  (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);


            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }





    }

        @Override
        public void onScanned(Barcode barcode) {
            Log.d("MainActivity","-------------------->" + barcode.displayValue);
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

        }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {
                    Toast.makeText(this,"No permission ", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
