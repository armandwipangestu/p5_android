package com.tugas.uas_rentalmobil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ListCar extends BaseActivity {

    // Variables
    TextView namaLengkap, username, email, noTelepon, password;

    // Global Variables to hold user data inside this activity
    String _NAMA_LENGKAP_, _USERNAME_, _EMAIL_, _NO_TELEPON_, _PASSWORD_;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hooks
        namaLengkap = findViewById(R.id.namaLengkap);

        // Connect to Database or Node Firebase
        database = FirebaseDatabase.getInstance("https://uas---rental-mobil-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // Set reference for store the data user sign up
        reference = database.getReference("users");

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
        _USERNAME_ = preferences.getString("username", "");

        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME_);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if data exist by username input
                if (snapshot.exists()) {
                        // Get all data from DB
                        _EMAIL_ = snapshot.child(_USERNAME_).child("email").getValue(String.class);
                        _NO_TELEPON_ = snapshot.child(_USERNAME_).child("noTelepon").getValue(String.class);
                        _PASSWORD_ = snapshot.child(_USERNAME_).child("password").getValue(String.class);

                        namaLengkap.setText("Hi, " + _NAMA_LENGKAP_);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}