package com.tugas.uas_rentalmobil;

import android.os.Bundle;

public class Transaction extends BaseActivity {

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
}