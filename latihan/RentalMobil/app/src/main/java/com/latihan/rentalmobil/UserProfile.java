package com.latihan.rentalmobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextView namaLengkap, username;
    TextInputLayout namaLengkapProfile, emailProfile, noTeleponProfile, passwordProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        Hooks
        namaLengkap = findViewById(R.id.nama_lengkap);
        username = findViewById(R.id.username);
        namaLengkapProfile = findViewById(R.id.nama_lengkap_profile);
        emailProfile = findViewById(R.id.email_profile);
        noTeleponProfile = findViewById(R.id.no_telepon_profile);
        passwordProfile = findViewById(R.id.password_profile);

//        Show All Data
        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_namaLengkap = intent.getStringExtra("namaLengkap");
        String user_email = intent.getStringExtra("email");
        String user_no_telepon = intent.getStringExtra("noTelepon");
        String user_password = intent.getStringExtra("password");

        namaLengkap.setText(user_namaLengkap);
        username.setText(user_username);
        namaLengkapProfile.getEditText().setText(user_namaLengkap);
        emailProfile.getEditText().setText(user_email);
        noTeleponProfile.getEditText().setText(user_no_telepon);
        passwordProfile.getEditText().setText(user_password);
    }

    private static final int TIME_INTERVAL = 2000; // Waktu interval antara dua kali tekan back (dalam milidetik)
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (backPressedTime + TIME_INTERVAL > System.currentTimeMillis()) {
            // Jika selisih waktu antara dua kali tekan back cukup kecil, maka keluar dari aplikasi
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Tekan kembali sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}