package com.tugas.uas_rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    // Variables
    TextView namaLengkap;

    // Global Variables to hold user data inside this activity
    String _NAMA_LENGKAP_;

    // Bottom Navigation
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener navigation = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();

            if (itemId == R.id.transaction) {
                Intent intent = new Intent(Dashboard.this, Transaction.class);
                startActivity(intent);
                finish();
                return true;
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(navigation);

        // Hooks
        namaLengkap = findViewById(R.id.namaLengkap);

        showData();
    }

    private void showData() {
        Intent intent = getIntent();

        _NAMA_LENGKAP_ = intent.getStringExtra("namaLengkap");

        namaLengkap.setText(_NAMA_LENGKAP_);
    }
}