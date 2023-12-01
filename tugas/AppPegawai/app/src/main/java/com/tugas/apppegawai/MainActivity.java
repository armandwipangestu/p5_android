package com.tugas.apppegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout btnGajiPegawai, btnPetunjuk, btnTentang, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hooks
        btnGajiPegawai = findViewById(R.id.btnGajiPegawai);
        btnPetunjuk = findViewById(R.id.btnPetunjuk);
        btnTentang = findViewById(R.id.btnTentang);
        btnKeluar = findViewById(R.id.btnKeluar);

        btnGajiPegawai.setOnClickListener(v -> {
            Intent intent = new Intent(this, GajiPegawai.class);
            startActivity(intent);
        });

        btnPetunjuk.setOnClickListener(v -> {
            Intent intent = new Intent(this, Petunjuk.class);
            startActivity(intent);
        });

        btnTentang.setOnClickListener(v -> {
            Intent intent = new Intent(this, Tentang.class);
            startActivity(intent);
        });

        btnKeluar.setOnClickListener(v -> {
            Intent intent = new Intent(this, Keluar.class);
            startActivity(intent);
        });
    }
}