package com.tugas.uas_rentalmobil;

import android.os.Bundle;
import android.widget.Toast;

public class Transaction extends BaseActivity {

    private static final int _TIME_INTERVAL = 2000; // time for between 2x when press back (in miliseconds)
    private long backPressedTime = 0; // initialize back press

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_transaction;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.transaction;
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + _TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}