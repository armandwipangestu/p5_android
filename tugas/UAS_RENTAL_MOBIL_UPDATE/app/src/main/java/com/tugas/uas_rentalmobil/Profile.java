package com.tugas.uas_rentalmobil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends BaseActivity {

    TextInputLayout nama_lengkap, email, noTelepon, password;
    ImageView avatar_image;
    TextView namalengkaplabel, usernamelabel;
    //global
    String NAMA_LENGKAP, USERNAME, EMAIL, NO_TELEPON, PASSWORD;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bug bottom navigation
        //setContentView(R.layout.activity_profile);

        //hooks
        avatar_image = findViewById(R.id.avatar_image);
        nama_lengkap = findViewById(R.id.nama_lengkap_profile);
        email = findViewById(R.id.email_profile);
        noTelepon = findViewById(R.id.no_telepon_profile);
        password = findViewById(R.id.password_profile);
        namalengkaplabel = findViewById(R.id.nama_lengkap);
        usernamelabel = findViewById(R.id.username);

        // Connect to Database or Node Firebase
        database = FirebaseDatabase.getInstance("https://uas---rental-mobil-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // Set reference for store the data user sign up
        reference = database.getReference("users");

        showData();
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.myProfile;
    }

    private void showData() {
        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        NAMA_LENGKAP = preferences.getString("namaLengkap", "");
        USERNAME = preferences.getString("username", "");
        NO_TELEPON = preferences.getString("noTelepon", "");
        PASSWORD = preferences.getString("password", "");
        EMAIL = preferences.getString("email", "");

        namalengkaplabel.setText(NAMA_LENGKAP);
        usernamelabel.setText("@" + USERNAME);
        nama_lengkap.getEditText().setText(NAMA_LENGKAP);
        email.getEditText().setText(EMAIL);
        noTelepon.getEditText().setText(NO_TELEPON);
        password.getEditText().setText(PASSWORD);

        if (!USERNAME.equals("arman")) {
            avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.avatar_image_female));
        } else {
            avatar_image.setImageDrawable(getResources().getDrawable(R.drawable.me_circle));
        }
    }

    public void update(View view) {
        boolean dataChanged = isDataChanged();
        if (dataChanged) {
            Toast.makeText(this, "Data berhasil di perbarui!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data tidak ada perubahan!", Toast.LENGTH_LONG).show();
        }
        Log.d("Debug", "isDataChanged(): " + dataChanged);
    }

    private boolean isDataChanged() {
        String newNamaLengkap = nama_lengkap.getEditText().getText().toString();
        String newEmail = email.getEditText().getText().toString();
        String newNoTelepon = noTelepon.getEditText().getText().toString();
        String newPassword = password.getEditText().getText().toString();

        boolean dataChanged = false;

        if (!NAMA_LENGKAP.equalsIgnoreCase(newNamaLengkap)) {
            reference.child(USERNAME).child("namaLengkap").setValue(newNamaLengkap);
            NAMA_LENGKAP = newNamaLengkap;
            namalengkaplabel.setText(NAMA_LENGKAP);
            dataChanged = true;
        }

        if (!EMAIL.equalsIgnoreCase(newEmail)) {
            reference.child(USERNAME).child("email").setValue(newEmail);
            EMAIL = newEmail;
            dataChanged = true;
        }

        if (!NO_TELEPON.equalsIgnoreCase(newNoTelepon)) {
            reference.child(USERNAME).child("noTelepon").setValue(newNoTelepon);
            NO_TELEPON = newNoTelepon;
            dataChanged = true;
        }

        if (!PASSWORD.equalsIgnoreCase(newPassword)) {
            reference.child(USERNAME).child("password").setValue(newPassword);
            PASSWORD = newPassword;
            dataChanged = true;
        }

        return dataChanged;
    }

}
