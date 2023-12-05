package com.tugas.uas_rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Transaction extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_transaction);
//        bottomNavigationView = findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setOnItemSelectedListener(navigation);
//    }

    @Override
    int getLayoutId() {
        return R.layout.activity_transaction;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.transaction;
    }
}