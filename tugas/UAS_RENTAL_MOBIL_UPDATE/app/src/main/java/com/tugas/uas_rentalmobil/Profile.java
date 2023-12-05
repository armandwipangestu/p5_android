package com.tugas.uas_rentalmobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends BaseActivity {
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_transaction);
//        bottomNavigationView = findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setOnItemSelectedListener(navigation);
//    }

    @Override
    int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    int getBottomNavigationMenuItemId() {
        return R.id.myProfile;
    }

    TextInputLayout nama_lengkap, email, noTelepon, password;
    TextView namalengkaplabel, usernamelabel;
    //global
    String NAMA_LENGKAP, USERNAME, EMAIL, NO_TELEPON, PASSWORD;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //nooks
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
    private void showData() {

        SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
        NAMA_LENGKAP = preferences.getString("namaLengkap", "");
        USERNAME = preferences.getString("username", "");
        NO_TELEPON = preferences.getString("noTelepon", "");
        PASSWORD = preferences.getString("password", "");
        EMAIL = preferences.getString("email", "");

        namalengkaplabel.setText(NAMA_LENGKAP);
        usernamelabel.setText(USERNAME);
        nama_lengkap.getEditText().setText(NAMA_LENGKAP);
        email.getEditText().setText(EMAIL);
        noTelepon.getEditText().setText(NO_TELEPON);





    }
    public void update(View view) {

        if (isNameChanged() || isEmailChange() || isPasswordChange()) {
            Toast.makeText(this, "Data Has been update", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data is and can not be update", Toast.LENGTH_LONG).show();
        }
    }
    private boolean isPasswordChange(){
        if(!PASSWORD.equals(password.getEditText().getText().toString()))
        {
            reference.child(USERNAME).child("password").setValue(password.getEditText().getText().toString());
            PASSWORD=password.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isNameChanged(){
        if(!NAMA_LENGKAP.equals(nama_lengkap.getEditText().getText().toString())){
            reference.child(USERNAME).child("namaLengkap").setValue(nama_lengkap.getEditText().getText().toString());

            NAMA_LENGKAP=nama_lengkap.getEditText().getText().toString();

            return true;
        }else{
            return false;
        }
    }

    private boolean isEmailChange(){
        if(!EMAIL.equals(email.getEditText().getText().toString()))
        {
            reference.child(USERNAME).child("email").setValue(email.getEditText().getText().toString());

            EMAIL=email.getEditText().getText().toString();

            return true;
        }else{
            return false;
        }
    }
}
