package com.tugas.uas_rentalmobil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ListCar extends BaseActivity {

    // Variables
    TextView namaLengkap;

    // Global Variables to hold user data inside this activity
    String _NAMA_LENGKAP_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hooks
        namaLengkap = findViewById(R.id.namaLengkap);

        showData();
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_list_car;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.listCar;
    }

    private void showData() {
//        Intent intent = getIntent();
//
//        _NAMA_LENGKAP_ = intent.getStringExtra("namaLengkap");

        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        _NAMA_LENGKAP_ = preferences.getString("namaLengkap", "");

        namaLengkap.setText(_NAMA_LENGKAP_);
    }
}