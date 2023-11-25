package com.latihan.rentalmobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {

    TextView namaLengkap, username;
    TextInputLayout namaLengkapProfile, emailProfile, noTeleponProfile, passwordProfile;

//    Global Variables to hold user data inside this activity
    String _USERNAME, _NAMALENGKAP, _EMAIL, _NOTELEPON, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance("https://rental-mobil-bde39-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");

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
        _USERNAME = intent.getStringExtra("username");
        _NAMALENGKAP = intent.getStringExtra("namaLengkap");
        _EMAIL = intent.getStringExtra("email");
        _NOTELEPON = intent.getStringExtra("noTelepon");
        _PASSWORD = intent.getStringExtra("password");

        namaLengkap.setText(_NAMALENGKAP);
        username.setText(_USERNAME);
        namaLengkapProfile.getEditText().setText(_NAMALENGKAP);
        emailProfile.getEditText().setText(_EMAIL);
        noTeleponProfile.getEditText().setText(_NOTELEPON);
        passwordProfile.getEditText().setText(_PASSWORD);
    }

    public void update(View view) {
        if (isNameChanged() || isEmailChanged() || isNoTeleponChanged() || isPasswordChanged()) {
            Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Tidak ada perubahan, tidak bisa diubah!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNoTeleponChanged() {
        if (!_NOTELEPON.equals(noTeleponProfile.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("no_telepon").setValue(noTeleponProfile.getEditText().getText().toString());
            _NOTELEPON = noTeleponProfile.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!_EMAIL.equals(emailProfile.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("email").setValue(emailProfile.getEditText().getText().toString());
            _EMAIL = emailProfile.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(passwordProfile.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("password").setValue(passwordProfile.getEditText().getText().toString());
            _PASSWORD = passwordProfile.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {
        if (!_NAMALENGKAP.equals(namaLengkapProfile.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("nama_lengkap").setValue(namaLengkapProfile.getEditText().getText().toString());
            _NAMALENGKAP = namaLengkapProfile.getEditText().getText().toString();
            namaLengkap.setText(_NAMALENGKAP);
            return true;
        } else {
            return false;
        }
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