package com.tugas.uas_rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends BaseActivity {

    // Variables
    TextView namaLengkap;

    // Global Variables to hold user data inside this activity
    String _NAMA_LENGKAP_;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//
//        // Hooks
//        namaLengkap = findViewById(R.id.namaLengkap);
//
//        showData();
//    }

    @Override
    int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.listCar;
    }

//    private void showData() {
//        Intent intent = getIntent();
//
//        _NAMA_LENGKAP_ = intent.getStringExtra("namaLengkap");
//
//        namaLengkap.setText(_NAMA_LENGKAP_);
//    }
}